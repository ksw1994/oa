package com.bootdo.oa.dao;

import com.bootdo.oa.domain.LeaveTimeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 请假小时记录表
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-10 10:35:12
 */
@Mapper
public interface LeaveTimeDao {

	LeaveTimeDO get(String id);
	
	List<LeaveTimeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeaveTimeDO leaveTime);
	
	int update(LeaveTimeDO leaveTime);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

    List<LeaveTimeDO> getListByDate(String date,String deptName);
}
