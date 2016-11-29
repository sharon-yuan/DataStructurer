package com.Suirui.DataStructurer.InfoFinder;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
public class DepartmentAndDateFinder {

	// read goverment.txt and postfix.txt
	public static void preFileReader() {
		/*
		 * try { File govermentInfo = new File("government.txt"); File
		 * postfixInfo = new File("postfix.txt"); File pagefootInfo = new
		 * File("pagefooter.txt"); File companyInfo = new File("company.txt");
		 * if (govermentInfo.exists() && postfixInfo.exists() &&
		 * pagefootInfo.exists()) ; else { System.err.
		 * println("government.txt or postfix.txt or pagefooter.txt doesn't exist!"
		 * ); return; } String line;
		 * if(infoFinderExector.governmentInfoList==null){
		 * infoFinderExector.governmentInfoList=new ArrayList<>();
		 * BufferedReader input = new BufferedReader( new InputStreamReader(new
		 * FileInputStream(govermentInfo), "utf-8"));
		 * 
		 * 
		 * while ((line = input.readLine()) != null) { if
		 * (line.trim().equals("")) continue; ArrayList<String> tempLineList =
		 * new ArrayList<>(); for (String atempString : line.split(" ")) { if
		 * (atempString.trim().equals("")) continue;
		 * tempLineList.add(atempString); }
		 * infoFinderExector.governmentInfoList.add(tempLineList);
		 * 
		 * } input.close();} if( infoFinderExector.postfixInfoList==null){
		 * infoFinderExector.postfixInfoList=new ArrayList<>(); BufferedReader
		 * input2 = new BufferedReader( new InputStreamReader(new
		 * FileInputStream(postfixInfo), "utf-8")); while ((line =
		 * input2.readLine()) != null) { if (line.trim().equals("")) continue;
		 * for (String atempString : line.split(" ")) { if
		 * (atempString.trim().equals("")) continue;
		 * infoFinderExector.postfixInfoList.add(atempString); } }
		 * input2.close(); } if(infoFinderExector.pagefooter==null){
		 * infoFinderExector.pagefooter=new ArrayList<>(); BufferedReader input3
		 * = new BufferedReader( new InputStreamReader(new
		 * FileInputStream(pagefootInfo), "utf-8")); while ((line =
		 * input3.readLine()) != null) { if (line.trim().equals("")) continue;
		 * String[] aStringList = line.split("。|；|，| "); for (String tempString
		 * : aStringList) { if (tempString.trim() == "") continue;
		 * infoFinderExector.pagefooter.add(tempString);
		 * 
		 * } } input3.close(); } if(infoFinderExector.compInfoList==null){
		 * infoFinderExector.compInfoList=new ArrayList<>(); BufferedReader
		 * input4 = new BufferedReader( new InputStreamReader(new
		 * FileInputStream(companyInfo), "utf-8"));
		 * 
		 * 
		 * while ((line = input4.readLine()) != null) { if
		 * (line.trim().equals("")) continue; ArrayList<String> tempLineList =
		 * new ArrayList<>(); for (String atempString : line.split(" ")) { if
		 * (atempString.trim().equals("")) continue;
		 * tempLineList.add(atempString); }
		 * infoFinderExector.compInfoList.add(tempLineList);
		 * 
		 * } input4.close(); } } catch (UnsupportedEncodingException e) {
		 * 
		 * e.printStackTrace(); } catch (FileNotFoundException e) {
		 * 
		 * e.printStackTrace(); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); }
		 * 
		 */}

	/*
	 * Core code to find the department info
	 * 
	 * @Param filePath Root-filepath not dirpath!
	 */
	public static Map<String, String> infoFinder(String filePath) {
		List<ArrayList<String>> governmentInfoList = null, compInfoList = null;
		ArrayList<String> postfixInfoList = null, pagefooter = null;

		try {
			File govermentInfo = new File("government.txt");
			File postfixInfo = new File("postfix.txt");
			File pagefootInfo = new File("pagefooter.txt");
			File companyInfo = new File("company.txt");
			if (govermentInfo.exists() && postfixInfo.exists() && pagefootInfo.exists())
				;
			else {
				System.err.println("government.txt or postfix.txt or pagefooter.txt doesn't exist!");
				return null;
			}
			String line;
			if (governmentInfoList == null) {
				governmentInfoList = new ArrayList<>();
				BufferedReader input = new BufferedReader(
						new InputStreamReader(new FileInputStream(govermentInfo), "utf-8"));

				while ((line = input.readLine()) != null) {
					if (line.trim().equals(""))
						continue;
					ArrayList<String> tempLineList = new ArrayList<>();
					for (String atempString : line.split(" ")) {
						if (atempString.trim().equals(""))
							continue;
						tempLineList.add(atempString);
					}
					governmentInfoList.add(tempLineList);

				}
				input.close();
			}
			if (postfixInfoList == null) {
				postfixInfoList = new ArrayList<>();
				BufferedReader input2 = new BufferedReader(
						new InputStreamReader(new FileInputStream(postfixInfo), "utf-8"));
				while ((line = input2.readLine()) != null) {
					if (line.trim().equals(""))
						continue;
					for (String atempString : line.split(" ")) {
						if (atempString.trim().equals(""))
							continue;
						postfixInfoList.add(atempString);
					}
				}
				input2.close();
			}
			if (pagefooter == null) {
				pagefooter = new ArrayList<>();
				BufferedReader input3 = new BufferedReader(
						new InputStreamReader(new FileInputStream(pagefootInfo), "utf-8"));
				while ((line = input3.readLine()) != null) {
					if (line.trim().equals(""))
						continue;
					String[] aStringList = line.split("。|；|，| ");
					for (String tempString : aStringList) {
						if (tempString.trim() == "")
							continue;
						pagefooter.add(tempString);

					}
				}
				input3.close();
			}
			if (compInfoList == null) {
				compInfoList = new ArrayList<>();
				BufferedReader input4 = new BufferedReader(
						new InputStreamReader(new FileInputStream(companyInfo), "utf-8"));

				while ((line = input4.readLine()) != null) {
					if (line.trim().equals(""))
						continue;
					ArrayList<String> tempLineList = new ArrayList<>();
					for (String atempString : line.split(" ")) {
						if (atempString.trim().equals(""))
							continue;
						tempLineList.add(atempString);
					}
					compInfoList.add(tempLineList);

				}
				input4.close();
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		Map<String, String> InfoFinderResult = new HashMap<>();
		preFileReader();

		Map<Date, Integer> timeResultMap = new HashMap<>();
		Map<String, Integer> departmentResultMap = new HashMap<>();
		// TODO have to improve the efficiency
		try {

			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			// match level-1 goverment.txt + postfix.txt
			String line, urlString = null;
			while ((line = input.readLine()) != null) {
				if (urlString == null) {
					urlString = line;
					InfoFinderResult.put("URL", urlString);
				}
				int pageNumber = PagenumberGetter.dfgk(urlString);
				if (pageNumber != 0) {
					System.err.println("pageNumber:" + pageNumber
							//+ " URL:" + urlString
							);
					input.close();
					governmentInfoList = null;
					compInfoList = null;
					postfixInfoList = null;
					pagefooter = null;
					timeResultMap = null;
					departmentResultMap = null;
					InfoFinderResult = null;
					timeResultMap = null;
					departmentResultMap = null;
					
					return null;
				}

				String[] aStringList = line.split("。|；|，| ");
				for (String tempString : aStringList) {
					if (pagefooter.contains(tempString))
						continue;
					/*
					 * List<ArrayList<String>>
					 * governmentInfoList=null,compInfoList=null;
					 * ArrayList<String> postfixInfoList=null,pagefooter=null;
					 */
					// add department info to department-result map
					List<String> resultofDfinder = departmentStringFinder(tempString, governmentInfoList, compInfoList,
							postfixInfoList, pagefooter);
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
			input.close();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		int MaxVal = 0;
		String MaxKey = "null null";
		Date Creattime = null, Deadline = null, Max = null;
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
				Max = entry.getKey();
			}
			if (Creattime == null) {
				Creattime = entry.getKey();
			}
			if (Deadline == null) {
				Deadline = entry.getKey();
				continue;
			}
			if (Creattime.after(entry.getKey()))
				Creattime = entry.getKey();
			if (!Deadline.after(entry.getKey()))
				Deadline = entry.getKey();
		}

		InfoFinderResult.put("Department", MaxKey);
		if (Creattime == null)
			InfoFinderResult.put("CreatDate", null);
		else
			InfoFinderResult.put("CreatDate", Creattime.toString());
		if (Deadline == null)
			InfoFinderResult.put("Deadline", null);
		else
			InfoFinderResult.put("Deadline", Deadline.toString());
		if (Max == null)
			InfoFinderResult.put("MaxDate", null);
		else
			InfoFinderResult.put("MaxDate", Max.toString());

		governmentInfoList = null;
		compInfoList = null;
		postfixInfoList = null;
		pagefooter = null;
		timeResultMap = null;
		departmentResultMap = null;
		timeResultMap = null;
		departmentResultMap = null;
		return InfoFinderResult;
	}

	public static List<String> departmentStringFinder(String line, List<ArrayList<String>> governmentInfoList,
			List<ArrayList<String>> compInfoList, ArrayList<String> postfixInfoList, ArrayList<String> pagefooter) {
		ArrayList<String> departmentResult = new ArrayList<>();
		int[] garde = { 0, 0, 0 };
		// match level-1 goverment.txt + postfix.txt

		for (int i = 0; i < governmentInfoList.size(); i++) {
			for (int j = 0; j < governmentInfoList.get(i).size(); j++) {
				String supGovString = governmentInfoList.get(i).get(0), govString = governmentInfoList.get(i).get(j);
				@SuppressWarnings("unused")
				int tempFlag = 0;
				if (line.contains(govString))
					for (int k = 0; k < postfixInfoList.size(); k++) {
						String postfixString = postfixInfoList.get(k);
						if (line.contains(govString + postfixString)) {
							if (!departmentResult.contains(supGovString + " " + govString)) {
								departmentResult.add(supGovString + " " + govString);
								// System.out.println(line + '\n' + supGovString
								// + " " + govString + "-" + postfixString);
							}
							tempFlag = 1;
							garde[0]++;
							break;
						}

						else if (line.contains(govString) && line.contains(postfixString)) {
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
		if (departmentResult.size() == 0) {
			for (ArrayList<String> infoLine : compInfoList)
				for (String tempS : infoLine) {
					if (line.contains(tempS)) {
						if (departmentResult.contains(tempS))
							continue;
						else
							departmentResult.add(infoLine.get(0) + " " + tempS);
					}
				}
		}

		return departmentResult;
	}

	public static void main(String[] args) {
		Map<String, String> ans = infoFinder("test.txt");
		for (Entry<String, String> entry : ans.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}

	}
}
