package com.bootdo.oa.service.impl;

import cn.afterturn.easypoi.cache.manager.IFileLoader;
import com.bootdo.common.excel.ExcelUtil;
import com.bootdo.common.excel.WriteExcle;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.oa.domain.WeekScopeDO;
import com.bootdo.oa.service.WeekScopeService;
import io.netty.util.internal.StringUtil;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.write.WritableWorkbook;
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
				overtimeDO.setStartTime(times[0].trim().substring(0, 5));//只取有效值
				overtimeDO.setEndTime(times[times.length - 1].trim().substring(0, 5));//只取有效值
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
	public File exportOvertime(String date) {
		long start = System.currentTimeMillis();
		date = "2019-11-01";
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

		List<OvertimeDO> overtimeList = overtimeDao.getListByDate(date);
		List<String> nameList = overtimeDao.getNameListByDate(date);
		//加班数据
		List<List<Object>> dataList = getData(overtimeList, nameList,date,maxDate);

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
				cell.setCellStyle(style0);
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

	public List<List<Object>> getData(List<OvertimeDO> overtimeList, List<String> nameList, String date, int maxDate){
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
}