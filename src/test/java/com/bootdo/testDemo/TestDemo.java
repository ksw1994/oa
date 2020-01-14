package com.bootdo.testDemo;


import com.bootdo.common.utils.Base64Utils;
import com.bootdo.common.utils.UUIDUtils;
import com.bootdo.common.word.WordExportUtil;
import com.bootdo.oa.service.RyxxService;
import com.bootdo.oa.service.WbRlppService;
import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.poi.excel.ExcelUtil;
import com.xiaoleilu.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {

    @Autowired
    private WbRlppService wbRlppService;

    @Test
    public void test() {

        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");
        System.out.println(row1.size());
        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

        //通过工具类创建writer
        String path = "/home/ksw/Desktop/writeTest.xlsx";
        ExcelWriter writer = ExcelUtil.getWriter(path);
        File file = new File(path);
        if(file.exists()) {
            file.delete();
        }
        //通过构造方法创建writer
        //ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");

        //跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();
        writer.passCurrentRow();

        //合并单元格后的标题行，使用默认标题样式
        Workbook workbook = writer.getWorkbook();
        //writer.merge(row1.size()-2, "测试标题");

        workbook.setSheetName(0, "这个是test");
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("women");
        sheet.addMergedRegionUnsafe(new CellRangeAddress(
                1, //first row (0-based)
                1, //last row  (0-based)
                1, //first column (0-based)
                2  //last column  (0-based)
        ));
        //给某个单元格设置边框
       /* Row row = sheet.getRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("333");
*/
        //Style the cell with borders all around.
        CellStyle style = workbook.createCellStyle();

        //style.setBorderBottom(BorderStyle.HAIR);
        //style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREEN.getIndex());

        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style);
        style.setAlignment(HorizontalAlignment.CENTER); // 居中
        Cell cell2 = row.createCell(2);
        cell2.setCellStyle(style);

        //一次性写出内容
        writer.write(rows);
        //关闭writer，释放内存
        writer.close();

        System.out.println("over");
    }




    @Test
    public void test3() {

        String id = UUIDUtils.randomUUID();

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

    @Test
    public void redisTest(){
        Jedis jedis = new Jedis("localhost",6379,100000);
        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true){
                long end = System.currentTimeMillis();
                if (end - start >= 1000){
                    break;
                }
                i++;
                jedis.set("test" + i,i+"");
            }
        }finally {
            jedis.close();
        }
        System.out.println("redis每秒写操作:"+i+"次");
    }

    @Test
    public void thirdTest(){
        int count = wbRlppService.getThirdCount(17);
        System.out.println(count);
    }
}
