package com.example.demo.excel;

import com.example.demo.excel.xssWorkBookNew.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/8/6
 */
//https://blog.csdn.net/qq_31615049/article/details/82228812

//https://blog.csdn.net/weixin_43952697/article/details/107249935
//    https://blog.csdn.net/llllvvv/article/details/98945250
    @RestController()
    @Slf4j
    @RequestMapping("getExcel2007SXSSFWorkbookTest")
public class ExcelSXSSFWorkbook2007Test {

    @RequestMapping("test")
    @ResponseBody
    public String getExcelUr() throws IOException {
        String fileDir  ="http://127.0.0.1/" + "V:/" +"哈哈哈"+"_xxx报表_"+".xlsx";
        getExcel(fileDir);
        return fileDir;
    }

    public  void getExcel(String fileDir) throws IOException {

        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        Sheet sheet = workbook.createSheet("我是sheet名称");
        FileOutputStream fileOutputStream = null;
        fileDir = "V:/" +"SXSSFWorkbook 2007导出"+".xlsx";

        try {
            File xlsFile =new File(fileDir);
            fileOutputStream = new FileOutputStream(xlsFile);
            getDetailExcel(sheet);
            workbook.write(fileOutputStream);
        }catch (Exception e){
           log.error(e.getMessage());
        }finally {
            if (fileOutputStream!=null){
                fileOutputStream.close();
            }
        }
    }

    private static void getDetailExcel(Sheet sheet){
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("hsj");
        student1.setAge(18);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Hsj");
        student2.setAge(20);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        String string = "id,name,age";
        String[] strings = string.split(",");
        Row row;
        row = sheet.createRow(0);

        for (int j = 0; j < strings.length; j++) {
            row.createCell(j).setCellValue(strings[j]);
        }

        int i = 0;
        Student dto =  studentList.get(0);
        //开始创建行和表格并且加入数据
        if (null != studentList && !studentList.isEmpty()) {

//            65535可以导出
//            65536可以导出
//            655370也能导出

//        1048575（一个sheet可以）
//        1048576（一个sheet 不行）

            for (i = 0 ;i<1048577;i++){
//              for (i = 0 ;i<655370;i++){
//            for (Student dto : studentList) {
//                i += 1;
                //创建行
                row = sheet.createRow(i);
                //列1
                if (null != dto.getId()) {
                    row.createCell(0).setCellValue(dto.getId());
                } else {
                    row.createCell(0).setCellValue("");
                }
                //列2
                if (null != dto.getName()) {
                    row.createCell(1).setCellValue(dto.getName());
                } else {
                    row.createCell(1).setCellValue("");
                }
                //列3
                if (null != dto.getAge()) {
                    row.createCell(2).setCellValue(dto.getAge());
                } else {
                    row.createCell(2).setCellValue("");
                }
            }
        }

    }
}
