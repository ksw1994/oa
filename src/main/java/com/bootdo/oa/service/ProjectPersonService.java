package com.bootdo.oa.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.Query;
import com.bootdo.oa.domain.JcxxDO;
import com.bootdo.oa.domain.ProjectPersonDO;
import com.bootdo.system.domain.MenuDO;

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
	//new
	List<ProjectPersonDO >projectList(Map<String, Object> map);
	int projectcount(Map<String, Object> map);
	
	List<ProjectPersonDO> list(Map<String, Object> map);
	int count(Map<String, Object> map);
	
	int save(ProjectPersonDO projectPerson);
	
	int update(ProjectPersonDO projectPerson);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	Tree<MenuDO> getTree(Map<String, Object> map,List<JcxxDO> jcxxDOs);
	int removeByUserId(String userId);
	int batchRemoveByProjectId(Integer[] ids);
	int removeByProjectId(Integer projectId);
	ProjectPersonDO getProject(Integer projectId);
}
