package com.bootdo.system.service.impl;

import com.bootdo.common.utils.Base64Utils;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.word.WordExportUtil;
import com.bootdo.system.service.DownloadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Value("${bootdo.templatePath}")
    private String templatePath;

    @Value("${bootdo.exportPath}")
    private String exportPath;

    @Override
    public File downloadResume(String resumeId) {
        String image1 = "/home/ksw/Desktop/word/图片1.png";
        String image2 = "/home/ksw/Desktop/word/图片2.png";
        String image3 = "/home/ksw/Desktop/word/图片3.png";
        String image4 = "/home/ksw/Desktop/word/图片4.png";

        String templateFilePath = templatePath+"简历.ftl";//ftl模板路径
        Map<String,Object> dataMap = new HashMap<>();
        StringBuffer certificate = new StringBuffer();
        certificate.append("1.精通Java开发语言 \r\n");
        certificate.append("2.精通node.JS、Webpack结合前端开发 \r\n");
        certificate.append("3.熟悉Struts、hibernate、SpringMVC、Spring、Hibernate、myBatis、 iBatis、Junit等MVC框架 \r\n");
        certificate.append("4.熟悉WEB开发相关技术（JSP、HTML、H5、css、javascript、jquery、vue） \r\n");
        certificate.append("5.熟悉系统、网络、存储及相关软件等 \r\n");

        List<Map<String,String>> projectList = new ArrayList<>();
        Map<String,String> m1 = new HashMap<>();
        m1.put("projectTime","2015.07-2017.09");
        m1.put("projectRoleName","初级工程师");
        m1.put("projectName","中国银行手机银行项目");
        m1.put("workContent","负责中国银行手机银行项目客户端核心代码编写");
        projectList.add(m1);

        Map<String,String> m2 = new HashMap<>();
        m2.put("projectTime","2017.10-2018.12");
        m2.put("projectRoleName","初级工程师");
        m2.put("projectName","【中国银行】住房公积金项目");
        m2.put("workContent","负责住房公积金项目后端租房租赁模块的开发");
        projectList.add(m2);

        Map<String,String> m3 = new HashMap<>();
        m3.put("projectTime","2019.01-至今");
        m3.put("projectRoleName","中级工程师");
        m3.put("projectName","中国银行】住房公积金推广项目");
        m3.put("workContent","负责中行住房公积金推广项目后端租房租赁模块的开发");
        projectList.add(m3);

        Map<String,String> m4 = new HashMap<>();
        m4.put("projectTime","2019.01-至今");
        m4.put("projectRoleName","高级工程师");
        m4.put("projectName","中国银行】住房公积金推广项目");
        m4.put("workContent","负责中行住房公积金推广项目后端租房租赁模块的开发");
        projectList.add(m4);

        dataMap.put("project_role","中级工程师");
        dataMap.put("name","张三");
        dataMap.put("age",25);
        dataMap.put("sex","男");
        dataMap.put("school","理工大学");
        dataMap.put("profession","软件工程");
        dataMap.put("seniority","5年");
        dataMap.put("bank_seniority","4年");
        dataMap.put("certificate",certificate.toString().replaceAll("\r\n", "<w:p></w:p>"));
        dataMap.put("projectList",projectList);
        dataMap.put("image1", new Base64Utils().getImageStr(image1));
        dataMap.put("image2", new Base64Utils().getImageStr(image2));
        dataMap.put("image3", new Base64Utils().getImageStr(image3));
        dataMap.put("image4", new Base64Utils().getImageStr(image4));
        String exportFilePath = exportPath+ DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN)+"张三简历.doc";//导出路径
        int loadType = 1;
        File file = null;
        try {

            file = new WordExportUtil().createDocFile(templateFilePath,dataMap,exportFilePath,loadType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


}

