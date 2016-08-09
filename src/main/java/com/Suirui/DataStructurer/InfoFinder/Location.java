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
import java.util.List;
import java.util.Map;

import com.Suirui.DataStructurer.SQL.SQLInfoReader;

public class Location {
	static List<ArrayList<String[]>> Locations =null;
	public static Map<String, String> locationFinder(String filePath){
		

		String[] anStrings = new String[3];
		String content = "";
		if(Locations==null)
		 Locations = SQLInfoReader.CityReader();
		
		HashMap<String, String> cityName = new HashMap<String, String>();

		String tempLine;
		for (int i = 0; i < Locations.size(); i++) {

			for (int j = 0; j < Locations.get(i).size(); j++) {
				String key = Locations.get(i).get(j)[0], val = Locations.get(i).get(j)[1];
				if (!cityName.containsKey(key))
					cityName.put(key, val);
				else {
					String tempString = cityName.get(key);
					cityName.put(key, val + " " + tempString);
				}

			}
		}
		@SuppressWarnings("unused")
		HashMap<String, String> found = new HashMap<String, String>();
		String keyWord = null;
		
		try {
			@SuppressWarnings("resource")
			BufferedReader input3 = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath )), "utf-8"));
			int indexcont = 0;
			while ((tempLine = input3.readLine()) != null) {
				if(!tempLine.contains(" "))continue;
				if(tempLine.split(" ").length==0)continue;
				if ((indexcont++) <= 50) {

					content += tempLine.split(" ")[0] + " ";
				}
				
			}

			// anStrings[2] is the info about classify result
	
				anStrings[2] =0+"";// Classify.ClassInfo(filePath) + "";
			

			ArrayList<String> val = null;
			@SuppressWarnings("resource")
			BufferedReader input2 = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath )), "utf-8"));

			while ((tempLine = input2.readLine()) != null) {
if(!tempLine.contains(" "))continue;
if(tempLine.split(" ").length==0)continue;
				String key = tempLine.split(" ")[0];
				if (cityName.containsKey(key)) {
					if (keyWord == null) {
						keyWord = key;
						for (String atempString : cityName.get(key).split(" ")) {
							val = new ArrayList<String>();
							val.add(atempString);
						}
						if (val.size() == 1) {// 县或者市对应唯一super
							// System.out.println(key+" "+cityName.get(key));
							anStrings[0] = content;
							anStrings[1] = key + " " + cityName.get(key);
							return anStrings;
						}

					} else {
						for (String aTempString : val) {
							if (aTempString.equals(key))// super做为key出现
							{
								// System.out.println(keyWord+" "+key);
								anStrings[0] = content;
								anStrings[1] = keyWord + " " + key;
								return anStrings;

							}

						}
						for (String aTempString : cityName.get(key).split(" ")) {
							if (aTempString.equals(keyWord))// key的super为最高频地点
							{
								// System.out.println(key+" "+keyWord);
								anStrings[0] = content;
								anStrings[1] = key + " " + keyWord;
								return anStrings;

							}
						}
						anStrings[0] = content;
						anStrings[1] = keyWord;
						return anStrings;

					}

				}
			}
			input2.close();
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
		anStrings[0] = content;
		anStrings[1] = keyWord;
		return anStrings;

	
		return null;
	}

}
