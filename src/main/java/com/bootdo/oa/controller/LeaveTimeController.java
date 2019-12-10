package com.bootdo.oa.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.oa.domain.LeaveTimeDO;
import com.bootdo.oa.service.LeaveTimeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 请假小时记录表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2019-12-05 10:30:30
 */
 
@Controller
@RequestMapping("/oa/leaveTime")
public class LeaveTimeController {
	@Autowired
	private LeaveTimeService leaveTimeService;
	
	@GetMapping()
	@RequiresPermissions("oa:leaveTime:leaveTime")
	String LeaveTime(){
	    return "oa/leaveTime/leaveTime";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:leaveTime:leaveTime")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LeaveTimeDO> leaveTimeList = leaveTimeService.list(query);
		int total = leaveTimeService.count(query);
		PageUtils pageUtils = new PageUtils(leaveTimeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:leaveTime:add")
	String add(){
	    return "oa/leaveTime/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:leaveTime:edit")
	String edit(@PathVariable("id") String id,Model model){
		LeaveTimeDO leaveTime = leaveTimeService.get(id);
		model.addAttribute("leaveTime", leaveTime);
	    return "oa/leaveTime/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:leaveTime:add")
	public R save( LeaveTimeDO leaveTime){
		if(leaveTimeService.save(leaveTime)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:leaveTime:edit")
	public R update( LeaveTimeDO leaveTime){
		leaveTimeService.update(leaveTime);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:leaveTime:remove")
	public R remove( String id){
		if(leaveTimeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:leaveTime:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		leaveTimeService.batchRemove(ids);
		return R.ok();
	}

	//导入excel
	@RequestMapping(value = "/importExcel", method= RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("oa:leaveTime:importExcel")
	public R importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		leaveTimeService.importExcelFile(file);
		return R.ok();
	}
	
}
