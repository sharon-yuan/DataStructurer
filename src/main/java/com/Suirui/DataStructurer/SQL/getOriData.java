package com.Suirui.DataStructurer.SQL;

import java.util.ArrayList;

public class getOriData {
	/**
	 * 
	 * @param args
	 */
public static void main(String args){
	getDataFromMysql();
	
}

/**
 * read data from Crawler.grade
 * @param type
 * @param val
 */
public static void getDataFromMysql(){
	ArrayList<String> attriList=new ArrayList<>();
	attriList.add("fileContent");
	
	SQLInfoReader.SQLinfoSaver("grade", attriList, "E:/data/china/gradeResult/", "UID");
}
}
