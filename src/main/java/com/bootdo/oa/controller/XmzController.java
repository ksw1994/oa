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

import com.bootdo.oa.domain.XmzDO;
import com.bootdo.oa.service.XmzService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 外包人员所属项目组表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
 
@Controller
@RequestMapping("/oa/xmz")
public class XmzController {
	@Autowired
	private XmzService xmzService;
	
	@GetMapping()
	@RequiresPermissions("oa:xmz:xmz")
	String Xmz(){
	    return "oa/xmz/xmz";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:xmz:xmz")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XmzDO> xmzList = xmzService.list(query);
		int total = xmzService.count(query);
		PageUtils pageUtils = new PageUtils(xmzList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:xmz:add")
	String add(){
	    return "oa/xmz/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:xmz:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		XmzDO xmz = xmzService.get(id);
		model.addAttribute("xmz", xmz);
	    return "oa/xmz/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:xmz:add")
	public R save( XmzDO xmz){
		if(xmzService.save(xmz)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:xmz:edit")
	public R update( XmzDO xmz){
		xmzService.update(xmz);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:xmz:remove")
	public R remove( Integer id){
		if(xmzService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:xmz:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		xmzService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/getAll")
	public List<XmzDO> list(){
		//所有项目组信息
		return xmzService.getAll();
	}
}
