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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TF2Vector {
	private static List<String> keys;
	private static List<Double> result;
	static List<String> stringList;
	static List<Double> freqList;
	static Map<String, Double> resultMap;

	public static void exector(String className, int type) {
		// @classname 1.ForYes 0.ForNo 3.ForHebei
		boolean forKNN = true;
		try {

			// String filepath = "D:/sharon/done/sichuan/";
			String filepath = "C:/Users/wangsy/Desktop/biding/content" + className;
			File dir = new File(filepath);
			File[] files = dir.listFiles();

			readKeyfile();
			File resultFile = new File("C:/Users/wangsy/Desktop/biding/" + className + type + ".txt");
			BufferedWriter output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(resultFile), "utf-8"));
			for (File tempFile : files) {
				if (!tempFile.getPath().endsWith("-tf.txt"))
					continue;
				// 比较两个文件 并将结果数组输出到result

				if (resultFile.exists()) {
					// System.out.print("文件存在");
				} else {
					// System.out.print("文件不存在");
					resultFile.createNewFile();// 不存在则创建
				}

				BufferedReader input = new BufferedReader(
						new InputStreamReader(new FileInputStream(tempFile), "utf-8"));
				String line;
				stringList = new ArrayList<String>();
				while ((line = input.readLine()) != null) {

					String[] lineSplit = line.split(" ");
					if (lineSplit.length != 2)
						continue;
					stringList.add(lineSplit[0]);

					freqList.add(Double.valueOf(lineSplit[1]));
					resultMap.put(lineSplit[0], Double.valueOf(lineSplit[1]));
				}
				distance(stringList, type);
				if (forKNN) {
					// 写入result
					for (int index = 0; index < result.size(); index++) {
						output.write(result.get(index) + " ");
					}
					if (className == "ForYes")
						output.write("1");
					else // if (className == "ForNo")
						output.write("0");

				} else {
					if (className == "ForYes")
						output.write("1 ");
					else
						output.write("-1 ");
					for (int index = 0; index < result.size(); index++) {
						output.write(index + ":" + result.get(index) + " ");
					}
				}
				output.write('\n');
				input.close();

			}

			output.close();
			// System.out.println("将compareKey结果存到" +
			// "C:/Users/wangsy/Desktop/biding/" + className + type + ".txt");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	
	/**
	 * 
	 * @param key 
	 * @param type type==0 欧式距离 type ==1 tfidf
	 */
	private static void distance(List<String> key, int type) {
	
		result = new ArrayList<Double>();
		for (String aString : keys) {
			if (resultMap.containsKey(aString)) {
				if (type == 1)
					result.add(resultMap.get(aString));
				else
					result.add(1.0);
			} else
				result.add(0.0);
		}
		
	}

	public static void saveCodeIntoFile(String inputFilePath, String outputFilePath) throws IOException {
		File file = new File(inputFilePath);
		File outputFile = new File(outputFilePath);
		// 比较两个文件 并将结果数组输出到result

		if (outputFile.exists()) {
			// System.out.print("文件存在");
		} else {
			// System.out.print("文件不存在");
			outputFile.createNewFile();// 不存在则创建
		}

		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));

		String line;
		stringList = new ArrayList<String>();
		while ((line = input.readLine()) != null) {

			String[] lineSplit = line.split(" ");
			if (lineSplit.length != 2)
				continue;
			stringList.add(lineSplit[0]);

			freqList.add(Double.valueOf(lineSplit[1]));
			resultMap.put(lineSplit[0], Double.valueOf(lineSplit[1]));
		}
		// 0 forTFIDF
		// 1 for 欧式距离

		int type = 1;
		distance(stringList, type);
		boolean forKNN = true;
		String className = "Forsichuan";
		if (forKNN) {
			// 写入result
			for (int index = 0; index < result.size(); index++) {
				output.write(result.get(index) + " ");
				System.out.println(result.get(index) + " ");
			}
			if (className == "ForYes")
				output.write("1");
			else // if (className == "ForNo")
				output.write("0");

		} else {
			if (className == "ForYes")
				output.write("1 ");
			else
				output.write("-1 ");
			for (int index = 0; index < result.size(); index++) {
				output.write(index + ":" + result.get(index) + " ");
			}
		}
		output.write('\n');
		input.close();
		output.close();

	}

	/**
	 * 将chosedKeyForChina.txt中的内容存入程序 keys
	 */

	public static void readKeyfile() {
		readKeyfile("chosedKeyForChina.txt");
	}
/**
 * save into private static List<String> keys;
 * @param filePath
 */
	public static void readKeyfile(String filePath) {
		keys = new ArrayList<>();
		// 得到commonkeys

		try {
			File commonkeys = new File(filePath);
			if (commonkeys.exists()) {

				BufferedReader input;
				input = new BufferedReader(new InputStreamReader(new FileInputStream(commonkeys), "utf-8"));
				String a;
				while ((a = input.readLine()) != null) {
					keys.add(a);
				}
				input.close();
				System.out.println("keys size:"+keys.size());
			} else {
				System.err.println("commonkeys 不存在");
				return;
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public static void getSingleKeyfile(String filePath) {
		// 1-->for yes
		// 0-->for no
		// else-->for test
		int dataType = 3;

		// @classname 1.ForYes 0.ForNo 3.ForHebei
		boolean forKNN = true;
		try {

			// String filepath = "D:/sharon/done/sichuan/";
			// String filepath = "C:/Users/wangsy/Desktop/biding/content" +
			// className;
			// File dir = new File(filepath);
			// File[] files = dir.listFiles();
			File inputfile = new File(filePath);

			readKeyfile();
			File resultFile = new File("tempans.txt");
			BufferedWriter output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(resultFile), "utf-8"));

			// 比较两个文件 并将结果数组输出到result

			if (resultFile.exists())
				;
			else {
				resultFile.createNewFile();// 不存在则创建
			}

			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputfile), "utf-8"));
			String line;
			stringList = new ArrayList<String>();
			while ((line = input.readLine()) != null) {

				String[] lineSplit = line.split(" ");
				if (lineSplit.length != 2)
					continue;
				stringList.add(lineSplit[0]);

				freqList.add(Double.valueOf(lineSplit[1]));
				resultMap.put(lineSplit[0], Double.valueOf(lineSplit[1]));
			}
			distance(stringList, 1);// 1 for TFIDF
			if (forKNN) {
				// 写入result
				for (int index = 0; index < result.size(); index++) {
					output.write(result.get(index) + " ");
				}
				if (dataType == 1)
					output.write("1");
				else // if (dataType == 0)
					output.write("0");

			} else {
				if (dataType == 1)
					output.write("1 ");
				else
					output.write("-1 ");
				for (int index = 0; index < result.size(); index++) {
					output.write(index + ":" + result.get(index) + " ");
				}
			}
			output.write('\n');
			input.close();

			output.close();
			// System.out.println("将compareKey结果存到" + resultFile.getPath());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
/*
 * 
 * 
 * 
 * */
	public static void SinglefileExector(String inputFilePath, String outputfilePath) {
		System.out.println("SinglefileExector"+inputFilePath+" --to-- "+outputfilePath);
		// 1-->for yes
		// 0-->for no
		// else-->for test
		int dataType = 3;

		// @classname 1.ForYes 0.ForNo 3.ForHebei
		boolean forKNN = true;
		try {

			File file = new File(inputFilePath);

			readKeyfile();
			File resultFile = new File(outputfilePath);
			BufferedWriter output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(resultFile), "utf-8"));

			// 比较两个文件 并将结果数组输出到result

			if (resultFile.exists())
				;
			else {
				resultFile.createNewFile();// 不存在则创建
			}

			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String line;
			stringList = new ArrayList<String>();
			freqList = new ArrayList<>();
			resultMap = new HashMap<>();
			int tempSize=0;
			while ((line = input.readLine()) != null) {
				String[] lineSplit = line.split(" ");
			
				if (lineSplit.length != 2)
					continue;
			
				stringList.add(lineSplit[0]);
				freqList.add(Double.valueOf(lineSplit[1]));
				
				resultMap.put(lineSplit[0], Double.valueOf(lineSplit[1]));
			}
			System.out.println(tempSize);
			tempSize=0;
			distance(stringList, 1);// 1 for TFIDF
			if (forKNN) {
				// 写入result
				for (int index = 0; index < result.size(); index++) {
					output.write(result.get(index) + " ");
				}
				if (dataType == 1)
					output.write("1");
				else  if (dataType == 0)
					output.write("0");

			} else {
				if (dataType == 1)
					output.write("1 ");
				else if (dataType == 0)
					output.write("-1 ");
				for (int index = 0; index < result.size(); index++) {
					tempSize++;
					output.write(index + ":" + result.get(index) + " ");
				}
			}
			output.write('\n');
			input.close();
System.out.println(tempSize);
			output.close();
			// System.out.println("将compareKey结果存到" + resultFile.getPath());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
/**
 * 
 * @param inputTFDirPath
 * @param outputfilePath
 * @param dataType  1-->for yes 0-->for no
 */
	public static void WholeDirExector(String inputTFDirPath, String outputfilePath, int dataType) {
		// 1-->for yes
		// 0-->for no
		// else-->for test

		// @classname 1.ForYes 0.ForNo 3.ForHebei
		boolean forKNN = true;
		try {

			// String filepath = "D:/sharon/done/sichuan/";
			// String filepath = "C:/Users/wangsy/Desktop/biding/content" +
			// className;
			// File dir = new File(filepath);
			// File[] files = dir.listFiles();
			File dir = new File(inputTFDirPath);
			File[] files = dir.listFiles();

			readKeyfile();

			File resultFile = new File(outputfilePath);
			BufferedWriter output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(resultFile), "utf-8"));

			for (File file : files) {
			if(file.getName().contains("Merge")) continue;
				// 比较两个文件 并将结果数组输出到result
				//System.out.println(file.getPath());
				if (resultFile.exists())
					;
				else {
					resultFile.createNewFile();// 不存在则创建
				}

				BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
				String line;
				stringList = new ArrayList<String>();
				freqList = new ArrayList<>();
				resultMap = new HashMap<>();
				while ((line = input.readLine()) != null) {

					String[] lineSplit = line.split(" ");
					if (lineSplit.length != 2)
						continue;
					stringList.add(lineSplit[0]);

					freqList.add(Double.valueOf(lineSplit[1]));
					resultMap.put(lineSplit[0], Double.valueOf(lineSplit[1]));
				}
				//System.out.println("stringlist" + stringList);
				distance(stringList, 1);// 1 for TFIDF
				if (forKNN) {
					// 写入result
					for (int index = 0; index < result.size(); index++) {
						output.write(result.get(index) + " ");
					}
					if (dataType == 1)
						output.write("1");
					else // if (dataType == 0)
						output.write("0");

				} else {
					if (dataType == 1)
						output.write("1 ");
					else
						output.write("-1 ");
					for (int index = 0; index < result.size(); index++) {
						output.write(index + ":" + result.get(index) + " ");
					}
				}
				output.write('\n');
				input.close();

			}
			output.close();

			// System.out.println("将compareKey结果存到" + resultFile.getPath());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// getCompareKey("ForNo", 1);
		// readKeyfile();
		/*
		 * String inputPath =
		 * "D:/sharon/done/sichuan/F0B2A2DFE3939C7CD0739A857077CEA2-tf-tf.txt",
		 * outputPath = "C:/Users/wangsy/Desktop/biding/sichuan/aaa.txt"; try {
		 * saveCodeIntoFile(inputPath,outputPath);
		 * 
		 * } catch (IOException e) { // TODO 自动生成的 catch 块 e.printStackTrace();
		 * }
		 */
		// getSingleKeyfile("D:/sharon/done/sichuan/F0B2A2DFE3939C7CD0739A857077CEA2-tf-tf.txt");

		WholeDirExector("E:/data/china/ChinaForNo-TF", "E:/data/china/Noisy",0);
	}

	public static void getAns(String className, int type) {
		exector(className, type);
	}
	
	
	/**
	 * 
	 * @param mode train/test
	 * @param data 
	 * @param target 1-5 
	 * @param qid 1-5 the set number of train data
	 * @return one line of tarin data for SVMRanking
	 */
	public static String vector4SVMRanking(String mode, List<Double> data,int target,int qid){
		
		/*The file format for the training data (also testing/validation data) is the same as for SVM-Rank. This is also the format used in LETOR datasets. Each of the following lines represents one training example and is of the following format:
		<line> .=. <target> qid:<qid> <feature>:<value> <feature>:<value> ... <feature>:<value> # <info>
		<target> .=. <positive integer>
		<qid> .=. <positive integer>
		<feature> .=. <positive integer>
		<value> .=. <float>
		<info> .=. <string>
		Here's an example: (taken from the SVM-Rank website). Note that everything after "#" are ignored.
		3 qid:1 1:1 2:1 3:0 4:0.2 5:0 
		2 qid:1 1:0 2:0 3:1 4:0.1 5:1 
		1 qid:1 1:0 2:1 3:0 4:0.4 5:0 
		1 qid:1 1:0 2:0 3:1 4:0.3 5:0  
		1 qid:2 1:0 2:0 3:1 4:0.2 5:0  
		2 qid:2 1:1 2:0 3:1 4:0.4 5:0  
		1 qid:2 1:0 2:0 3:1 4:0.1 5:0 
		1 qid:2 1:0 2:0 3:1 4:0.2 5:0  
		2 qid:3 1:0 2:0 3:1 4:0.1 5:1 
		3 qid:3 1:1 2:1 3:0 4:0.3 5:0 
		4 qid:3 1:1 2:0 3:0 4:0.4 5:1 
		1 qid:3 1:0 2:1 3:1 4:0.5 5:0 */
		
		String anString=target+ " qid:"+qid;
		for(int i=0;i<data.size();i++){
			if(0.0==data.get(i)) continue;
			anString+=" "+i+":"+data.get(i);
		}
		
		
		return anString;
	}
	
	/**
	 * 
	 * @param mode  train/test
	 * @param data 
	 * @param target 1 or 0
	 * @return
	 */
public static String vector4KNN(String mode, List<Double> data,int target){
	String anString="";
	
	return anString;
	
}

}
