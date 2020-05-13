package com.bootdo.oa.service.impl;

import com.bootdo.common.excel.ExcelUtil;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
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
		//创建人用户id
		leaveTime.setCreateBy(ShiroUtils.getUserId());
		//创建时间
		leaveTime.setCreateTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		return leaveTimeDao.save(leaveTime);
	}
	
	@Override
	public int update(LeaveTimeDO leaveTime){
		leaveTime.setUpdateBy(ShiroUtils.getUserId());
		leaveTime.setUpdateTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
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
		List<LeaveTimeDO> resultList = getExcel(list);
		System.out.println(resultList);
		for (LeaveTimeDO leaveTimeDO : resultList) {
			save(leaveTimeDO);
		}
	}

	@Override
	public List<LeaveTimeDO> getListByDate(String date,String deptName) {
		return leaveTimeDao.getListByDate(date,deptName);
	}

	public List<LeaveTimeDO> getExcel(List<List<String>> list){
		List<LeaveTimeDO> resultList = new ArrayList<>();
		for (List<String> strings : list) {
			if (strings.get(2).equals("完成") && strings.get(3).equals("同意")){
				LeaveTimeDO leaveTimeDO = new LeaveTimeDO();
				leaveTimeDO.setName(strings.get(9));
				leaveTimeDO.setDeptName(strings.get(10));
				leaveTimeDO.setLeaveType(strings.get(14));
				leaveTimeDO.setStart(strings.get(15));
				leaveTimeDO.setEnd(strings.get(16));
				if (leaveTimeDO.getLeaveType().equals("病假") && getNum(strings.get(17)).doubleValue() == 3.5){
					leaveTimeDO.setDuration(new BigDecimal(4));
				}else if (leaveTimeDO.getLeaveType().equals("事假") && getNum(strings.get(17)).doubleValue() == 3.5){
					leaveTimeDO.setDuration(new BigDecimal(4));
				}else{
					leaveTimeDO.setDuration(getNum(strings.get(17)));
				}
				leaveTimeDO.setReason(strings.get(18));
				try {
					leaveTimeDO.setLeaveDate(DateUtils.getDate(leaveTimeDO.getStart().substring(0,10)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				resultList.add(leaveTimeDO);
			}
		}
		return resultList;
	}

	public  BigDecimal getNum(String s) {
		String sp = "(\\d+\\.\\d+)";
		Pattern p = Pattern.compile(sp);
		//Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
		Matcher m = p.matcher(s);
		//m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
		if (m.find()) {
			//如果有相匹配的,则判断是否为null操作
			//group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
			s = m.group(1) == null ? "" : m.group(1);
		} else {
			//如果匹配不到小数，就进行整数匹配
			String sp1 = "(\\d+)";
			p = Pattern.compile(sp1);
			m = p.matcher(s);
			if (m.find()) {
				//如果有整数相匹配
				s = m.group(1) == null ? "" : m.group(1);
			} else {
				//如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
				s = "";
			}
		}
		return new BigDecimal(Double.valueOf(s));
	}

}
