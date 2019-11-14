package com.bootdo.oa.controller;


import java.util.List;
import java.util.Map;

import com.bootdo.oa.domain.JcxxDO;
import com.bootdo.oa.service.JcxxService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;



/**
 * 简历基础信息表 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-11 10:29:06
 */
 
@Controller
@RequestMapping("/oa/jcxx")
public class JcxxController {
	@Autowired
	private JcxxService jcxxService;
	
	@GetMapping()
	@RequiresPermissions("oa:jcxx:jcxx")
	String Jcxx(){
	    return "oa/jcxx/jcxx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:jcxx:jcxx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<JcxxDO> jcxxList = jcxxService.list(query);
		// int total = jcxxService.count(query);
		int total = jcxxList.size();
		PageUtils pageUtils = new PageUtils(jcxxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:jcxx:add")
	String add(){
	    return "oa/jcxx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:jcxx:edit")
	String edit(@PathVariable("id") String id,Model model){
		JcxxDO jcxx = jcxxService.get(id);
		model.addAttribute("jcxx", jcxx);
	    return "oa/jcxx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:jcxx:add")
	public R save( JcxxDO jcxx){
		if(jcxxService.save(jcxx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:jcxx:edit")
	public R update( JcxxDO jcxx){
		jcxxService.update(jcxx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:jcxx:remove")
	public R remove( String id){
		if(jcxxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:jcxx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		jcxxService.batchRemove(ids);
		return R.ok();
	}

	//导入excel
	@RequestMapping(value = "/importExcel", method= RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("oa:jcxx:importExcel")
	public R importExcel(@RequestParam("file")MultipartFile file, HttpServletRequest request){
		jcxxService.importExcelExcelFile(file);
		return R.ok();
	}

}
