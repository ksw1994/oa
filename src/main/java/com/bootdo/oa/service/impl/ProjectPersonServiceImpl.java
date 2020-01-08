package com.bootdo.oa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.oa.dao.JcxxDao;
import com.bootdo.oa.dao.ProjectPersonDao;
import com.bootdo.oa.domain.JcxxDO;
import com.bootdo.oa.domain.ProjectPersonDO;
import com.bootdo.oa.service.ProjectPersonService;
import com.bootdo.system.domain.MenuDO;



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
	
	@Override//
	public int save(ProjectPersonDO projectPerson){
		List<String> userIds = projectPerson.getUserIds();
		int rows = 0;
		for (String userId : userIds) {
			projectPerson.setUserId(userId);
			int row = projectPersonDao.save(projectPerson);
			rows=row+rows;
		}
		
		return rows;
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

	@Override
	public List<ProjectPersonDO> projectList() {
		// TODO Auto-generated method stub
		return projectPersonDao.projectList();
	}

	@Override
	public int projectcount() {
		// TODO Auto-generated method stub
		return projectPersonDao.projectcount();
	}
	//jcxx插入到tree里
	@Override
	public Tree<MenuDO> getTree(Map<String, Object> map,List<JcxxDO> jcxxDOs) {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		//JcxxServiceImpl jcxxServiceImpl = new JcxxServiceImpl();//空指针
		//List<JcxxDO> jcxxDOs =jcxxServiceImpl.list(map);//空指针
		for (JcxxDO jcxxDO : jcxxDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(jcxxDO.getId());
			tree.setParentId("0");//先固定每个人为顶级菜单
			tree.setText(jcxxDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("selected", false);
			tree.setState(state);
			trees.add(tree);
		}
	System.out.println("trees:"+trees);
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override//每次删除trees的最高节点id：-1
	public int removeByUserId(String userId) {
		return projectPersonDao.removeByUserId(userId);	
	}

	@Override//根据项目Id来多选删除
	public int batchRemoveByProjectId(Integer[] ids) {
		return projectPersonDao.batchRemoveByProjectId(ids);
	}

	@Override
	public int removeByProjectId(Integer projectId) {
		return projectPersonDao.removeByProjectId(projectId);
	}
	
}
