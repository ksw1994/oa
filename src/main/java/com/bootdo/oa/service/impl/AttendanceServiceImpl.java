package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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

    @Override
    public int deleteByCondition(Map<String, Object> map) {
        return attendanceDao.deleteByCondition(map);
    }
    /**
     * 根据 考勤信息的  身份号 月份 和项目ID 更新考勤信息  没有就新增
     */
    @Transactional
    public void saveOrUpdate(AttendanceDO attendanceDO) {
        Map<String,Object> selectMap = new HashMap<String, Object>();
        selectMap.put("idCard", attendanceDO.getIdCard());
        selectMap.put("mouth", attendanceDO.getMouth());
        selectMap.put("projectId", attendanceDO.getProjectId());
        List<AttendanceDO> attendanceDOList = attendanceDao.list(selectMap);
        if(attendanceDOList!=null && attendanceDOList.size()>0) {
            attendanceDO.setId(attendanceDOList.get(0).getId());
            attendanceDao.update(attendanceDO);
        }else {
            attendanceDao.save(attendanceDO);
        }
    }
}
