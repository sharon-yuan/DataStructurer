package com.Suirui.DataStructurer.InfoFinder;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class infoFinderExector {
	public static ArrayList<ArrayList<String>> governmentInfoList = new ArrayList<ArrayList<String>>();
	public static ArrayList<String> postfixInfoList = new ArrayList<>();
	public static ArrayList<String> pagefooter = new ArrayList<>();

	public static void main(String []args){
		DepartmentAndDateFinder.preFileReader();
		Map<String, String>DepartAndDate=DepartmentAndDateFinder.infoFinder("test.txt");
		for (Entry<String, String> entry : DepartAndDate.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
		
	}

}
