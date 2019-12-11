package com.bootdo.oa.service;

import com.bootdo.oa.domain.ProjectPersonDO;

import java.util.List;
import java.util.Map;

/**
 * 项目人员信息表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-11 10:02:11
 */
public interface ProjectPersonService {
	
	ProjectPersonDO get(Integer id);
	
	List<ProjectPersonDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProjectPersonDO projectPerson);
	
	int update(ProjectPersonDO projectPerson);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
