package com.bootdo.oa.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.oa.domain.FjDO;
import com.bootdo.oa.service.FjService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 简历附件表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-12 16:54:55
 */
 
@Controller
@RequestMapping("/oa/fj")
public class FjController {
	@Autowired
	private FjService fjService;
	
	@GetMapping()
	@RequiresPermissions("oa:fj:fj")
	String Fj(){
	    return "oa/fj/fj";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:fj:fj")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FjDO> fjList = fjService.list(query);
		int total = fjService.count(query);
		PageUtils pageUtils = new PageUtils(fjList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:fj:add")
	String add(){
	    return "oa/fj/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:fj:edit")
	String edit(@PathVariable("id") String id,Model model){
		FjDO fj = fjService.get(id);
		model.addAttribute("fj", fj);
	    return "oa/fj/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:fj:add")
	public R save( FjDO fj){
		if(fjService.save(fj)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:fj:edit")
	public R update( FjDO fj){
		fjService.update(fj);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:fj:remove")
	public R remove( String id){
		if(fjService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:fj:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		fjService.batchRemove(ids);
		return R.ok();
	}

	//多图片上传
	@RequestMapping(value = "/filesUpload", method= RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("oa:fj:filesUpload")
	public R filesUpload(@RequestParam(value = "jcxxId",required = false) String jcxxId,@RequestParam(value = "files",required = false) MultipartFile[]  files, HttpServletRequest request,HttpServletResponse response){
		fjService.filesUpload(files,jcxxId);
		return R.ok();
	}
	
}
