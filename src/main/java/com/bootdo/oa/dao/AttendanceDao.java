package com.bootdo.oa.dao;

import com.bootdo.oa.domain.AttendanceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考勤明细表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 11:08:46
 */
@Mapper
public interface AttendanceDao {

	AttendanceDO get(Integer id);
	
	List<AttendanceDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AttendanceDO attendance);
	
	int update(AttendanceDO attendance);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
