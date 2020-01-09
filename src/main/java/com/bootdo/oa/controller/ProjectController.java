package com.bootdo.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bootdo.oa.domain.XmzDO;
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

import com.bootdo.oa.domain.AttendanceDO;
import com.bootdo.oa.domain.ProjectDO;
import com.bootdo.oa.service.AttendanceService;
import com.bootdo.oa.service.ProjectService;
import com.google.common.collect.Iterators;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;

/**
 * 项目信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-15 15:50:56
 */
 
@Controller
@RequestMapping("/oa/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	@Autowired
    private DictService dictService;
	@Autowired
	private AttendanceService attendanceService;
	
	
	@GetMapping()
	@RequiresPermissions("oa:project:project")
	String Project(){
	    return "oa/project/project";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:project:project")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ProjectDO> projectList = projectService.list(query);
		int total = projectService.count(query);
		PageUtils pageUtils = new PageUtils(projectList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:project:add")
	String add(Model model){
	    ProjectDO project = new ProjectDO();
	    
	    List<DictDO> dictDOS = dictService.listByType("project_status");
	    
	    model.addAttribute("projectStatus", dictDOS);
        model.addAttribute("project", project);
	    return "oa/project/edit";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:project:edit")
	String edit(@PathVariable("id") String id,Model model){
		ProjectDO project = projectService.get(id);
		
		List<DictDO> dictDOS = dictService.listByType("project_status");
		model.addAttribute("projectStatus", dictDOS);
		model.addAttribute("project", project);
	    return "oa/project/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:project:add")
	public R save( ProjectDO project){
		if(projectService.save(project)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:project:edit")
	public R update( ProjectDO project){
	    if(project!=null&&StringUtils.isEmpty(project.getId())) {
	        projectService.save(project);
	    }else {
	        projectService.update(project);
	    }
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:project:remove")
	public R remove( String id){
		if(projectService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:project:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		projectService.batchRemove(ids);
		return R.ok();
	}
	
	/**
     * 获取项目有效工作量 和 实际工作量
     */
	@GetMapping("/getWorkInfo/{projectId}")
    public String getWorkInfo(@PathVariable("projectId") String projectId,Model model){
        
        Map<String, Object> returnMap = new HashMap<String, Object>();
        ProjectDO projectDO = projectService.get(projectId);
        returnMap.put("cWorkNum", projectDO.getCWorkNum());
        returnMap.put("zWorkNum", projectDO.getZWorkNum());
        returnMap.put("gWorkNum", projectDO.getGWorkNum());
        returnMap.put("workNum", projectDO.getWorkNum());
        
        returnMap.put("cWorkLoad", 0.00);
        returnMap.put("zWorkLoad", 0.00);
        returnMap.put("gWorkLoad", 0.00);
        returnMap.put("workLoad", 0.00);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", projectId);
        List<AttendanceDO> attendanceDOList = attendanceService.list(map);
        attendanceDOList.stream().forEach(attendanceDO ->{
            Double manMouth = Double.parseDouble(attendanceDO.getManMouth());
            if("初级工程师".equals(attendanceDO.getItemRole())) {
                returnMap.put("cWorkLoad", (Double)returnMap.get("cWorkLoad")+manMouth);
            }else if("工程师".equals(attendanceDO.getItemRole())||"中级工程师".equals(attendanceDO.getItemRole())) {
                returnMap.put("zWorkLoad",(Double)returnMap.get("zWorkLoad")+manMouth);            
            }else if("高级工程师".equals(attendanceDO.getItemRole())) {
                returnMap.put("gWorkLoad", (Double)returnMap.get("gWorkLoad")+manMouth); 
            }
        });
        returnMap.put("workLoad", (Double)returnMap.get("cWorkLoad")+(Double)returnMap.get("zWorkLoad")+(Double)returnMap.get("gWorkLoad")); 
        model.addAttribute("work", returnMap);
        return "oa/project/project_work";
    }
	
	
	/**
     * 获取有效考勤信息
     */
    @GetMapping("/getAttendanceInfo/{projectId}")
    public String getAttendanceInfo(@PathVariable("projectId") String projectId,Model model){
        
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
        ProjectDO projectDO = projectService.get(projectId);
        returnMap.put("cWorkNum", projectDO.getCWorkNum());
        returnMap.put("zWorkNum", projectDO.getZWorkNum());
        returnMap.put("gWorkNum", projectDO.getGWorkNum());
        returnMap.put("workNum", projectDO.getWorkNum());
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectId", projectId);
        List<AttendanceDO> attendanceDOList = attendanceService.list(map);
        
        model.addAttribute("work", returnMap);
        return "oa/project/project_attendance";
    }

	@ResponseBody
	@GetMapping("/getAll")
	public List<ProjectDO> list(){
		//所有项目信息
		return projectService.getAll();
	}
    
}
