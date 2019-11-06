package com.bootdo.system.dao;

import com.bootdo.system.domain.PersonDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-09 14:36:01
 */
@Mapper
public interface PersonDao {

	PersonDO get(Integer pid);
	
	List<PersonDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PersonDO person);
	
	int update(PersonDO person);
	
	int remove(Integer pid);
	
	int batchRemove(Integer[] pids);
}
