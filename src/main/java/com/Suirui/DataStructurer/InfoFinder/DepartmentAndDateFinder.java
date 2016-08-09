package com.Suirui.DataStructurer.InfoFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DepartmentAndDateFinder {
	
	

	// read goverment.txt and postfix.txt
	public static void preFileReader() {
		try {
			File govermentInfo = new File("government.txt");
			File postfixInfo = new File("postfix.txt");
			File pagefootInfo = new File("pagefooter.txt");
			if (govermentInfo.exists() && postfixInfo.exists() && pagefootInfo.exists())
				;
			else {
				System.err.println("government.txt or postfix.txt or pagefooter.txt doesn't exist!");
				return;
			}
			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(govermentInfo), "utf-8"));

			String line;
			while ((line = input.readLine()) != null) {
				if (line.trim().equals(""))
					continue;
				ArrayList<String> tempLineList = new ArrayList<>();
				for (String atempString : line.split(" ")) {
					if (atempString.trim().equals(""))
						continue;
					tempLineList.add(atempString);
				}
				infoFinderExector.governmentInfoList.add(tempLineList);

			}
			input.close();
			BufferedReader input2 = new BufferedReader(
					new InputStreamReader(new FileInputStream(postfixInfo), "utf-8"));
			while ((line = input2.readLine()) != null) {
				if (line.trim().equals(""))
					continue;
				for (String atempString : line.split(" ")) {
					if (atempString.trim().equals(""))
						continue;
					infoFinderExector.postfixInfoList.add(atempString);
				}
			}
			input2.close();

			BufferedReader input3 = new BufferedReader(
					new InputStreamReader(new FileInputStream(pagefootInfo), "utf-8"));
			while ((line = input3.readLine()) != null) {
				if (line.trim().equals(""))
					continue;
				String[] aStringList = line.split("。|；|，| ");
				for (String tempString : aStringList) {
					if (tempString.trim() == "")
						continue;
					infoFinderExector.pagefooter.add(tempString);

				}
			}
			input3.close();

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/*
	 * Core code to find the department info
	 * 
	 * @Param filePath Root-filepath not dirpath!
	 */
	public static Map<String, String> infoFinder(String filePath) {
		if(infoFinderExector.governmentInfoList==null||infoFinderExector.postfixInfoList==null||infoFinderExector.pagefooter==null)
		preFileReader();
		int[] garde = { 0, 0, 0 };
		Map<Date, Integer> timeResultMap = new HashMap<>();
		Map<String, Integer> departmentResultMap = new HashMap<>();
		// TODO have to improve the efficiency
		try {
			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			// match level-1 goverment.txt + postfix.txt
			String line;
			while ((line = input.readLine()) != null) {
				String[] aStringList = line.split("。|；|，| ");
				for (String tempString : aStringList) {
					if (infoFinderExector.pagefooter.contains(tempString))
						continue;

					// add department info to department-result map
					List<String> resultofDfinder = departmentStringFinder(tempString);
					for (String tempResultString : resultofDfinder) {

						if (!departmentResultMap.containsKey(tempResultString))
							departmentResultMap.put(tempResultString, 1);
						else {
							departmentResultMap.put(tempResultString, 1 + departmentResultMap.get(tempResultString));
						}
					}

					// add time info to time-result map
					List<Date> resultofTimefinder = DateFinder.dateFinder(tempString);
					for (Date tempResultString : resultofTimefinder) {

						if (!timeResultMap.containsKey(tempResultString))
							timeResultMap.put(tempResultString, 1);
						else {
							timeResultMap.put(tempResultString, 1 + timeResultMap.get(tempResultString));
						}
					}

				}
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		Map<String, String> InfoFinderResult = new HashMap<>();
		int MaxVal = 0;
		String MaxKey = "";
		Date Creattime=null, Deadline=null, Max = null;
		for (Entry<String, Integer> entry : departmentResultMap.entrySet()) {
			if (entry.getValue() > MaxVal) {
				MaxVal = entry.getValue();
				MaxKey = entry.getKey();
			}
		}
		MaxVal = 0;
		for (Entry<Date, Integer> entry : timeResultMap.entrySet()) {
			if (entry.getValue() > MaxVal) {
				MaxVal = entry.getValue();
				Max=entry.getKey();
			}
			if(Creattime==null){
				Creattime=entry.getKey();
			}
			if(Deadline==null){
				Deadline=entry.getKey();
				continue;
			}
			if(Creattime.after(entry.getKey()))Creattime=entry.getKey();
			if(!Deadline.after(entry.getKey()))Deadline=entry.getKey();
		}

		InfoFinderResult.put("Department", MaxKey);
		if(Creattime==null)InfoFinderResult.put("CreatDate",null);
		else InfoFinderResult.put("CreatDate", Creattime.toString());
		if(Deadline==null)InfoFinderResult.put("Deadline",null);
		else
		InfoFinderResult.put("Deadline", Deadline.toString());
		if(Max==null)InfoFinderResult.put("MaxDate",null);
		else
		InfoFinderResult.put("MaxDate", Max.toString());
		
		return InfoFinderResult;
	}

	public static List<String> departmentStringFinder(String line) {
		ArrayList<String> departmentResult = new ArrayList<>();
		int[] garde = { 0, 0, 0 };
		// match level-1 goverment.txt + postfix.txt

		for (int i = 0; i < infoFinderExector.governmentInfoList.size(); i++) {
			for (int j = 0; j < infoFinderExector.governmentInfoList.get(i).size(); j++) {
				String supGovString = infoFinderExector.governmentInfoList.get(i).get(0), govString = infoFinderExector.governmentInfoList.get(i).get(j);
				int tempFlag = 0;
				if (line.contains(govString))
					for (int k = 0; k < infoFinderExector.postfixInfoList.size(); k++) {
						String postfixString = infoFinderExector.postfixInfoList.get(k);
						if (line.contains(govString + postfixString)) {
							if (!departmentResult.contains(supGovString + " " + govString)) {
								departmentResult.add(supGovString + " " + govString);
								// System.out.println(line + '\n' + supGovString
								// + " " + govString + "-" + postfixString);
							}
							tempFlag = 1;
							garde[0]++;
							break;
						} else if (line.contains(govString) && line.contains(postfixString)) {
							if (!departmentResult.contains(supGovString + " " + govString))
								// departmentResult.add(supGovString + " " +
								// govString);
								garde[1]++;
							tempFlag = 1;
							continue;
						}

					}

			}
		}

		return departmentResult;
	}
	
	
	public static void main(String []args){
		Map<String, String>ans=infoFinder("test.txt");
		for (Entry<String, String> entry : ans.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		
	}
}
