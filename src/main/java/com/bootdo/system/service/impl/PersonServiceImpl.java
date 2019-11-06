package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.PersonDao;
import com.bootdo.system.domain.PersonDO;
import com.bootdo.system.service.PersonService;



@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;
	
	@Override
	public PersonDO get(Integer pid){
		return personDao.get(pid);
	}
	
	@Override
	public List<PersonDO> list(Map<String, Object> map){
		return personDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return personDao.count(map);
	}
	
	@Override
	public int save(PersonDO person){
		return personDao.save(person);
	}
	
	@Override
	public int update(PersonDO person){
		return personDao.update(person);
	}
	
	@Override
	public int remove(Integer pid){
		return personDao.remove(pid);
	}
	
	@Override
	public int batchRemove(Integer[] pids){
		return personDao.batchRemove(pids);
	}
	
}
