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

import com.bootdo.oa.domain.ProjectPersonDO;
import com.bootdo.oa.service.ProjectPersonService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 项目人员信息表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-11 10:02:11
 */
 
@Controller
@RequestMapping("/oa/projectPerson")
public class ProjectPersonController {
	@Autowired
	private ProjectPersonService projectPersonService;
	
	@GetMapping()
	@RequiresPermissions("oa:projectPerson:projectPerson")
	String ProjectPerson(){
	    return "oa/projectPerson/projectPerson";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:projectPerson:projectPerson")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProjectPersonDO> projectPersonList = projectPersonService.list(query);
		int total = projectPersonService.count(query);
		PageUtils pageUtils = new PageUtils(projectPersonList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:projectPerson:add")
	String add(){
	    return "oa/projectPerson/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:projectPerson:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ProjectPersonDO projectPerson = projectPersonService.get(id);
		model.addAttribute("projectPerson", projectPerson);
	    return "oa/projectPerson/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:projectPerson:add")
	public R save( ProjectPersonDO projectPerson){
		if(projectPersonService.save(projectPerson)>0){
			return R.ok();
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
	public R remove( Integer id){
		if(projectPersonService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:projectPerson:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		projectPersonService.batchRemove(ids);
		return R.ok();
	}
	
}
