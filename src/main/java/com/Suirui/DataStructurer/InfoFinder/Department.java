package com.Suirui.DataStructurer.InfoFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Department {
	private static ArrayList<ArrayList<String>> governmentInfoList = new ArrayList<ArrayList<String>>();
	private static ArrayList<String> postfixInfoList = new ArrayList<>();
	private static ArrayList<String> pagefooter = new ArrayList<>();

	public static void main(String[] args) {
		preFileReader();
		List<String> arrayStringList = departmentFinder("test.txt");
		for (String a : arrayStringList)
			System.out.println(a);
	}

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
				governmentInfoList.add(tempLineList);

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
					postfixInfoList.add(atempString);
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
					pagefooter.add(tempString);
					//System.out.println(tempString);
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
	public static List<String> departmentFinder(String filePath) {
		int[] garde = { 0, 0, 0 };
		List<String> departmentResult = new ArrayList<>();
		// TODO have to improve the efficiency
		try {
			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			// match level-1 goverment.txt + postfix.txt
			String line;
			while ((line = input.readLine()) != null) {
				String[] aStringList = line.split("。|；|，| ");
				for (String tempString : aStringList){
					if(pagefooter.contains(tempString))continue;
					departmentResult = departmentStringFinder(tempString);}
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return departmentResult;
	}

	public static List<String> departmentStringFinder(String line) {
		ArrayList<String> departmentResult = new ArrayList<>();
		int[] garde = { 0, 0, 0 };
		// match level-1 goverment.txt + postfix.txt

		for (int i = 0; i < governmentInfoList.size(); i++) {
			for (int j = 0; j < governmentInfoList.get(i).size(); j++) {
				String supGovString = governmentInfoList.get(i).get(0), govString = governmentInfoList.get(i).get(j);
				int tempFlag = 0;
				if (line.contains(govString))
					for (int k = 0; k < postfixInfoList.size(); k++) {
						String postfixString = postfixInfoList.get(k);
						if (line.contains(govString + postfixString)) {
							if (!departmentResult.contains(supGovString + " " + govString)) {
								departmentResult.add(supGovString + " " + govString);
								System.out.println(line + '\n' + supGovString + " " + govString + "-" + postfixString);
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
}
