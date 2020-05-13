package com.bootdo.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.exception.BizExceptionEnum;
import com.bootdo.common.exception.BusinessException;
import com.bootdo.common.utils.ExcelUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.oa.convertor.AttendanceConvertor;
import com.bootdo.oa.domain.AttendanceDO;
import com.bootdo.oa.domain.JcxxDO;
import com.bootdo.oa.excel.AttendanceExcel;
import com.bootdo.oa.service.AttendanceService;
import com.bootdo.oa.service.JcxxService;
import com.bootdo.system.service.UserService;

/**
 * 考勤明细表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-11-19 11:08:46
 */
 
@Controller
@RequestMapping("/oa/attendance")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private JcxxService jcxxService;
	
	@GetMapping()
	@RequiresPermissions("oa:attendance:attendance")
	String Attendance(){
	    return "oa/attendance/attendance";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:attendance:attendance")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AttendanceDO> attendanceList = attendanceService.list(query);
		int total = attendanceService.count(query);
		PageUtils pageUtils = new PageUtils(attendanceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("oa:attendance:add")
	String add(){
	    return "oa/attendance/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:attendance:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AttendanceDO attendance = attendanceService.get(id);
		model.addAttribute("attendance", attendance);
	    return "oa/attendance/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:attendance:add")
	public R save( AttendanceDO attendance){
		if(attendanceService.save(attendance)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:attendance:edit")
	public R update( AttendanceDO attendance){
		attendanceService.update(attendance);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:attendance:remove")
	public R remove( Integer id){
		if(attendanceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:attendance:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		attendanceService.batchRemove(ids);
		return R.ok();
	}
	
	/**
     * 文件上传 caopeilun
     * 
     * @param file
     * @return
     */
	@PostMapping("/upload")
    @ResponseBody
    @RequiresPermissions("oa:attendance:attendance")
    public R uploadExcel(@RequestParam("uploadFile") MultipartFile file,@RequestParam("projectId") Integer projectId) {

       try {
            List<AttendanceExcel> attendanceExcelList = ExcelUtil.importExcel(file, 0, 1, AttendanceExcel.class);
            
            // 删除该项目下考勤信息
            Map<String,Object> deleteCondition = new HashMap<String, Object>();
            deleteCondition.put("projectId",projectId);
            attendanceService.deleteByCondition(deleteCondition);
            
            if (attendanceExcelList == null || attendanceExcelList.size() <= 0) {
                //校验Excel信息不能为空  为空返回提示信息
                return R.error(BizExceptionEnum.FILE_EXCEL_NULL_ERROR.getMessage());
            } else {
                //StringBuilder returnMessage = new StringBuilder();
                //List<AttendanceDO> attendanceDOList = new ArrayList<>();
                for (AttendanceExcel attendanceExcel : attendanceExcelList) {
                    JcxxDO jcxxDO = new JcxxDO();
                    //判断身份证号是否为空 若不存在则跳过
                    if(attendanceExcel!=null && StringUtils.isNotBlank(attendanceExcel.getIdCard())) {
                        jcxxDO = jcxxService.getByCardId(attendanceExcel.getIdCard());
                    }else {
                        continue;
                    }
                    AttendanceDO attendanceDO = AttendanceConvertor.convertToDO(attendanceExcel);
                    attendanceDO.setProjectId(projectId);
                    //attendanceDOList.add(attendanceDO);
                    //attendanceService.save(attendanceDO);
                    attendanceService.saveOrUpdate(attendanceDO);
                }
              /*  resultList.addAll(workersDetailService.addWorkersDetailList(workersDetailAddReqList));*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessException){
                BusinessException businessException = (BusinessException) e;
                return R.error(businessException.getCode(),businessException.getMessage());
            }
        }
        return R.ok();
    }
	
}
