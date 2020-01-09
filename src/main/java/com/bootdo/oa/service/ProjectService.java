package com.bootdo.oa.service;

import com.bootdo.oa.domain.ProjectDO;

import java.util.List;
import java.util.Map;

/**
 * 项目信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-15 15:50:56
 */
public interface ProjectService {
	
	ProjectDO get(String id);
	
	List<ProjectDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProjectDO project);
	
	int update(ProjectDO project);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<ProjectDO> getAll();
}
