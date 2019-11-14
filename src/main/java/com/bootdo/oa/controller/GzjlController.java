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

import com.bootdo.oa.domain.GzjlDO;
import com.bootdo.oa.service.GzjlService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 简历工作经历表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:56
 */
 
@Controller
@RequestMapping("/oa/gzjl")
public class GzjlController {
	@Autowired
	private GzjlService gzjlService;
	
	@GetMapping()
	@RequiresPermissions("oa:gzjl:gzjl")
	String Gzjl(){
	    return "oa/gzjl/gzjl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:gzjl:gzjl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GzjlDO> gzjlList = gzjlService.list(query);
		int total = gzjlService.count(query);
		PageUtils pageUtils = new PageUtils(gzjlList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:gzjl:add")
	String add(){
	    return "oa/gzjl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:gzjl:edit")
	String edit(@PathVariable("id") String id,Model model){
		GzjlDO gzjl = gzjlService.get(id);
		model.addAttribute("gzjl", gzjl);
	    return "oa/gzjl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:gzjl:add")
	public R save( GzjlDO gzjl){
		if(gzjlService.save(gzjl)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:gzjl:edit")
	public R update( GzjlDO gzjl){
		gzjlService.update(gzjl);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:gzjl:remove")
	public R remove( String id){
		if(gzjlService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:gzjl:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		gzjlService.batchRemove(ids);
		return R.ok();
	}
	
}
