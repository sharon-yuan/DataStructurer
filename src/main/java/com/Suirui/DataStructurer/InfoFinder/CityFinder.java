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
	public static void main(String[] args) {
		cityFinder("test.txt");
	}

	public static String cityFinder(String filePath) {
		ArrayList<String> cityList = SQLInfoReader.LocationsReader();
		Map<String, Integer> resultCountyMap = new HashMap();
		Map<String, Integer> resultCityMap = new HashMap();

		try {
			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			String line;
			String tempCityNameForLoop="";
			while ((line = input.readLine()) != null) {
				
			
				for(String splitLine:line.split(" ")){
				
				for (String atempCountyString : cityList) {
					if (!atempCountyString.contains(" "))
						continue;
					
					if (splitLine.contains(atempCountyString.split(" ")[0])) {
						if (atempCountyString.split(" ")[0].trim().equals("")
								|| atempCountyString.split(" ")[0] == null)
							continue;
						int freq = 1;
						if (resultCountyMap.containsKey(atempCountyString)) {
							freq = 1 + resultCountyMap.get(atempCountyString);
						}
						resultCountyMap.put(atempCountyString, freq);
					}
					if(!tempCityNameForLoop.equals(atempCountyString.split(" ")[1])){
						
						tempCityNameForLoop=atempCountyString.split(" ")[1];
					if (splitLine.contains(tempCityNameForLoop)) {
						
						if (atempCountyString.split(" ")[0].trim().equals("")
								|| atempCountyString.split(" ")[0] == null)
							continue;
						String tempKey = atempCountyString.split(" ")[1] + " " + atempCountyString.split(" ")[2];
						int freq = 1;
						if (resultCityMap.containsKey(tempKey))
							freq = resultCityMap.get(tempKey) + 1;

						resultCityMap.put(tempKey, freq);
					}}
				}}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for (Entry<String, Integer> entryCity : resultCityMap.entrySet()) {
			System.out.println(entryCity.getKey()+"  "+entryCity.getValue());
		}
		String resultString = null;
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
		}
		int maxVal=0;
		for (Entry<String, Integer> entryCity : resultCityMap.entrySet()) {
			if(entryCity.getValue()>maxVal){resultString=entryCity.getKey();maxVal=entryCity.getValue();}
			
		}
System.out.println("CityFinder result: "+resultString+" "+maxVal);
		return resultString;
	}

}
