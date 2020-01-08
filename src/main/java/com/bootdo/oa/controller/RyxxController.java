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

import com.bootdo.oa.domain.RyxxDO;
import com.bootdo.oa.service.RyxxService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 外包人员信息表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
 
@Controller
@RequestMapping("/oa/ryxx")
public class RyxxController {
	@Autowired
	private RyxxService ryxxService;
	
	@GetMapping()
	@RequiresPermissions("oa:ryxx:ryxx")
	String Ryxx(){
	    return "oa/ryxx/ryxx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:ryxx:ryxx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RyxxDO> ryxxList = ryxxService.list(query);
		int total = ryxxService.count(query);
		PageUtils pageUtils = new PageUtils(ryxxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:ryxx:add")
	String add(){
	    return "oa/ryxx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:ryxx:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		RyxxDO ryxx = ryxxService.get(id);
		model.addAttribute("ryxx", ryxx);
	    return "oa/ryxx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:ryxx:add")
	public R save( RyxxDO ryxx){
		if(ryxxService.save(ryxx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:ryxx:edit")
	public R update( RyxxDO ryxx){
		ryxxService.update(ryxx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:ryxx:remove")
	public R remove( Integer id){
		if(ryxxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:ryxx:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		ryxxService.batchRemove(ids);
		return R.ok();
	}
	
}
