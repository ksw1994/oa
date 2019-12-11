package com.bootdo.oa.dao;

import com.bootdo.oa.domain.OvertimeDO;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 打卡时间表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-12-03 10:34:37
 */
@Mapper
public interface OvertimeDao {

	OvertimeDO get(String id);
	
	List<OvertimeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OvertimeDO overtime);
	
	int update(OvertimeDO overtime);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	/**
	 * 根据年月获取
	 * @param date
	 * @return
	 */
	List<OvertimeDO> getListByDate(String date,String deptName);

	/**
	 * 根据年月获取
	 * @param date
	 * @return
	 */
	List<String> getNameListByDate(String date,String deptName);

	/**
	 * 获取所有的部门
	 * @return
	 */
	List<String> getAllDept();
}
