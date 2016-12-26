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
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import com.Suirui.DataStructurer.SQL.SQLInfoReader;
import com.Suirui.DataStructurer.Util.FileIO;
import com.sun.tools.classfile.Opcode.Set;
import com.sun.tools.javac.util.List;

public class CityFinder {
	public static void main(String[] args) {

		System.out.println(cityFinder("test.txt"));
		ArrayList<String> content = new ArrayList<>();
		content.add("池州市是一个呼啦圈地盘变雪白安徽省河北省，安徽省");
		String[]anStrings=forTitile(content);
		for(String tmpString:anStrings)
		System.err.println(tmpString);
	}

	public static String cityFinder(String filePath) {
		System.gc();
		ArrayList<String> cityList = new ArrayList<>();

		Map<String, Integer> resultCountyMap = new HashMap<String, Integer>();
		Map<String, Integer> resultCityMap = new HashMap<String, Integer>();
		String line;
		try {
			BufferedReader inputCityInfo = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File("CityInfo.txt")), "utf-8"));
			while ((line = inputCityInfo.readLine()) != null)
				cityList.add(line);
			inputCityInfo.close();

			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));

			String tempCityNameForLoop = "";
			while ((line = input.readLine()) != null) {
				String[] linesplitArray = line.split(" ");

				for (String splitLine : linesplitArray) {
					for (String atempCountyString : cityList) {
						if (!atempCountyString.contains(" "))
							continue;
						String[] tempStringArray = atempCountyString.split(" ");
						if (splitLine.contains(tempStringArray[0])) {
							if (tempStringArray[0].trim().equals("") || tempStringArray[0] == null)
								continue;
							int freq = 1;
							if (resultCountyMap.containsKey(atempCountyString)) {
								freq = 1 + resultCountyMap.get(atempCountyString);
							}
							resultCountyMap.put(atempCountyString, freq);
						}
						if (!tempCityNameForLoop.equals(tempStringArray[1])) {

							tempCityNameForLoop = tempStringArray[1];
							if (splitLine.contains(tempCityNameForLoop)) {

								if (tempStringArray[0].trim().equals("") || tempStringArray[0] == null)
									continue;
								String tempKey = tempStringArray[1] + " " + tempStringArray[2];
								int freq = 1;
								if (resultCityMap.containsKey(tempKey))
									freq = resultCityMap.get(tempKey) + 1;

								resultCityMap.put(tempKey, freq);
								tempKey = null;
							}
						}
						tempStringArray = null;
					}
				}
				linesplitArray = null;
			}
			tempCityNameForLoop = null;
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
			if (!flag) {
				resultCityMap.put(entryCounty.getKey().split(" ")[1] + " " + entryCounty.getKey().split(" ")[2], 1);
			}
		}
		int maxVal = 0;
		for (Entry<String, Integer> entryCity : resultCityMap.entrySet()) {
			if (entryCity.getValue() > maxVal) {
				resultString = entryCity.getKey();
				maxVal = entryCity.getValue();
			}

		}
		resultCountyMap = null;
		resultCityMap = null;
		cityList = null;
		line = null;
		return resultString;
	}

	public static String[] forTitile(ArrayList<String> content) {

		System.gc();
		ArrayList<String> cityList = new ArrayList<>();
		String[] result = new String[3];
		HashSet<String>[] county = new HashSet[3];
		for (int i = 0; i < 3; i++)
			county[i] = new HashSet<>();
		HashMap<String, Integer>[] countyResult = new HashMap[3];
		for (int i = 0; i < 3; i++)
			countyResult[i] = new HashMap<>();

		ArrayList<String> cityFileContent = FileIO.getLinesArray("CityInfo.txt");
		//System.out.println(cityFileContent.size());
		for (String tempString : cityFileContent) {

			String[] tempcities = tempString.split(" ");

			for (int i = 0; i < 3; i++)
				county[i].add(tempcities[i]);

		}
		
		for (int i = 0; i < 3; i++)
			for (String tempContent : content) {
String[]splitTempContent=tempContent.split("[,. ，。]");
for(String tempTrySString:splitTempContent)
				for (String atempString : county[i]) {
					if (tempTrySString.contains(atempString)) {
						
						if (countyResult[i].containsKey(atempString))
							countyResult[i].put(atempString, countyResult[i].get(atempString) + 1);
						else
							countyResult[i].put(atempString, 1);
					}
				}

			}

		for (int i = 0; i < 3; i++)
			if (countyResult[i].size()>0) {
				
				java.util.Set<String> tempSet = countyResult[i].keySet();
				String Maxkey = "";
				int MaxVal = 0;
				for (String keyString : tempSet) {
					//System.out.println(MaxVal+keyString +" "+ countyResult[i].get(keyString));
					if (MaxVal < countyResult[i].get(keyString)){
						MaxVal=countyResult[i].get(keyString);
						Maxkey = keyString;
						}
				}
				result[i] = Maxkey;
				//System.out.println(Maxkey);
			}
		return result;
	}

}
