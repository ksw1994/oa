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
import java.util.List;


@Service
public class ExcelServiceImpl implements ExcelService {

    @Value("${bootdo.uploadPath}")
    private String uploadPath;

    @Override
    public String readExcelFile(MultipartFile file) throws Exception {
        String result = "";
        //创建处理EXCEL的类
        //List<List<String>> list = ExcelUtil.readExcel(file,0);
        //解析excel，获取上传的事件单
        //List<Map<String, Object>> userList = readExcel.getExcelInfo(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,

        String path = FileUtil.uploadFile(file.getBytes(), uploadPath, FileUtil.renameToUUID(file.getOriginalFilename()));

        if(!StringUtil.isNullOrEmpty(path)){
            result = "上传成功";
        }else{
            result = "上传失败";
        }
        return result;
    }
}
