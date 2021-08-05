package com.example.demo.excel.xssWorkBookNew;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/8/5
 */
//https://blog.csdn.net/weixin_43952697/article/details/107249935?utm_term=%E8%A7%A3%E5%86%B3XSSFWorkbook%E5%AF%BC%E5%87%BAexcel%E9%97%AE%E9%A2%98&utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~sobaiduweb~default-2-107249935&spm=3001.4430
public class ExcelLast {
//
//    @PostMapping("/excelCarrierOrder")
//    public ResponseEntity<Resource> excelCarrierOrder(HttpServletResponse response) throws IOException {
//
//　　  //列表数据
//        XSSFWorkbook workbook = this.getExcel();
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        workbook.write(os);
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Expires", "0");
//        response.setHeader("charset", "utf-8");
//        // 当前日期，用于导出文件名称
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String name =sdf.format(new Date()) + ".xlsx";
//        response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(name, "UTF-8") + "\"");
//        workbook.write(response.getOutputStream());
//        if (null != os) {
//            os.close();
//        }
//        return new ResponseEntity<Resource>(null, null, HttpStatus.OK);
//    }

    public static void main(String[] args) throws IOException {
        getExcel();
    }

    public static void getExcel() throws IOException {

        String fileDir = System.getProperty("user.dir") + "/downLoad/" +"哈哈哈"+"_xxx报表_"+".xlsx";

        //模板路径
//        XSSFWorkbook workbook = new XSSFWorkbook();

        //避免内存溢出
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook , 1000);

        //sheet页（默认sheet1页），也可通过workboot.createSheet("订单列表")创建sheet页
//        XSSFSheet sheetAt = workbook.getSheetAt(0);
//        XSSFSheet sheetAt = workbook.createSheet("我是sheet名称");

        Sheet sheetAt = workbook.createSheet("我是sheet名称");


        FileOutputStream fileOutputStream = null;

        try {
            File xlsFile =new File(fileDir);
            fileOutputStream = new FileOutputStream(xlsFile);
            getDetailExcel(sheetAt,workbook);
            workbook.write(fileOutputStream);
        }catch (Exception e){

        }finally {
            fileOutputStream.close();
        }
    }

    private static void getDetailExcel(Sheet sheetAt,SXSSFWorkbook workbook){
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("hsj");
        student1.setAge(18);

        Student student2 = new Student();
        student2.setId(1L);
        student2.setName("hsj");
        student2.setAge(18);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        //XSSFRow row;
        Row row;
        int i = 0;
        //开始创建行和表格并且加入数据
        if (null != studentList && !studentList.isEmpty()) {
            for (Student dto : studentList) {
                i += 1;
                //创建行
                row = sheetAt.createRow(i);
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
