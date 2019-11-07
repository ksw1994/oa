package com.bootdo.common.excel;



import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

    public static List<List<String>> readExcel(MultipartFile file){
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

            Sheet xssfSheet = wb.getSheetAt(0);  //读取sheet(页)
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

        } finally{
            try {
                input.close();
            } catch (IOException e) {

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

        } finally{
            try {
                input.close();
            } catch (IOException e) {

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
}
