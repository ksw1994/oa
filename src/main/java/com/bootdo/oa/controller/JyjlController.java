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

import com.bootdo.oa.domain.JyjlDO;
import com.bootdo.oa.service.JyjlService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 简历教育经历表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:56
 */
 
@Controller
@RequestMapping("/oa/jyjl")
public class JyjlController {
	@Autowired
	private JyjlService jyjlService;
	
	@GetMapping()
	@RequiresPermissions("oa:jyjl:jyjl")
	String Jyjl(){
	    return "oa/jyjl/jyjl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:jyjl:jyjl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JyjlDO> jyjlList = jyjlService.list(query);
		int total = jyjlService.count(query);
		PageUtils pageUtils = new PageUtils(jyjlList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:jyjl:add")
	String add(){
	    return "oa/jyjl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:jyjl:edit")
	String edit(@PathVariable("id") String id,Model model){
		JyjlDO jyjl = jyjlService.get(id);
		model.addAttribute("jyjl", jyjl);
	    return "oa/jyjl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:jyjl:add")
	public R save( JyjlDO jyjl){
		if(jyjlService.save(jyjl)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:jyjl:edit")
	public R update( JyjlDO jyjl){
		jyjlService.update(jyjl);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:jyjl:remove")
	public R remove( String id){
		if(jyjlService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:jyjl:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		jyjlService.batchRemove(ids);
		return R.ok();
	}
	
}
