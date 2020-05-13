package com.bootdo.oa.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.oa.domain.WeekScopeDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.oa.domain.WbRlppDO;
import com.bootdo.oa.service.WbRlppService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目人力匹配表
 * 
 * @author ksw
 * @email 18819123386@163.com
 * @date 2020-01-07 09:20:31
 */
 
@Controller
@RequestMapping("/oa/wbRlpp")
public class WbRlppController {
	@Autowired
	private WbRlppService wbRlppService;
	
	@GetMapping()
	@RequiresPermissions("oa:wbRlpp:wbRlpp")
	String WbRlpp(){
	    return "oa/wbRlpp/wbRlpp";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:wbRlpp:wbRlpp")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WbRlppDO> wbRlppList = wbRlppService.list(query);
		int total = wbRlppService.count(query);
		PageUtils pageUtils = new PageUtils(wbRlppList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:wbRlpp:add")
	String add(){
	    return "oa/wbRlpp/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:wbRlpp:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		WbRlppDO wbRlpp = wbRlppService.get(id);
		model.addAttribute("wbRlpp", wbRlpp);
	    return "oa/wbRlpp/edit";
	}
	
	/**
	 * 保存
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:wbRlpp:add")
	public R save( WbRlppDO wbRlpp){
		if(wbRlppService.save(wbRlpp)>0){
			return R.ok();
		}
		return R.error("外包人员数量不够！");
	}*/

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping (value = "/save",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
	@RequiresPermissions("oa:wbRlpp:add")
	public R save(@RequestBody String params){
		List<WbRlppDO> wbRlppList = JSONArray.parseArray(params, WbRlppDO.class);
		if(wbRlppService.saveList(wbRlppList)>0){
			return R.ok();
		}
		return R.error("外包人员数量不够！");
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:wbRlpp:edit")
	public R update( WbRlppDO wbRlpp){
		wbRlppService.update(wbRlpp);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:wbRlpp:remove")
	public R remove( Integer id){
		if(wbRlppService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:wbRlpp:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		wbRlppService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 获取该项目的第三方人员数
	 */
	@ResponseBody
	@GetMapping ("/getThirdCount")
	public Integer getThirdCount(Integer id){
		return wbRlppService.getThirdCount(id);
	}

	//单文件导出加班数据
	@GetMapping(value = "/exportExcle",produces="application/json")
	@ResponseBody
	public String download(@RequestParam("endDate")String endDate,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = wbRlppService.exportExcle(endDate);
		if (file == null){
			return "该截止年月没有数据";
		}
		if (file.exists()) {
			// 设置强制下载不打开
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download charset=UTF-8");
			//response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(),"utf-8"));
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			OutputStream outputStream = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				outputStream = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					outputStream.write(buffer, 0, i);
					i = bis.read(buffer);
				}
				outputStream.flush();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//删除被下载的本地文件
		file.delete();
		return null;
	}
}
