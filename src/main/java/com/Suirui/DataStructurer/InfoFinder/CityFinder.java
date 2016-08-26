package com.Suirui.DataStructurer.InfoFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.Suirui.DataStructurer.SQL.SQLInfoReader;

public class CityFinder {
public static void main(String []args){
	while(true)
	cityFinder("test.txt");
}
	public static String cityFinder(String filePath) {
		System.gc();
		ArrayList<String>cityList=new ArrayList<>();
		
		
		Map<String, Integer> resultCountyMap = new HashMap<String, Integer>();
		Map<String, Integer> resultCityMap = new HashMap<String, Integer>();
		String line;
		try {
			BufferedReader inputCityInfo = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File("CityInfo.txt")), "utf-8"));
			while((line=inputCityInfo.readLine())!=null)
				cityList.add(line);
			inputCityInfo.close();
			
			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			
			String tempCityNameForLoop="";
			while ((line = input.readLine()) != null) {
				String []linesplitArray=line.split(" ");
			
				for(String splitLine:linesplitArray){
				
				for (String atempCountyString : cityList) {
					if (!atempCountyString.contains(" "))
						continue;
					String[] tempStringArray=atempCountyString.split(" ");
					if (splitLine.contains(tempStringArray[0])) {
						if (tempStringArray[0].trim().equals("")
								|| tempStringArray[0] == null)
							continue;
						int freq = 1;
						if (resultCountyMap.containsKey(atempCountyString)) {
							freq = 1 + resultCountyMap.get(atempCountyString);
						}
						resultCountyMap.put(atempCountyString, freq);
					}
					if(!tempCityNameForLoop.equals(tempStringArray[1])){
						
						tempCityNameForLoop=tempStringArray[1];
					if (splitLine.contains(tempCityNameForLoop)) {
						
						if (tempStringArray[0].trim().equals("")
								|| tempStringArray[0] == null)
							continue;
						String tempKey = tempStringArray[1] + " " +tempStringArray[2];
						int freq = 1;
						if (resultCityMap.containsKey(tempKey))
							freq = resultCityMap.get(tempKey) + 1;

						resultCityMap.put(tempKey, freq);
						tempKey=null;
					}}
					tempStringArray=null;}
				}
				linesplitArray=null;}
			tempCityNameForLoop=null;
			input.close();
		} catch (UnsupportedEncodingException e) {
		
			e.printStackTrace();
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		String resultString = "null null";
		// 判断哪个地点可信度更高
		for (Entry<String, Integer> entryCounty : resultCountyMap.entrySet()) {
			boolean flag = false;
			entryCounty.getKey();
			entryCounty.getValue();
			for (Entry<String, Integer> entryCity : resultCityMap.entrySet()) {
				if (entryCity.getKey().split(" ")[0].equals(entryCounty.getKey().split(" ")[1])) {
					flag = true;
					
					resultCityMap.replace(entryCity.getKey(), entryCity.getValue() + 5 * entryCounty.getValue());
				}
				

			}
			if(!flag){
				resultCityMap.put(entryCounty.getKey().split(" ")[1]+" "+entryCounty.getKey().split(" ")[2], 1);
			}
		}
		int maxVal=0;
		for (Entry<String, Integer> entryCity : resultCityMap.entrySet()) {
			if(entryCity.getValue()>maxVal){resultString=entryCity.getKey();maxVal=entryCity.getValue();}
			
		}
		resultCountyMap=null;
		resultCityMap=null;
		cityList=null;
		line=null;
		return resultString;
	}

}
