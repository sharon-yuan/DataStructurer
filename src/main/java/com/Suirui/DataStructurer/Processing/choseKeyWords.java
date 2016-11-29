package com.Suirui.DataStructurer.Processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.Suirui.DataStructurer.WordSeg.StopWord;

public class choseKeyWords {
	class Node {
		public String word;
		public double freq;

		Node(String word, double freq) {
			this.word = word;
			this.freq = freq;

		}
	}

	public static Map<String, Double> contentYesMap = new HashMap<String, Double>();
	public static Map<String, Double> contentNoMap = new HashMap<String, Double>();
public static List<ArrayList<String>> contentAns = new ArrayList<ArrayList<String>>();

	// v1.0
	/*
	 * public static void main(String[] args) {
	 * 
	 * String path = "D:/sharon/done/sczf/"; getListFromFile(path +
	 * "sichuan-yes/", contentYesMap); getListFromFile(path + "sichuan-no/",
	 * contentNoMap);
	 * 
	 * Iterator<Map.Entry<String, Double>> iter =
	 * contentYesMap.entrySet().iterator();
	 * 
	 * while (iter.hasNext()) {
	 * 
	 * @SuppressWarnings("rawtypes") Map.Entry entry = iter.next(); String key =
	 * entry.getKey().toString(); double val =
	 * Double.valueOf(entry.getValue().toString()); if
	 * (contentNoMap.containsKey(key)) { val = val - contentNoMap.get(key); }
	 * Jug(key, val);
	 * 
	 * 
	 * }
	 * 
	 * BufferedWriter output; try { output = new BufferedWriter(new
	 * OutputStreamWriter(new FileOutputStream(new
	 * File(path+"chosedKeyForSichuan.txt")),"utf-8")); for(int
	 * i=0;i<contentAns.size();i++){
	 * output.write(contentAns.get(i).get(0)+'\n');
	 * 
	 * } output.close(); } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * System.out.println("get autoKey. Saved in " +path); }
	 */
	public static void getKeys(){
		/*String path = "E:/data/china/forGrade/";
		getListFromFile(path + "renew-1-TF/", contentYesMap);
		getListFromFile(path + "renew-2-TF/", contentNoMap);*/
		String path = "E:/data/china/";
		getListFromFile(path + "ChinaForYes-TF/", contentYesMap);
		getListFromFile(path + "ChinaForNo-TF/", contentNoMap);
		
		Map<String, Double> contentAnsMap=new HashMap<>();
		contentAnsMap.putAll(contentYesMap);
		contentAnsMap.putAll(contentNoMap);
		
		Iterator<Map.Entry<String, Double>> iter = contentAnsMap.entrySet().iterator();
		
		while (iter.hasNext()) {
			Entry<String, Double> entry = iter.next();
			String key = entry.getKey();
			if(!StopWord.JugStopWord(key)){ contentAnsMap.put(key, 0.0);continue;}
			boolean showTages=false;
			if(key.equals("日")){System.err.println("-----------------");System.out.println(Double.valueOf(contentYesMap.get(key))+"-"+Double.valueOf(contentNoMap.get(key))+"="+Math.abs(Double.valueOf(contentYesMap.get(key))-Double.valueOf(contentNoMap.get(key))));
			showTages=true;
			}
			
			if(contentNoMap.get(key)==null){
				if(showTages)
				System.out.println(key+"not find at NoMap");
				contentNoMap.put(key, 0.0);
			}
			if(contentYesMap.get(key)==null){
				if(showTages)
				System.out.println(key+"not find at YesMap");
				
				contentYesMap.put(key, 0.0);
			}
			//System.out.println(contentYesMap.get(key)+" "+contentNoMap.get(key));
			if(contentNoMap.get(key)==0.0||contentYesMap.get(key)==0.0)
				if(showTages)
			System.out.println(Double.valueOf(contentYesMap.get(key))+"-"+Double.valueOf(contentNoMap.get(key))+"="+Math.abs(Double.valueOf(contentYesMap.get(key))-Double.valueOf(contentNoMap.get(key))));
			double val = Double.valueOf(contentYesMap.get(key))-Double.valueOf(contentNoMap.get(key));
			if(val<0) val=val*(-0.1);
			if(val==0.0){
				if(showTages)
				System.out.println(key+" "+Double.valueOf(contentYesMap.get(key))+"-"+Double.valueOf(contentNoMap.get(key))+"="+Math.abs(Double.valueOf(contentYesMap.get(key))-Double.valueOf(contentNoMap.get(key))));
			}
			contentAnsMap.put(key, val);
		}
		/*// gothough contentansMap
				iter = contentAnsMap.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, Double> entry = iter.next();
					String key = entry.getKey().toString();
					double val = Double.valueOf(entry.getValue().toString());
					Jug(key, val, (int) Math.round(0.02 * contentAnsMap.size()));
				}*/
				 List<Map.Entry<String, Double>> list_Data = new ArrayList<Map.Entry<String, Double>>(contentAnsMap.entrySet()); 
				Collections.sort( list_Data,new Comparator<Map.Entry<String, Double>>() {

					@Override
					public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
						if(o1.getValue()>o2.getValue())return -1;
						else if(o1.getValue()<o2.getValue())return 1;
						else return 0;
					}
				} );
				BufferedWriter output;
				File outputFile=new File("chosedKeyForChina.txt");
				try {
					output = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
					for (int i = 0; i < Math.round(0.01*list_Data.size()); i++) {
						String keyresult=list_Data.get(i).getKey();
						output.write(keyresult+
								//" /(NoMap)"+contentNoMap.get(keyresult)+" -(YesMap)"+contentYesMap.get(keyresult)+" = "+list_Data.get(i).getValue()+ 
								'\n');

					}
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
System.out.println(contentYesMap.size()+" "+contentNoMap.size()+" "+contentAnsMap.size());
				System.out.println("get autoKey. Saved in " + outputFile.getAbsolutePath());
	}

	public static void main(String[] args) {
		
		getKeys();
		/*

		String path = "E:/data/china/forGrade/";
		getListFromFile(path + "renewTitle-2-TF/", contentYesMap);
		getListFromFile(path + "renewTitle-1-TF/", contentNoMap);
		Map<String, Double> contentAnsMap = contentYesMap;
		// gothough contentnomap, calu the value=yes.value-no.value
		Iterator<Map.Entry<String, Double>> iter = contentNoMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Double> entry = iter.next();
			String key = entry.getKey().toString();
			double val = -1.0 * Double.valueOf(entry.getValue().toString());
			if (contentAnsMap.containsKey(key)) {
				val += contentYesMap.get(key);
			}
			contentAnsMap.put(key, val);
		}
		// gothough contentansMap
		iter = contentAnsMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Double> entry = iter.next();
			String key = entry.getKey().toString();
			double val = Double.valueOf(entry.getValue().toString());
			Jug(key, val, (int) Math.round(0.02 * contentAnsMap.size()));
		}
		BufferedWriter output;
		try {
			output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File("chosedKeyForRenew.txt")), "utf-8"));
			for (int i = 0; i < contentAns.size(); i++) {
				output.write(contentAns.get(i).get(0) + '\n');

			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("get autoKey. Saved in " + "--chosedKeyForRenew.txt");
	*/}

	public static void getAns(String className) {
		// ClassName=>contentForSuirui contentForYes

		String path = "C:/Users/wangsy/Desktop/biding/";
		getListFromFile(path + className, contentYesMap);
		getListFromFile(path + "contentForNo/", contentNoMap);

		Iterator<Entry<String, Double>> iter = contentYesMap.entrySet().iterator();

		while (iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			String key = entry.getKey().toString();
			double val = Double.valueOf(entry.getValue().toString());
			if (contentNoMap.containsKey(key)) {
				val = val - contentNoMap.get(key);
			}
			Jug(key, val);

		}

		BufferedWriter output;
		try {
			output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(path + "chosedKeyForChina.txt")), "utf-8"));
			for (int i = 0; i < contentAns.size(); i++) {
				output.write(contentAns.get(i).get(0) + '\n');

			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("get autoKey. Saved in " + path);

	}

	/**
	 * 去除停用词 按大小存入list contentAns
	 * @param key
	 * @param val
	 */
	private static void Jug(String key, double val) {
		if (key.length() == "是".length())
			return;
		String[] stopwords = { "省", "市", "县", "州", "区", "局", "厅", "第", "次", "级", "一", "二", "三", "四", "五", "六", "七", "八",
				"九", "十", "百", "千", "万" };
		for (String aString : stopwords) {
			if (key.contains(aString))
				return;
		}
		ArrayList<String> keys = new ArrayList<String>();
		keys.add(key);
		keys.add(val + "");
		if (contentAns.isEmpty()) {
			contentAns.add(keys);

			return;
		}
		int i;
		for (i = 0; i < contentAns.size() && (Math.abs(val) > Math.abs(Double.valueOf(contentAns.get(i).get(1))));) {
			i++;
		}
		if (i > 0)
			contentAns.add(i, keys);
		while (contentAns.size() > 100)
			contentAns.remove(0);

	}
	
	@SuppressWarnings("unused")
	private static void Jug(String key, double val, int size) {
		if (key.length() == "是".length())
			return;
		if (key.matches(".?\\d+.?"))
			return;
		String[] stopwords = { "省", "市", "县", "州", "区", "局", "厅", "第", "次", "级", "一", "二", "三", "四", "五", "六", "七", "八",
				"九", "十", "百", "千", "万" };
		for (String aString : stopwords) {
			if (key.contains(aString))
				return;

		}
		ArrayList<String> keys = new ArrayList<String>();
		keys.add(key);
		keys.add(val + "");
		if (contentAns.isEmpty()) {
			contentAns.add(keys);

			return;
		}
		int i;
		/*
		 * for(i=0;i<contentAns.size()&&(Math.abs(val)>Math.abs(Double.valueOf(
		 * contentAns.get(i).get(1))));){ i++; } if(i>0)contentAns.add(i, keys);
		 */
		for (i = 0; i < contentAns.size(); i++) {
			double tempdouble = Double.valueOf(contentAns.get(i).get(1));
			if (tempdouble < 0)
				tempdouble = tempdouble * (-10);
			if (val < 0) {
				val = val * (-0.1);
			}
			if (val < tempdouble)
				break;
		}
		if (i > 0)
			contentAns.add(i, keys);
		while (contentAns.size() > 100)
			contentAns.remove(0);

	}

	/**
	 *  save content of file into Map */
	private static void getListFromFile(String path, Map<String, Double> contentMap) {
		String str = "";
		BufferedReader input;

		try {
			input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(path + "Merged-seg")), "utf-8"));
			while ((str = input.readLine()) != null) {
				String[] tempStrings = str.split(" ");
				contentMap.put(tempStrings[0], Double.valueOf(tempStrings[1]));
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void executor(String targetFilePath, String noiseFilePath) {

		getListFromFile(targetFilePath, contentYesMap);
		getListFromFile(noiseFilePath, contentNoMap);

		Iterator<Entry<String, Double>> iter = contentYesMap.entrySet().iterator();

		while (iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			String key = entry.getKey().toString();
			double val = Double.valueOf(entry.getValue().toString());
			if (contentNoMap.containsKey(key)) {
				val = val - contentNoMap.get(key);
			}
			Jug(key, val);

		}

		BufferedWriter output;
		try {
			output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File("chosedKeyForChina.txt")), "utf-8"));
			for (int i = 0; i < contentAns.size(); i++) {
				output.write(contentAns.get(i).get(0) + '\n');

			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("get autoKey. Saved in chosedKeyForChina.txt");

	}

}
