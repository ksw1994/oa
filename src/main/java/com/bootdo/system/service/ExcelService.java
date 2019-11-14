package com.bootdo.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {
    /**
     * 读取excel中的数据,生成list
     */
    String readExcelFile(MultipartFile file) throws Exception;
}
