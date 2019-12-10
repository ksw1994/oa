package com.bootdo.system.service.impl;

import com.bootdo.common.excel.ExcelUtil;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.system.service.ExcelService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExcelServiceImpl implements ExcelService {

    @Value("${bootdo.uploadPath}")
    private String uploadPath;

    @Override
    public List<String> readExcelFile(MultipartFile file) throws Exception {
        List<String> result = new ArrayList<>();
        List<List<String>> list1 = ExcelUtil.readExcel(file, 0);
        List<List<String>> list2 = ExcelUtil.readExcel(file, 3);
        return result;
    }
}
