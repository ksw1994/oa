package com.bootdo.system.service;

import com.bootdo.system.domain.PersonDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-09 14:36:01
 */
public interface PersonService {
	
	PersonDO get(Integer pid);
	
	List<PersonDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PersonDO person);
	
	int update(PersonDO person);
	
	int remove(Integer pid);
	
	int batchRemove(Integer[] pids);
}
