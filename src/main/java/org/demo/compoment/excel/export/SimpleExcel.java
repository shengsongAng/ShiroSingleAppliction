package org.demo.compoment.excel.export;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.demo.compoment.excel.ExcelData;
import org.demo.compoment.excel.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SimpleExcel {

    /**
     * 简单的导出
     * 一个sheet，且没有格式
     * @param response
     */
    @RequestMapping("/simpleExcel")
    public void simpleExcel(HttpServletResponse response){
        try{
            //一个空的Excel
            Workbook book = new HSSFWorkbook();
            //标题栏
            String[] titleArr = {"ID","用户名","姓名","性别","年龄","电话"};
            //模拟数据库获取数据
            List<UserInfo> dataList = ExcelData.getDataList(0,10);
            //存放处理过后的数据
            List<Map<String,String>> mList = new ArrayList<>();
            for(UserInfo u:dataList){
                Map<String,String> map = new HashMap<>();
                map.put("col0",u.getId());
                map.put("col1",u.getUsername());
                map.put("col2",u.getFullname());
                map.put("col3",u.getSex());
                map.put("col4",String.valueOf(u.getAge()));
                map.put("col5",u.getPhone());
                mList.add(map);
            }

            //往excel写内容
            this.createExcel(book,titleArr,mList);

            //文件标题
            String xlsFile_name = "一个简单的Excel.xlsx";
            //中文乱码
            xlsFile_name = new String(xlsFile_name.getBytes(), "ISO-8859-1");

            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + xlsFile_name);
            response.flushBuffer();

            //写出
            OutputStream outputStream = response.getOutputStream();
            book.write(outputStream);

            //关闭资源
            book.close();
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param book
     * @param titleArr
     * @param list
     */
    public void createExcel(Workbook book, String[] titleArr, List<Map<String,String>> list){
        Sheet sheet = book.createSheet("sheet1");//创建一个sheet1的sheet
        //标题行
        Row row = sheet.createRow(0);
        for(int i=0; i<titleArr.length; i++){
            Cell cell = row.createCell(i);//创建列
            cell.setCellValue(titleArr[i]);
        }

        //数据行
        for(int i=0; i<list.size(); i++){
            Row row1 = sheet.createRow(i+1);
            //列的值
            Map<String,String> map = list.get(i);
            for(int j=0; j<map.size(); j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(map.get("col"+j));
            }
        }
    }
}
