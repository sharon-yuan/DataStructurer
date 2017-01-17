package com.Suirui.DataStructurer.Util;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.*;

public class ExcelIO {
	
	public static ArrayList<ArrayList<String>> readExcelData(String filePath) throws Exception{
		InputStream inp=new FileInputStream(new File(filePath));
        Workbook wb = WorkbookFactory.create(inp);
       
        //获取第一张表
        Sheet st = wb.getSheetAt(0);
 
      
        ArrayList<ArrayList<String>>result=new ArrayList<>();
        //遍历第一张表的所有行
        for(int i=0;i<=st.getLastRowNum();i++){
        	//第一行一般为title不读取
        ArrayList<String>rowList=new ArrayList<>();
        		Row row = st.getRow(i);	//获取第一行数据
        		//遍历第一行所有的列(单元格)
        		for(int j=0;j<row.getLastCellNum();j++){
        			Cell cell = row.getCell(j);  //获取第一个单元格
        			//获取单元格类型
        			rowList.add(cell.toString());
        		}
        	result.add(rowList);
        	
        }
        return result;
    }


}
