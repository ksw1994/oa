package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.oa.dao.ProjectDao;
import com.bootdo.oa.domain.ProjectDO;
import com.bootdo.oa.service.ProjectService;



@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public ProjectDO get(String id){
		return projectDao.get(id);
	}
	
	@Override
	public List<ProjectDO> list(Map<String, Object> map){
		return projectDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return projectDao.count(map);
	}
	
	@Override
	public int save(ProjectDO project){
		return projectDao.save(project);
	}
	
	@Override
	public int update(ProjectDO project){
		return projectDao.update(project);
	}
	
	@Override
	public int remove(String id){
		return projectDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return projectDao.batchRemove(ids);
	}

	@Override
	public List<ProjectDO> getAll() {
		return projectDao.getAll();
	}

}
