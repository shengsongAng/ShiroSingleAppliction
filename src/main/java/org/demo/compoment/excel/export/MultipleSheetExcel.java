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
public class MultipleSheetExcel {
    /**
     * 导出多个sheet的Excel
     * 没有格式
     */
    @RequestMapping("/multipleSheetExcel")
    public void multipleSheetExcel(HttpServletResponse response){
        try{
            //一个空的Excel
            Workbook book = new HSSFWorkbook();
            //标题栏
            String[] titleArr = {"ID","用户名","姓名","性别","年龄","电话"};
            //模拟数据库获取数据
            List<UserInfo> dataList1 = ExcelData.getDataList(0,5);
            List<UserInfo> dataList2 = ExcelData.getDataList(5,10);

            //第一个sheet
            List<Map<String,String>> mList1 = new ArrayList<>();
            for(UserInfo u:dataList1){
                Map<String,String> map = new HashMap<>();
                map.put("col0",u.getId());
                map.put("col1",u.getUsername());
                map.put("col2",u.getFullname());
                map.put("col3",u.getSex());
                map.put("col4",String.valueOf(u.getAge()));
                map.put("col5",u.getPhone());
                mList1.add(map);
            }
            this.createExcel(book,"sheet1",0,titleArr,mList1);

            //第二个sheet
            List<Map<String,String>> mList2 = new ArrayList<>();
            for(UserInfo u:dataList2){
                Map<String,String> map = new HashMap<>();
                map.put("col0",u.getId());
                map.put("col1",u.getUsername());
                map.put("col2",u.getFullname());
                map.put("col3",u.getSex());
                map.put("col4",String.valueOf(u.getAge()));
                map.put("col5",u.getPhone());
                mList2.add(map);
            }
            this.createExcel(book,"sheet2",1,titleArr,mList2);

            //文件标题
            String xlsFile_name = "多个Sheet的Excel.xlsx";
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
     * @param book          Excel对象
     * @param sheetName     sheet名称
     * @param sheetNum      sheet位置
     * @param titleArr      标题数组
     * @param list          数据列表
     */
    public void createExcel(Workbook book, String sheetName, int sheetNum, String[] titleArr, List<Map<String,String>> list){
        //创建一个sheet
        Sheet sheet = book.createSheet();
        //指定sheet的标题和位置
        book.setSheetName(sheetNum,sheetName);
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
