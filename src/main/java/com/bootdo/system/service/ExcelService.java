package com.bootdo.system.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ExcelService {
    /**
     * 读取excel中的数据,生成list
     */
    List<String> readExcelFile(MultipartFile[] files) throws Exception;
}
