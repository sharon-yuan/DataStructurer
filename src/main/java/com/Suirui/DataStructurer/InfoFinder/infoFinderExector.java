package com.Suirui.DataStructurer.InfoFinder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.Suirui.DataStructurer.SQL.SQLInfoReader;
import com.Suirui.DataStructurer.SQL.SQLInfoWriter;
import com.Suirui.DataStructurer.SQL.SQLbasic;
import com.Suirui.DataStructurer.Util.FileIO;

public class infoFinderExector {
	
	
public static final Connection conn = SQLbasic.getConn();
	public static void main(String []args){
		
		//try {
		//	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("InfoFinderResult.txt")),"utf-8"));
		 ArrayList<String> cityList = SQLInfoReader.LocationsReader();
		 try {
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("CityInfo.txt")),"utf-8"));
		for(String cityLine:cityList){
			output.write(cityLine+'\n');
		}
		output.close();
		 } catch (UnsupportedEncodingException e) {
		
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 cityList=null;	
 		String fileDir="E:/data/china/textsQ34/";
 		
		File Dir=new File(fileDir);
		File[] dirList=Dir.listFiles();
		int countF=0;
		for(File tempF:dirList){
			System.gc();
			System.out.println("countF: "+countF++);
		
		/*Map<String, String>InfoResult=new HashMap<>();
		Map<String, String>DepartAndDate=null;
		DepartAndDate=DepartmentAndDateFinder.infoFinder(tempF.getPath());
		if(DepartAndDate==null) {InfoResult=null;continue;}
		Map<String, String>Location=new HashMap<>();
		String locationString="";
		locationString=CityFinder.cityFinder(tempF.getPath());
		Location.put("Location",locationString);	
		locationString=null;
		InfoResult.putAll(DepartAndDate);
		InfoResult.putAll(Location);
		DepartAndDate=null;Location=null;
		
		ArrayList<String> tempLineString=new ArrayList<>();
		tempLineString.add(tempF.getName());
		for (Entry<String, String> entry : InfoResult.entrySet()) {
			//output.write(entry.getValue()+" ");
			tempLineString.add(entry.getValue());
			
		}
		InfoResult=null;
		tempLineString.add(tempF.getPath());*/
			ArrayList<String> tempLineString=new ArrayList<>();
			tempLineString.add(tempF.getName());
			/*System.out.println(fileContentList.get(0));
			System.out.println(DepartmentAndDateFinder.departmentStringFinder(fileContentList.get(0)));
			System.out.println(DateFinder.dateFinder(fileContentList.get(1)));
			String []resultCity=CityFinder.forTitile(fileContentList);
			for(String tempccitu:resultCity)
				System.out.println(tempccitu);*/
			ArrayList<String> fileContentList=FileIO.getLinesArray(tempF.getAbsolutePath());
			tempLineString.add(fileContentList.get(0));
			List<String> templist=DepartmentAndDateFinder.departmentStringFinder(fileContentList.get(0));
			if(templist==null||templist.size()==0)
			tempLineString.add(null);
			else{
				System.out.println(templist.size());
				tempLineString.add(templist.get(0));
				}
			ArrayList<Date> tempdate=DateFinder.dateFinder(fileContentList.get(1));
			if(tempdate==null||tempdate.size()==0)
				tempLineString.add(null);
			else
			tempLineString.add(DateFinder.dateFinder(fileContentList.get(1)).get(0).toString());
			
			String []resultCity=CityFinder.forTitile(fileContentList);
			for(String tempccitu:resultCity)
				tempLineString.add(tempccitu);
		for(String tempString:tempLineString)
			System.out.println("info--"+tempString);
		
		SQLInfoWriter.SQLStringListWriter("Q34Info", tempLineString);
		//tempLineString=null;
		
		//output.write(tempF.getPath()+'\n');
		}
		//output.close();
		/*} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}*/
	}

}
