package com.bootdo.oa.dao;

import com.bootdo.oa.domain.ProjectPersonDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目人员信息表
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-11 10:02:11
 */
@Mapper
public interface ProjectPersonDao {

	ProjectPersonDO get(Integer id);
	//new
	List<ProjectPersonDO >projectList(Map<String,Object> map);
	int projectcount(Map<String,Object> map);
	
	List<ProjectPersonDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProjectPersonDO projectPerson);
	
	int update(ProjectPersonDO projectPerson);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	int removeByUserId(String userId );
	int batchRemoveByProjectId(Integer[] ids);
	int removeByProjectId(Integer projectId);
	ProjectPersonDO getProject(Integer projectId);
	int removeRepeat(String userId, Integer projectId);
	
}
