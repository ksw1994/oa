package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.ProjectPersonDao;
import com.bootdo.oa.domain.ProjectPersonDO;
import com.bootdo.oa.service.ProjectPersonService;



@Service
public class ProjectPersonServiceImpl implements ProjectPersonService {
	@Autowired
	private ProjectPersonDao projectPersonDao;
	
	@Override
	public ProjectPersonDO get(Integer id){
		return projectPersonDao.get(id);
	}
	
	@Override
	public List<ProjectPersonDO> list(Map<String, Object> map){
		return projectPersonDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return projectPersonDao.count(map);
	}
	
	@Override
	public int save(ProjectPersonDO projectPerson){
		return projectPersonDao.save(projectPerson);
	}
	
	@Override
	public int update(ProjectPersonDO projectPerson){
		return projectPersonDao.update(projectPerson);
	}
	
	@Override
	public int remove(Integer id){
		return projectPersonDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return projectPersonDao.batchRemove(ids);
	}
	
}
