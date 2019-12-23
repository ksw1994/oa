package com.bootdo.oa.service.impl;

import cn.afterturn.easypoi.cache.manager.IFileLoader;
import com.bootdo.common.excel.ExcelUtil;
import com.bootdo.common.excel.WriteExcle;
import com.bootdo.common.exception.BusinessException;
import com.bootdo.common.utils.*;
import com.bootdo.oa.domain.LeaveTimeDO;
import com.bootdo.oa.domain.WeekScopeDO;
import com.bootdo.oa.service.LeaveTimeService;
import com.bootdo.oa.service.WeekScopeService;
import io.netty.util.internal.StringUtil;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.write.WritableWorkbook;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

import com.bootdo.oa.dao.OvertimeDao;
import com.bootdo.oa.domain.OvertimeDO;
import com.bootdo.oa.service.OvertimeService;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;


@Service
public class OvertimeServiceImpl implements OvertimeService {
	@Autowired
	private OvertimeDao overtimeDao;

	@Autowired
	private WeekScopeService weekScopeService;

	@Autowired
	private LeaveTimeService leaveTimeService;

	@Value("${bootdo.templatePath}")
	private String templatePath;

	@Value("${bootdo.exportPath}")
	private String exportPath;

	@Override
	public OvertimeDO get(String id) {
		return overtimeDao.get(id);
	}

	@Override
	public List<OvertimeDO> list(Map<String, Object> map) {
		return overtimeDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return overtimeDao.count(map);
	}

	@Override
	public int save(OvertimeDO overtime) {
		overtime.setId(UUIDUtils.randomUUID());
		overtime.setCreateBy(ShiroUtils.getUserId());//创建人用户id
		overtime.setCreateTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));//创建时间
		return overtimeDao.save(overtime);
	}

	@Override
	public int update(OvertimeDO overtime) {
		overtime.setUpdateBy(ShiroUtils.getUserId());
		overtime.setUpdateTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		return overtimeDao.update(overtime);
	}

	@Override
	public int remove(String id) {
		return overtimeDao.remove(id);
	}

	@Override
	public int batchRemove(String[] ids) {
		return overtimeDao.batchRemove(ids);
	}

	@Override
	public void importExcelFile(MultipartFile file) {
		List<List<String>> list = ExcelUtil.readExcel(file, 0);
		String[] dates = list.get(0).get(0).split("报表生成时间：");
		String yearMonth = dates[1].substring(0, 7);//该打卡记录的年月
		list.remove(list.get(0));
		list.remove(list.get(0));
		List<OvertimeDO> resultList = new ArrayList<>();
		try {
			resultList = getExcle(list, yearMonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (OvertimeDO overtimeDO : resultList) {
			save(overtimeDO);
		}
	}

	public List<OvertimeDO> getExcle(List<List<String>> list, String yearMonth) throws ParseException {
		List<OvertimeDO> resultList = new ArrayList<>();
		WeekScopeDO weekScope = weekScopeService.getByYearMonth(yearMonth);
		List<String> scopeList = new ArrayList<>();//这个月所有的周末
		String[] scopes = weekScope.getScope().split(",");
		for (String scope : scopes) {
			scopeList.add(scope.trim());
		}
		for (List<String> strings : list) {
			String name = strings.get(0).trim();
			String deptName = strings.get(1).trim();
			for (int i = 5; i < strings.size(); i++) {
				StringBuffer date = new StringBuffer();
				date.append(yearMonth);
				date.append("-");
				date.append(i - 4);
				if (isOk(strings.get(i), scopeList, date.toString()).equals("3")) {
					continue;
				}
				OvertimeDO overtimeDO = new OvertimeDO();
				String[] times = strings.get(i).split("\n");
				overtimeDO.setName(name);//名称
				overtimeDO.setDeptName(deptName);//部门
				overtimeDO.setStartTime(times[0].trim());//只取有效值
				overtimeDO.setEndTime(times[times.length - 1].trim());//只取有效值
				overtimeDO.setDate(DateUtils.getDate(date.toString()));
				if (isOk(strings.get(i), scopeList, date.toString()).equals(OvertimeDO.OVERTIME_STATUS)) {//加班打卡且有效
					overtimeDO.setStatus(OvertimeDO.OVERTIME_STATUS);
					//判断改天是否是周末
					if (scopeList.contains(date.toString())) {//周末
						if (DateUtils.largerTime(overtimeDO.getStartTime(), overtimeDO.getEndTime())) {//如果开始时间大于结束时间那肯定是跨天打卡
							overtimeDO.setOvertime(DateUtils.timeDifference(overtimeDO.getStartTime(), "23:59"));
						} else {
							overtimeDO.setOvertime(DateUtils.timeDifference(overtimeDO.getStartTime(), overtimeDO.getEndTime()));
						}
					} else {//工作天
						overtimeDO.setOvertime(DateUtils.timeDifference("19:00", overtimeDO.getEndTime()));
					}
					resultList.add(overtimeDO);
				} else if (isOk(strings.get(i), scopeList, date.toString()).equals(OvertimeDO.OUT_WORK_STATUS)) {//外勤打卡，只记录时间，不计算小时数
					overtimeDO.setStatus(OvertimeDO.OUT_WORK_STATUS);
					resultList.add(overtimeDO);
				} else if (isOk(strings.get(i), scopeList, date.toString()).equals(OvertimeDO.NORMAL_STATUS)) {//正常打卡，只保留数据
					overtimeDO.setStatus(OvertimeDO.NORMAL_STATUS);
					resultList.add(overtimeDO);
				}
			}
		}
		return resultList;
	}

	/**
	 * 3:就是不用检查(null) o:正常 1:外勤 2:正常打卡
	 *
	 * @param s
	 * @param scopeList
	 * @param date
	 * @return
	 */
	public String isOk(String s, List<String> scopeList, String date) {
		if (!StringUtil.isNullOrEmpty(s)) {
			String[] strings = s.split("\n");
			String time = strings[strings.length - 1];
			if (time.length() == 5) {
				if (DateUtils.largerTime(time, "21:00")) {//打卡时间晚于9点
					return "0";
				} else if (scopeList.contains(date)) {//周末
					return "0";
				}
				return "2";
			} else {
				return "1";
			}
		}
		return "3";
	}

	@Override
	public File exportOvertime(String date,String deptName) {
		long start = System.currentTimeMillis();
		//date = "2019-11-01";
		//deptName = "BU2广州开发中心";
		StringBuffer tempPath = new StringBuffer();//模板地址
		tempPath.append(templatePath);
		String newPath = exportPath+"加班数据.xlsx";
		//先判断该月的最大天数
		int maxDate = 0;
			try {
			maxDate = DateUtils.getMaxDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (maxDate == 28){
			tempPath.append("加班考勤模板-28.xlsx");
		}else if (maxDate == 29){
			tempPath.append("加班考勤模板-29.xlsx");
		}else if (maxDate == 30){
			tempPath.append("加班考勤模板-30.xlsx");
		}else if (maxDate == 31){
			tempPath.append("加班考勤模板-31.xlsx");
		}

		List<OvertimeDO> overtimeList = overtimeDao.getListByDate(date,deptName);
		if (CollectionUtils.isEmpty(overtimeList)){
			return null;
		}
		List<String> nameList = overtimeDao.getNameListByDate(date,deptName);
		//该月所有的请假数据
		List<LeaveTimeDO> leaveTimeList = leaveTimeService.getListByDate(date,deptName);
		//加班请假数据数据
		List<List<Object>> dataList = getData(overtimeList, nameList,leaveTimeList,date,maxDate);

		//新建一份excle
		File newFile = WriteExcle.createNewFile(tempPath.toString(), newPath);
		// 新文件写入数据，并下载
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		XSSFCellStyle style0 = null;
		XSSFCellStyle style1 = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(newFile));// 创建个workbook
			//合并的单元格样式
			style0 = workbook.createCellStyle();
			//垂直居中
			style0.setVerticalAlignment(VerticalAlignment.CENTER);
			style0.setAlignment(HorizontalAlignment.CENTER);//水平居中 // 创建一个居中格式
			style0.setBorderLeft(BorderStyle.THIN);
			style0.setBorderRight(BorderStyle.THIN);
			style0.setBorderBottom(BorderStyle.THIN);
			style0.setBorderTop(BorderStyle.THIN);

			//合并的单元格样式(有灰色背景)
			style1 = workbook.createCellStyle();
			//垂直居中
			style1.setVerticalAlignment(VerticalAlignment.CENTER);
			style1.setAlignment(HorizontalAlignment.CENTER);//水平居中 // 创建一个居中格式
			style1.setBorderLeft(BorderStyle.THIN);
			style1.setBorderRight(BorderStyle.THIN);
			style1.setBorderBottom(BorderStyle.THIN);
			style1.setBorderTop(BorderStyle.THIN);
			style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

			sheet = workbook.getSheetAt(0);
			XSSFRow row0 = sheet.createRow(0);
			row0.setHeightInPoints(45);//行高
			XSSFCell cell0 = row0.createCell(0);
			String year = date.substring(0, 4);
			String month = date.substring(5, 7);
			cell0.setCellValue(year+"年"+month+"月考勤表");
			cell0.setCellStyle(style0);
			CellRangeAddress region = new CellRangeAddress(0, 0, 0, 22 + maxDate);
			sheet.addMergedRegion(region);
			setBorderStyle(sheet, region);
			initSheet(workbook,sheet,date,maxDate);
			workbook.setSheetName(0,month+"月");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 写数据
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(newFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		XSSFRow row2 = sheet.getRow(2);
		int lastRowNum = 3;
		for (List<Object> l : dataList) {
			XSSFRow row = sheet.createRow(lastRowNum);
			XSSFCell cell0 = row.createCell(0);
			cell0.setCellValue((Integer) l.get(0));
			cell0.setCellStyle(style0);
			XSSFCell cell1 = row.createCell(1);
			cell1.setCellValue((String) l.get(1));
			cell1.setCellStyle(style0);
			for (int i = 0; i < maxDate ; i++) {
				XSSFCell cell = row.createCell(i+2);
				BigDecimal overtime = (BigDecimal) l.get(i + 2);
				if (overtime.doubleValue() > 0){
					cell.setCellValue(overtime.doubleValue());
				}
				if (row2.getCell(i+2).getStringCellValue().equals("六") || row2.getCell(i+2).getStringCellValue().equals("日")){
					cell.setCellStyle(style1);
				}else{
					cell.setCellStyle(style0);
				}
			}
			for (int i = 0; i < 21 ; i++) {
				XSSFCell cell = row.createCell(maxDate+2+i);
				if (i == 8 || i == 10 || i == 12 || i==14 || i== 16){//调休、事假、病假、年假、其他假期日期
					String d = (String) l.get(maxDate + i - 6);
					if (!StringUtil.isNullOrEmpty(d)){
						cell.setCellValue(d);
					}
				}
				if (i == 9 || i == 11 || i== 13 || i== 15 || i== 17){//调休时间
					BigDecimal times = (BigDecimal) l.get(maxDate + i-6);
					if (times.doubleValue() > 0){
						cell.setCellValue(times.doubleValue());
					}
				}
				cell.setCellStyle(style0);
				//自适应列宽
				sheet.autoSizeColumn(maxDate+2+i,true);
			}
			lastRowNum++;
		}
		try {
			fos.flush();
			workbook.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println((System.currentTimeMillis()-start)/1000 +"秒");
		return newFile;
	}

	@Override
	public List<String> getAllDept() {
		return overtimeDao.getAllDept();
	}

	/**
	 * 初始化模板（里面的日期）
	 * @param sheet
	 * @param maxDate
	 */
	private void initSheet(XSSFWorkbook workbook,XSSFSheet sheet,String date, int maxDate) {
		//设置周末的单元格背景色为灰色
		String yearMonth = date.substring(0,8);
		XSSFRow row1 = sheet.getRow(1);
		XSSFRow row2 = sheet.getRow(2);
		CellStyle style = workbook.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setAlignment(HorizontalAlignment.CENTER);//水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		for (int i = 0; i < maxDate ; i++) {
			XSSFCell cell = row2.getCell(i+2);
			try {
				String weekDay = DateUtils.getWeekDay(yearMonth + (i + 1));
				cell.setCellValue(weekDay);
				if (weekDay.equals("六") || weekDay.equals("日")){
					cell.setCellStyle(style);
					row1.getCell(i+2).setCellStyle(style);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置合并单元格边框 - 线条
	 * */
	private static void setBorderStyle(Sheet sheet, CellRangeAddress region) {
		// 合并单元格左边框样式
		RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
		RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);

		// 合并单元格上边框样式
		RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
		RegionUtil.setTopBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);

		// 合并单元格右边框样式
		RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
		RegionUtil.setRightBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);

		// 合并单元格下边框样式
		RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
		RegionUtil.setBottomBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);
	}

	public List<List<Object>> getData(List<OvertimeDO> overtimeList, List<String> nameList, List<LeaveTimeDO> leaveTimeList, String date, int maxDate){
		List<List<Object>> resultList = new ArrayList<>();
		String yearMonth = date.substring(0,8);
		int count = 1;
		for (String name : nameList) {
			List<Object> list = new ArrayList<>();
			list.add(count);
			list.add(name);
			for (int i = 1; i < maxDate+1 ; i++) {
				try {
					BigDecimal overtime = getOvertime(overtimeList, name, yearMonth + i);
					list.add(overtime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			//这个月的总的调休日期
			StringBuffer restCan = new StringBuffer();
			//这个月的总的事假日期
			StringBuffer casualLeave = new StringBuffer();
			//这个月的总的病假日期
			StringBuffer sickLeave = new StringBuffer();
			//这个月的总的年假日期
			StringBuffer yearLeave = new StringBuffer();
			//这个月的总的其他假期日期
			StringBuffer otherLeave = new StringBuffer();
			for (int i = 1; i < maxDate+1 ; i++) {
				if (isLeaveInThisDate(leaveTimeList,name,"调休",yearMonth+i)){
					restCan.append(yearMonth+i);
					restCan.append(",");
				}else if (isLeaveInThisDate(leaveTimeList,name,"事假",yearMonth+i)){
					casualLeave.append(yearMonth+i);
					casualLeave.append(",");
				}else if (isLeaveInThisDate(leaveTimeList,name,"病假",yearMonth+i)){
					sickLeave.append(yearMonth+i);
					sickLeave.append(",");
				}else if (isLeaveInThisDate(leaveTimeList,name,"年假",yearMonth+i)){
					yearLeave.append(yearMonth+i);
					yearLeave.append(",");
				}else if (isLeaveInThisDate(leaveTimeList,name,"婚假",yearMonth+i)){
					otherLeave.append(yearMonth+i);
					otherLeave.append(",");
				}else if (isLeaveInThisDate(leaveTimeList,name,"陪产假",yearMonth+i)){
					otherLeave.append(yearMonth+i);
					otherLeave.append(",");
				}
			}
			//这个月总的调休时间 小时数
			BigDecimal restCanHours = new BigDecimal(0);
			//这个月总的事假时间 小时
			BigDecimal casualLeaveHours = new BigDecimal(0);
			//这个月总的病假时间 小时
			BigDecimal sickLeaveHours = new BigDecimal(0);
			//这个月总的年假时间 天数
			BigDecimal yearLeaveHours = new BigDecimal(0);
			//这个月总的其他假期时间 天数
			BigDecimal otherLeaveHours = new BigDecimal(0);
			try {
				restCanHours = getLeavetime(leaveTimeList,name,yearMonth,"调休");
				casualLeaveHours = getLeavetime(leaveTimeList,name,yearMonth,"事假");
				sickLeaveHours = getLeavetime(leaveTimeList,name,yearMonth,"病假");
				yearLeaveHours = getLeavetime(leaveTimeList,name,yearMonth,"年假");
				BigDecimal marriageLeaveHours = getLeavetime(leaveTimeList, name, yearMonth, "婚假");
				BigDecimal paternityLeaveHours = getLeavetime(leaveTimeList, name, yearMonth, "陪产假");
				otherLeaveHours = marriageLeaveHours.add(paternityLeaveHours);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (!StringUtil.isNullOrEmpty(restCan.toString())){
				deleteCharAtLast(restCan);
			}
			list.add(restCan.toString());
			list.add(restCanHours);
			if (!StringUtil.isNullOrEmpty(casualLeave.toString())){
				deleteCharAtLast(casualLeave);
			}
			list.add(casualLeave.toString());
			//保留一位小数
			list.add(casualLeaveHours.divide(new BigDecimal(8),1,BigDecimal.ROUND_HALF_UP));
			if (!StringUtil.isNullOrEmpty(sickLeave.toString())){
				deleteCharAtLast(sickLeave);
			}
			list.add(sickLeave.toString());
			list.add(sickLeaveHours.divide(new BigDecimal(8),1,BigDecimal.ROUND_HALF_UP));

			if (!StringUtil.isNullOrEmpty(yearLeave.toString())){
				deleteCharAtLast(yearLeave);
			}
			list.add(yearLeave.toString());
			list.add(yearLeaveHours);

			if (!StringUtil.isNullOrEmpty(otherLeave.toString())){
				deleteCharAtLast(otherLeave);
			}
			list.add(otherLeave.toString());
			list.add(otherLeaveHours);
			resultList.add(list);
			count++;
		}
		return resultList;
	}

	public BigDecimal getOvertime(List<OvertimeDO> overtimeList,String name,String date) throws ParseException {
		for (OvertimeDO overtimeDO : overtimeList) {
			if (overtimeDO.getName().equals(name) && DateUtils.format(overtimeDO.getDate(),DateUtils.DATE_PATTERN).equals(date)
			&& overtimeDO.getOvertime() != null && overtimeDO.getOvertime().doubleValue() > 0){
				return overtimeDO.getOvertime();
			}
		}
		return new BigDecimal(0);
	}

	public BigDecimal getLeavetime(List<LeaveTimeDO> leaveTimeList,String name,String date,String leaveType) throws ParseException {
		double result = 0;
		for (LeaveTimeDO leaveTimeDO : leaveTimeList) {
			if (leaveTimeDO.getName().equals(name) && leaveTimeDO.getLeaveType().equals(leaveType)
			&& DateUtils.format(leaveTimeDO.getLeaveDate(),DateUtils.DATE_PATTERN).substring(0,7).equals(date.substring(0,7))){
				BigDecimal d = leaveTimeDO.getDuration();
				result += d.doubleValue();
			}
		}
		return new BigDecimal(result);
	}

	public boolean isLeaveInThisDate(List<LeaveTimeDO> leaveTimeList,String name,String leaveType,String date){
		for (LeaveTimeDO leaveTimeDO : leaveTimeList) {
			if (leaveTimeDO.getName().equals(name) && leaveTimeDO.getLeaveType().equals(leaveType)
					&& DateUtils.format(leaveTimeDO.getLeaveDate(),DateUtils.DATE_PATTERN).equals(date)){
				return true;
			}
		}
		return false;
	}

	public void deleteCharAtLast(StringBuffer s){
		s.deleteCharAt(s.length()-1);
	}
}