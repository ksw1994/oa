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

import com.bootdo.oa.domain.XmjlDO;
import com.bootdo.oa.service.XmjlService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 简历项目经历表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:56
 */
 
@Controller
@RequestMapping("/oa/xmjl")
public class XmjlController {
	@Autowired
	private XmjlService xmjlService;
	
	@GetMapping()
	@RequiresPermissions("oa:xmjl:xmjl")
	String Xmjl(){
	    return "oa/xmjl/xmjl";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:xmjl:xmjl")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XmjlDO> xmjlList = xmjlService.list(query);
		int total = xmjlService.count(query);
		PageUtils pageUtils = new PageUtils(xmjlList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:xmjl:add")
	String add(){
	    return "oa/xmjl/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:xmjl:edit")
	String edit(@PathVariable("id") String id,Model model){
		XmjlDO xmjl = xmjlService.get(id);
		model.addAttribute("xmjl", xmjl);
	    return "oa/xmjl/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:xmjl:add")
	public R save( XmjlDO xmjl){
		if(xmjlService.save(xmjl)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:xmjl:edit")
	public R update( XmjlDO xmjl){
		xmjlService.update(xmjl);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:xmjl:remove")
	public R remove( String id){
		if(xmjlService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:xmjl:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		xmjlService.batchRemove(ids);
		return R.ok();
	}
	
}
