package com.Suirui.DataStructurer.Processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class freqToTF {

	public static void f2TF(String inputFilePath, String outputFilePath) {
		//System.out.println("freqToTF->f2TF " + inputFilePath + "save to" + outputFilePath);
		try {
			List<String> contentList = new ArrayList<String>();
			List<Double> timesList = new ArrayList<Double>();
			String str;
			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(inputFilePath)), "utf-8"));
			double sum = 0.0;
			while ((str = input.readLine()) != null) {
				// System.out.println("readline"+str);
				if (str.matches("[\u4E00-\u9FA5]+ [0-9]+")) {
					// System.out.println("matches 字字 数数");
					String[] strings = new String[2];
					strings = str.split(" ");
					contentList.add(strings[0]);
					timesList.add(Double.valueOf(strings[1]));
					sum += Double.valueOf(strings[1]);
				}
				/*
				 * else { //System.out.println("zheerzheer");
				 * 
				 * if(str.matches("[\u4E00-\u9FA5]+ [0-9]+.?")) {
				 * //System.err.println('\n'+"matches 字字 数数 *n"); String
				 * []strArray=str.split(" "); for(int
				 * i=0;i<strArray.length;i++){
				 * //System.out.println(strArray[i]);
				 * if(i%2==0){contentList.add(strArray[i]);} else{
				 * timesList.add(Double.valueOf(strArray[i])); sum +=
				 * Double.valueOf(strArray[i]);}
				 * 
				 * } } else{System.out.println(str );
				 * System.err.println(inputFilePath);} continue; }
				 */
				else {
					String[] strArray = str.split(" ");
					for (int i = 0; i < strArray.length; i++) {
						//System.out.println(strArray[i]);
						if (i % 2 == 0) {
							if (strArray[i].matches("[\u4E00-\u9FA5]+")) {
								contentList.add(strArray[i]);
								//System.out.println(strArray[i]);
							} else{//System.out.println(strArray[i]+"被判定为不含有中文");
								i++;}
						} else {
							//System.out.println(strArray[i]);
							timesList.add(Double.valueOf(strArray[i]));
							sum += Double.valueOf(strArray[i]);
						}

					}

				}

			}

			input.close();

			BufferedWriter output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(outputFilePath)), "utf-8"));
			for (int i = 0; i < timesList.size(); i++) {
				output.write(contentList.get(i) + " " + (timesList.get(i) / sum) + '\n');

			}
			output.close();
		} catch (

		UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	/**
	 * 将此文件夹下的所有文件进行f2TF
	 * @param tempDir
	 */
	public static void wholeDirToTF(File tempDir){
		//fileMerge.MergeSegDir(tempDir.getAbsolutePath());
		// String fileDirPath = "E:/data/china/ChinaForYes-seg";
		String fileDirPath = tempDir.getAbsolutePath();
		// Preprocessor.word2TF(fileDirPath);
		File dir = new File(DirController.DirChanger(fileDirPath, "-seg"));
		String outDir = DirController.DirChanger(fileDirPath, "-TF");
		File outDirFile = new File(outDir);
		if (!outDirFile.isDirectory())
			outDirFile.mkdirs();
		File[] files = dir.listFiles();
		for (File tempF : files) {
			System.out.println(tempF.getName());
			if(tempF.getName().contains("Merged"))continue;
			File tempTFF = new File(outDir + tempF.getName());
			if (!tempTFF.exists())
				f2TF(tempF.getPath(), outDir + tempF.getName());
	}
	}
	/**
	 * 将此文件夹以及之下的子文件夹中的-seg文件夹进行f2TF
	 * @param supDir
	 */
	public static void wholeDirToTFStartATSupDir(File supDir){


		
		File[] dirs = supDir.listFiles();
		for (File tempDir : dirs) {
			if (!tempDir.getName().endsWith("-seg"))
				continue;
			wholeDirToTF(tempDir);
			 /*fileMerge.MergeSegDir(tempDir.getAbsolutePath());
			// String fileDirPath = "E:/data/china/ChinaForYes-seg";
			String fileDirPath = tempDir.getAbsolutePath();
			// Preprocessor.word2TF(fileDirPath);
			File dir = new File(DirController.DirChanger(fileDirPath, "-seg"));
			String outDir = DirController.DirChanger(fileDirPath, "-TF");
			File outDirFile = new File(outDir);
			if (!outDirFile.isDirectory())
				outDirFile.mkdirs();
			File[] files = dir.listFiles();
			for (File tempF : files) {
				File tempTFF = new File(outDir + tempF.getName());
				//if (!tempTFF.exists())
					f2TF(tempF.getPath(), outDir + tempF.getName());*/
			}
		
	}
	public static void main(String[] args) {
		//wholeDirToTFStartATSupDir(new File("E:/data/china/forGrade"));
		wholeDirToTF(new File(DirController.DirChanger("E:/data/china/dfgk-seg/", "-seg")));
	}
	}


