package com.bootdo.common.excel;



import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.poi.excel.ExcelWriter;
import com.xiaoleilu.hutool.util.StrUtil;
import org.activiti.rest.editor.model.ModelEditorJsonRestResource;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

    public static List<List<String>> readExcel(MultipartFile file,int sheet){
        String postfix = getPostfix(file.getOriginalFilename());
        List<List<String>> list = new ArrayList<List<String>>();
        // IO流读取文件
        InputStream input = null;
        Workbook wb = null;
        List<String> rowList = null;
        try {
            input = file.getInputStream();
            if(OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
                POIFSFileSystem fs  =new POIFSFileSystem(input);
                wb = new HSSFWorkbook(fs);
            }else if(OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){
                wb = new XSSFWorkbook(input);
            }else{
                return null;
            }

            Sheet xssfSheet = wb.getSheetAt(sheet);  //读取sheet(页)
            int totalRows = xssfSheet.getLastRowNum();

            for(int rowNum = 1;rowNum <=totalRows;rowNum++){ //读取Row,从第二行开始
                Row titileRow = xssfSheet.getRow(0);	//读取标题，第一行
                Row row = xssfSheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                rowList = new ArrayList<String>();
                short totalCells = titileRow.getLastCellNum();
                //读取列，从第一列开始
                for(int c=0;c<totalCells;c++){
                    Cell cell = row.getCell(c);
                    if(cell==null){
                        rowList.add(EMPTY);
                        continue;
                    }
                    rowList.add(getValue(cell).trim());
                }
                list.add(rowList);
            }
            return list;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * read the Excel 2010 .xlsx
     * @param file
     * @param
     * @param
     * @return
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static List<List<String>> readExcel(File file){
        String postfix = getPostfix(file.getName());
        List<List<String>> list = new ArrayList<List<String>>();
        // IO流读取文件
        InputStream input = null;
        Workbook wb = null;
        List<String> rowList = null;
        try {
            input = new FileInputStream(file);
            if(OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
                POIFSFileSystem fs  =new POIFSFileSystem(input);
                wb = new HSSFWorkbook(fs);
            }else if(OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){
                wb = new XSSFWorkbook(input);
            }else{
                return null;
            }

            Sheet xssfSheet = wb.getSheetAt(0);  //读取sheet(页)
            int totalRows = xssfSheet.getLastRowNum();
            for(int rowNum = 0;rowNum <= totalRows;rowNum++){ //读取Row,从第二行开始
                Row row = xssfSheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                rowList = new ArrayList<String>();
                short totalCells = row.getLastCellNum();
                //读取列，从第一列开始
                for(int c=0;c<=totalCells+1;c++){
                    Cell cell = row.getCell(c);
                    if(cell==null){
                        rowList.add(EMPTY);
                        continue;
                    }
                    rowList.add(getValue(cell).trim());
                }
                list.add(rowList);
            }
            return list;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 单元格格式
     * @param hssfCell
     * @return
     */
    public static String getValue(Cell hssfCell){
        if (CellType.BOOLEAN.equals(hssfCell.getCellTypeEnum())) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (CellType.NUMERIC.equals(hssfCell.getCellTypeEnum())) {
            String cellValue = "";
            if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
                Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                cellValue = sdf.format(date);
            }else{
                DecimalFormat df = new DecimalFormat("#.##");
                cellValue = df.format(hssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
                if(strArr.equals("00")){
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else if (CellType.FORMULA.equals(hssfCell.getCellTypeEnum())) {
            // add this for reading formula cell throws exception. . .
            return hssfCell.getCellFormula();
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    /**
     * 获得path的后缀名
     * @param path
     * @return
     */
    public static String getPostfix(String path){
        if(path==null || EMPTY.equals(path.trim())){
            return EMPTY;
        }
        if(path.contains(POINT)){
            return path.substring(path.lastIndexOf(POINT)+1,path.length()).toLowerCase();
        }
        return EMPTY;
    }

    /**
     * 上传处理失败的Excel数据保存为Excel文件
     *
     * @param errorMapList 处理失败的Excel数据
     * @param errorMsg     要提示的错误消息
     * @return 上传成功后的Excel文件路径
     */
    protected String uploadErrorExcelData(List<Map<String, Object>> errorMapList, String errorMsg) throws Exception {
        ExcelWriter writer = new ExcelWriter();
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        String fileUrl = null;
        try {
            /*
             * 以下代码是保存到文件服务器
             */
            // 去掉边框
            CellStyle styleSet = writer.getCellStyle();
           // styleSet.setBorder(BorderStyle.NONE, IndexedColors.BLACK);
            // 错误消息
           /* if (StrUtil.isNotBlank(errorMsg)) {
                writer.write(CollUtil.asIterable(errorMsg));
            }*/
            // 设置标题
            /*if (CollUtil.isNotEmpty(getTitle())) {
                writer.write(getTitle());
            }*/
            // 处理失败的数据 Map List
            writer.write(errorMapList);
            out = new ByteArrayOutputStream();
            // out为OutputStream，需要写出到的目标流
            writer.flush(out);
            in = new ByteArrayInputStream(out.toByteArray());
            //fileUrl = FdfsFileUtil.uploadFileStream("error.xls", in);
            /*
             * 以上代码是保存到文件服务器
             */
            /*
             * 以下代码是保存到本地，仅供本地测试使用
             */
//             writer = ExcelUtil.getWriter("D:\\新建文件夹333\\" + System.currentTimeMillis() + "_error.xlsx");
//             // 去掉边框
//             StyleSet styleSet = writer.getStyleSet();
//             styleSet.setBorder(BorderStyle.NONE, IndexedColors.BLACK);
//             // 错误消息
//             if (StrUtil.isNotBlank(errorMsg)) {
//                 writer.write(CollUtil.toList(errorMsg));
//             }
//             // 设置标题
//             if (CollUtil.isNotEmpty(getTitle())) {
//                 writer.write(getTitle());
//             }
//             // 处理失败的数据 Map List
//             writer.write(errorMapList);
//             writer.flush();
            /*
             * 以上代码是保存到本地，仅供本地测试使用
             */
        } finally {
           /* IoUtil.close(writer);
            IoUtil.close(out);
            IoUtil.close(in);*/
        }
        return fileUrl;
    }



}
