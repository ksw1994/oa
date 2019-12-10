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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.oa.domain.WeekScopeDO;
import com.bootdo.oa.service.WeekScopeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 周末、节假日范围表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-03 16:38:38
 */
 
@Controller
@RequestMapping("/oa/weekScope")
public class WeekScopeController {
	@Autowired
	private WeekScopeService weekScopeService;
	
	@GetMapping()
	@RequiresPermissions("oa:weekScope:weekScope")
	String WeekScope(){
	    return "oa/weekScope/weekScope";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:weekScope:weekScope")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WeekScopeDO> weekScopeList = weekScopeService.list(query);
		int total = weekScopeService.count(query);
		PageUtils pageUtils = new PageUtils(weekScopeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:weekScope:add")
	String add(){
	    return "oa/weekScope/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:weekScope:edit")
	String edit(@PathVariable("id") String id,Model model){
		WeekScopeDO weekScope = weekScopeService.get(id);
		model.addAttribute("weekScope", weekScope);
	    return "oa/weekScope/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:weekScope:add")
	public R save(@RequestParam("dateList")String dateList){
		if(weekScopeService.save(dateList)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:weekScope:edit")
	public R update( WeekScopeDO weekScope){
		weekScopeService.update(weekScope);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:weekScope:remove")
	public R remove( String id){
		if(weekScopeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:weekScope:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		weekScopeService.batchRemove(ids);
		return R.ok();
	}
	
}
