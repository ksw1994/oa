package com.bootdo.oa.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.oa.domain.OvertimeDO;
import com.bootdo.oa.service.OvertimeService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 打卡时间表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-12-03 10:34:37
 */
 
@Controller
@RequestMapping("/oa/overtime")
public class OvertimeController {
	@Autowired
	private OvertimeService overtimeService;
	
	@GetMapping()
	@RequiresPermissions("oa:overtime:overtime")
	String Overtime(){
	    return "oa/overtime/overtime";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:overtime:overtime")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OvertimeDO> overtimeList = overtimeService.list(query);
		int total = overtimeService.count(query);
		PageUtils pageUtils = new PageUtils(overtimeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:overtime:add")
	String add(){
	    return "oa/overtime/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:overtime:edit")
	String edit(@PathVariable("id") String id,Model model){
		OvertimeDO overtime = overtimeService.get(id);
		model.addAttribute("overtime", overtime);
	    return "oa/overtime/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:overtime:add")
	public R save( OvertimeDO overtime){
		if(overtimeService.save(overtime)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:overtime:edit")
	public R update( OvertimeDO overtime){
		overtimeService.update(overtime);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:overtime:remove")
	public R remove( String id){
		if(overtimeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:overtime:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		overtimeService.batchRemove(ids);
		return R.ok();
	}

	//导入excel
	@RequestMapping(value = "/importExcel", method= RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("oa:overtime:importExcel")
	public R importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		overtimeService.importExcelFile(file);
		return R.ok();
	}

	//单文件导出加班数据
	@GetMapping(value = "/exportOvertime",produces="application/json")
	@ResponseBody
	//@RequiresPermissions("oa:overtime:exportOvertime")
	public R download(@RequestParam("date")String date,@RequestParam("deptName")String deptName,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jcxxId : 要导出的简历id
		File file = overtimeService.exportOvertime(date,deptName);
		if (file == null){
			return R.error("没有加班数据");
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
		return R.ok();
	}

	/**
	 * 获取所有部门
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getAllDept")
	public List<String> getAllDept(){
		return overtimeService.getAllDept();
	}
}
