package com.bootdo.system.controller;

import com.bootdo.system.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/excel")
class ExcelController {

    @Autowired(required=true)
    private ExcelService excelService;

    //导入excel
    @RequestMapping(value = "/import", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importExcel(@RequestParam(value="files",required = false) MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> result = excelService.readExcelFile(files);
        map.put("url",result);
        return map;
    }
}
