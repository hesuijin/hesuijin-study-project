package com.example.demo.excel;

import com.example.demo.excel.xssWorkBookNew.Student;
import org.apache.commons.collections4.ListUtils;
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
@RestController
@RequestMapping("getExcel1")
public class Excel2007Other {

    @RequestMapping("test")
    @ResponseBody
    public String getExcelUr() throws IOException {
        String fileDir = "http://127.0.0.1/" + "V:/" + "哈哈哈" + "_xxx报表_" + ".xlsx";
        getExcel(fileDir);
        return fileDir;
    }

    public void getExcel(String fileDir) throws IOException {
        //避免内存溢出
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook, 1000);


        FileOutputStream fileOutputStream = null;

        fileDir = "V:/" + "哈哈哈" + "_xxx报表_" + ".xlsx";

        try {
            File xlsFile = new File(fileDir);
            fileOutputStream = new FileOutputStream(xlsFile);
            getDetailExcel(workbook);
            workbook.write(fileOutputStream);
        } catch (Exception e) {

        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    private static void getDetailExcel(SXSSFWorkbook workbook) {


        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("hsj");
        student1.setAge(10);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Hsj");
        student2.setAge(15);

        Student student3 = new Student();
        student3.setId(3L);
        student3.setName("Hsj");
        student3.setAge(20);

        Student student4 = new Student();
        student4.setId(4L);
        student4.setName("Hsj");
        student4.setAge(25);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        String string = "id,name,age";
        String[] strings = string.split(",");


        List<List<Student>> studentLists = ListUtils.partition(studentList, 2);

        for (int k = 0; k < studentLists.size(); k++) {
            Sheet sheet = workbook.createSheet("我是sheet名称"+k);
            Row row;
            row = sheet.createRow(0);

            List<Student> studentListTemp = studentLists.get(k);

            for (int j = 0; j < strings.length; j++) {
                row.createCell(j).setCellValue(strings[j]);
            }

            int i = 0;
            //开始创建行和表格并且加入数据
            if (null != studentListTemp && !studentListTemp.isEmpty()) {
                for (Student dto : studentListTemp) {
                    i += 1;
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
}
