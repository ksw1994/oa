package com.bootdo.testDemo;


import com.bootdo.common.excel.ExportExcelUtil;
import com.bootdo.common.utils.Base64Utils;
import com.bootdo.common.word.WordExportUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {


    @Test
    public void test() {

            ExportExcelUtil<Student> util = new ExportExcelUtil<Student>();
            // 准备数据
            List<Student> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(new Student(111,"张三asdf","男"));
                list.add(new Student(111,"李四asd","男"));
                list.add(new Student(111,"王五","女"));
            }
            String[] columnNames = { "ID", "姓名", "性别" };
            try {
                util.exportExcel("用户导出", columnNames, list, new FileOutputStream("/home/ksw/Desktop/test.xls"), ExportExcelUtil.EXCEL_FILE_2003);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    @Test
    public void test3() {

        String image = "/home/ksw/Pictures/desktop.jpg";
        String templateFilePath = "/home/ksw/Desktop/word/简历.ftl";
        String exportFilePath = "/home/ksw/Desktop/word/张三简历.doc";
        Map<String,Object> dataMap = new HashMap<>();
        int loadType = 1;
        dataMap.put("project_role","中级工程师");
        dataMap.put("name","张三");
        dataMap.put("age",25);
        dataMap.put("sex","男");
        dataMap.put("school","理工大学");
        dataMap.put("profession","软件工程");
        dataMap.put("seniority","5年");
        dataMap.put("bank_seniority","4年");
        dataMap.put("certificate","计算机资格证");
        dataMap.put("project_time","2015.07-2017.09");
        dataMap.put("project_name","中国银行手机银行项目");
        dataMap.put("work_content","负责中国银行手机银行项目客户端核心代码编写");
        dataMap.put("image", new Base64Utils().getImageStr(image));

        File file = null;
        try {
            file = new WordExportUtil().createDocFile(templateFilePath,dataMap,exportFilePath,loadType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(file.getName());
    }
}
