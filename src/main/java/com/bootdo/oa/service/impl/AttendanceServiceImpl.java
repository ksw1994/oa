package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.AttendanceDao;
import com.bootdo.oa.domain.AttendanceDO;
import com.bootdo.oa.service.AttendanceService;



@Service
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private AttendanceDao attendanceDao;
	
	@Override
	public AttendanceDO get(Integer id){
		return attendanceDao.get(id);
	}
	
	@Override
	public List<AttendanceDO> list(Map<String, Object> map){
		return attendanceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return attendanceDao.count(map);
	}
	
	@Override
	public int save(AttendanceDO attendance){
		return attendanceDao.save(attendance);
	}
	
	@Override
	public int update(AttendanceDO attendance){
		return attendanceDao.update(attendance);
	}
	
	@Override
	public int remove(Integer id){
		return attendanceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return attendanceDao.batchRemove(ids);
	}
	
}
