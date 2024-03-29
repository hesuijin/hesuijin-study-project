package com.example.demo.excel;

import com.example.demo.excel.xssWorkBookNew.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
//https://blog.csdn.net/weixin_43952697/article/details/107249935
//    https://blog.csdn.net/llllvvv/article/details/98945250
    @RestController()
    @RequestMapping("getExcel2003Test")
public class ExcelHSSFWorkbook2003Test {

    @RequestMapping("test")
    @ResponseBody
    public String getExcelUr() throws IOException {
        String fileDir  ="http://127.0.0.1/" + "V:/" +"哈哈哈"+"_xxx报表_"+".xlsx";
        getExcel(fileDir);
        return fileDir;
    }

    public  void getExcel(String fileDir) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("我是sheet名称");
        FileOutputStream fileOutputStream = null;
        fileDir = "V:/" +"HSSFWorkbook 2003导出"+".xlsx";

        try {
            File xlsFile =new File(fileDir);
            fileOutputStream = new FileOutputStream(xlsFile);
            getDetailExcel(sheet);
            workbook.write(fileOutputStream);
        }catch (Exception e){

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
//            65536可以不能导出  导出excel显示为空白 需要修复
            for (i = 0 ;i<65536;i++){
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
