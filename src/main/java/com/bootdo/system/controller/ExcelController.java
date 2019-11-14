package com.bootdo.system.controller;

import com.bootdo.common.word.WordExport;
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
import java.io.IOException;
import java.util.ArrayList;
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
    public Map<String, Object> importExcel(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String result = excelService.readExcelFile(file);
        map.put("message", result);
        return map;
    }
}
