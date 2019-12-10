package com.bootdo.oa.service.impl;

import com.bootdo.common.excel.ExcelUtil;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.UUIDUtils;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bootdo.oa.dao.LeaveTimeDao;
import com.bootdo.oa.domain.LeaveTimeDO;
import com.bootdo.oa.service.LeaveTimeService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class LeaveTimeServiceImpl implements LeaveTimeService {
	@Autowired
	private LeaveTimeDao leaveTimeDao;
	
	@Override
	public LeaveTimeDO get(String id){
		return leaveTimeDao.get(id);
	}
	
	@Override
	public List<LeaveTimeDO> list(Map<String, Object> map){
		return leaveTimeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return leaveTimeDao.count(map);
	}
	
	@Override
	public int save(LeaveTimeDO leaveTime){
		leaveTime.setId(UUIDUtils.randomUUID());
		leaveTime.setCreateBy(ShiroUtils.getUserId());//创建人用户id
		leaveTime.setCreateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));//创建时间
		return leaveTimeDao.save(leaveTime);
	}
	
	@Override
	public int update(LeaveTimeDO leaveTime){
		leaveTime.setUpdateBy(ShiroUtils.getUserId());//修改人用户id
		leaveTime.setUpdateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));//修改时间
		return leaveTimeDao.update(leaveTime);
	}
	
	@Override
	public int remove(String id){
		return leaveTimeDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return leaveTimeDao.batchRemove(ids);
	}

	@Override
	public void importExcelFile(MultipartFile file) {
		List<List<String>> list = ExcelUtil.readExcel(file, 0);
		String[] dates = list.get(0).get(0).split("报表生成时间：");
		String yearMonth = dates[1].substring(0,7);//该打卡记录的年月
		list.remove(list.get(0));
		list.remove(list.get(0));
		list.remove(list.get(0));
		List<LeaveTimeDO> resultList = getExcel(list, yearMonth);
		for (LeaveTimeDO leaveTimeDO : resultList) {
			save(leaveTimeDO);
		}
	}

	public List<LeaveTimeDO> getExcel(List<List<String>> list,String yearMonth){
		List<LeaveTimeDO> resultList = new ArrayList<>();
		for (List<String> strings : list) {
			if (!isLeave(strings)){//这个月没有请假
				continue;
			}
			LeaveTimeDO leaveTimeDO = new LeaveTimeDO();
			leaveTimeDO.setName(strings.get(0));
			leaveTimeDO.setDeptName(strings.get(1));
			leaveTimeDO.setYearMonth(yearMonth);
			if (isNumeric(strings.get(8))){//丧假
				Double funeralLeave = Double.valueOf(strings.get(8));
				leaveTimeDO.setFuneralLeave(new BigDecimal(funeralLeave*8));
			}
			if (isNumeric(strings.get(9))){//事假
				leaveTimeDO.setCasualLeave(new BigDecimal(Double.valueOf(strings.get(9))));
			}
			if (isNumeric(strings.get(10))){//婚假
				Double maritalLeave = Double.valueOf(strings.get(10));
				leaveTimeDO.setMaritalLeave(new BigDecimal(maritalLeave*8));
			}
			if (isNumeric(strings.get(11))){//年假
				Double annualLeave = Double.valueOf(strings.get(11));
				leaveTimeDO.setAnnualLeave(new BigDecimal(annualLeave*8));
			}
			if (isNumeric(strings.get(12))){//病假
				leaveTimeDO.setSickLeave(new BigDecimal(Double.valueOf(strings.get(12))));
			}
			if (isNumeric(strings.get(13))){//病假
				leaveTimeDO.setRestCan(new BigDecimal(Double.valueOf(strings.get(13))));
			}
			if (isNumeric(strings.get(14))){//陪产假
				Double paternityLeave = Double.valueOf(strings.get(14));
				leaveTimeDO.setPaternityLeave(new BigDecimal(paternityLeave*8));
			}
			resultList.add(leaveTimeDO);
		}
		return resultList;
	}

	public boolean isLeave(List<String> list){
		if (isNumeric(list.get(8))){
			return true;
		}
		if (isNumeric(list.get(9))){
			return true;
		}
		if (isNumeric(list.get(10))){
			return true;
		}
		if (isNumeric(list.get(11))){
			return true;
		}
		if (isNumeric(list.get(12))){
			return true;
		}
		if (isNumeric(list.get(13))){
			return true;
		}
		if (isNumeric(list.get(14))){
			return true;
		}
		return false;
	}

	/**
	 * 利用正则表达式判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str){
		if (StringUtil.isNullOrEmpty(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}
}
