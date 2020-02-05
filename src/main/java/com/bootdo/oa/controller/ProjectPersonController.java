package com.bootdo.oa.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.oa.domain.JcxxDO;
import com.bootdo.oa.domain.ProjectPersonDO;
import com.bootdo.oa.service.JcxxService;
import com.bootdo.oa.service.ProjectPersonService;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 项目人员信息表
 * 
 * @author hwg
 * @email 18819123386@163.com
 * @date 2019-12-11 10:02:11
 */
 
@Controller
@RequestMapping("/oa/projectPerson")
public class ProjectPersonController {
	@Autowired
	private ProjectPersonService projectPersonService;
	
	@Autowired
	private JcxxService jcxxService;
	
	@GetMapping()
	@RequiresPermissions("oa:projectPerson:projectPerson")
	String ProjectPerson(){
	    return "oa/projectPerson/projectPerson";
	}
	
	//分项目查看人员
	@ResponseBody
	@GetMapping("/personlist")
	@RequiresPermissions("oa:projectPerson:personlist")
	public PageUtils personlist(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProjectPersonDO> projectPersonList = projectPersonService.list(query);
		int total = projectPersonService.count(query);
		PageUtils pageUtils = new PageUtils(projectPersonList, total);
		return pageUtils;
	}
	
	/**
	 * 改动
	 * 添加项目页-再到人员list
	 * 
	 * 
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:projectPerson:projectPerson")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询项目统计人员列表数据
        Query query = new Query(params);
		List<ProjectPersonDO> projectPersonList = projectPersonService.projectList(query);
		int total = projectPersonService.projectcount(query);
		PageUtils pageUtils = new PageUtils(projectPersonList, total);
		return pageUtils;
	}
	
	//userTree
	
	@GetMapping("/tree")
	@ResponseBody
	public Tree<MenuDO> tree(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<JcxxDO> jcxxList = jcxxService.list(query);
		Tree<MenuDO>  tree = projectPersonService.getTree(query,jcxxList);
		return tree;
	}
	
	
	
	//--
	@GetMapping("/add")
	@RequiresPermissions("oa:projectPerson:add")
	String add(){
	    return "oa/projectPerson/add";
	}
	
	@GetMapping("/addUserId")
	@RequiresPermissions("oa:projectPerson:add")
	String addUserId(){
	    return "oa/projectPerson/addUserId";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:projectPerson:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ProjectPersonDO projectPerson = projectPersonService.get(id);
		model.addAttribute("projectPerson", projectPerson);
	    return "oa/projectPerson/edit";
	}
	
	@GetMapping("/personAdd/{projectId}")
	@RequiresPermissions("oa:projectPerson:personAdd")
	String personAdd(@PathVariable("projectId") Integer projectId,Model model){
		ProjectPersonDO projectPersonAdd = projectPersonService.getProject(projectId);
		model.addAttribute("projectPersonAdd", projectPersonAdd);
	    return "oa/projectPerson/personAdd";
	}
	
	/**
	 * 保存
	 * 同时删除选取trees的最高节点id：“-1”
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:projectPerson:add")
	public R save( ProjectPersonDO projectPerson){
		if(projectPersonService.save(projectPerson)>0){
			projectPersonService.removeByUserId("-1");
			return R.ok();
		}else if(projectPersonService.save(projectPerson)<0) {
			return R.error("错误，请选择正确项目id");
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:projectPerson:edit")
	public R update( ProjectPersonDO projectPerson){
		projectPersonService.update(projectPerson);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:projectPerson:remove")
	public R remove(Integer projectId){
		if(projectPersonService.removeByProjectId(projectId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 * 批量删除byProjectId
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:projectPerson:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
			if(projectPersonService.batchRemoveByProjectId(ids)>0){
			return R.ok();
			}
			return R.error();
	}
	
	/**
	 * 删除person
	 */
	@PostMapping( "/personRemove")
	@ResponseBody
	@RequiresPermissions("oa:projectPerson:personRemove")
	public R personRemove(Integer id){
		if(projectPersonService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 * 批量删除person
	 */
	@PostMapping( "/batchPersonRemove")
	@ResponseBody
	@RequiresPermissions("oa:projectPerson:batchPersonRemove")
	public R personRemove(@RequestParam("ids[]") Integer[] ids){
			if(projectPersonService.batchRemove(ids)>0){
			return R.ok();
			}
			return R.error();
	}
}
