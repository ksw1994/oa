package com.bootdo.oa.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.bootdo.oa.domain.AttendanceDO;
import com.bootdo.oa.excel.AttendanceExcel;


/**
 * @program: agentServer
 * @description:
 * @author: Conten
 * @create: 2019-02-01 10:55
 **/
public class AttendanceConvertor {

    public static AttendanceDO convertToDO(AttendanceExcel attendanceExcel) {
        AttendanceDO attendanceDO = new AttendanceDO();
        BeanUtils.copyProperties(attendanceExcel,attendanceDO);
        return attendanceDO;
    }

}
