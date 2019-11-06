package com.bootdo.system.dao;

import com.bootdo.system.domain.StudentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-20 09:50:08
 */
@Mapper
public interface StudentDao {

	StudentDO get(Integer id);
	
	List<StudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentDO student);
	
	int update(StudentDO student);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

}
