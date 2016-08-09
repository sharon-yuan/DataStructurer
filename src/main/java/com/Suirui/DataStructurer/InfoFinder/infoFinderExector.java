package com.Suirui.DataStructurer.InfoFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class infoFinderExector {
	public static ArrayList<ArrayList<String>> governmentInfoList = new ArrayList<ArrayList<String>>();
	public static ArrayList<String> postfixInfoList = new ArrayList<>();
	public static ArrayList<String> pagefooter = new ArrayList<>();

	public static void main(String []args){
		String filePath="test.txt";
		DepartmentAndDateFinder.preFileReader();
		Map<String, String>InfoResult=new HashMap<>();
		Map<String, String>DepartAndDate=DepartmentAndDateFinder.infoFinder(filePath);
		Map<String, String>Location=new HashMap<>();
		CityFinder.cityFinder(filePath);
		
		Location.put("Location",CityFinder.cityFinder(filePath));
		InfoResult.putAll(DepartAndDate);
		InfoResult.putAll(Location);
		for (Entry<String, String> entry : InfoResult.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
		
	}

}
