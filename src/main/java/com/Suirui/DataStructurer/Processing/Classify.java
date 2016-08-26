package com.Suirui.DataStructurer.Processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.Suirui.DataStructurer.KNN.*;
import com.Suirui.DataStructurer.SQL.SQLInfoWriter;

/*
 * get classify by KNN or SVM
 * 
 *@Author Sharon-Y
 * */
public class Classify {

	public static void main(String[] args) {

		/*
		 * compareKeys.getSingleKeyfile(
		 * "D:/sharon/done/sichuan/05424C8E6A85F19709B5C95395FEF3CC-tf-tf.txt");
		 * System.out.println(TestKNN.getAns(
		 * "D:/sharon/knn/webpageInfo/tempans.txt"));
		 */
		
		
		String fileDirPath = "E:/data/china/dfgk/";
		
		//Preprocessor.word2TF(fileDirPath);
		
		exector(DirController.DirChanger(fileDirPath, "-TF"));

	}

	@SuppressWarnings("unused")
	private static void printFile(String filePath) {
		try {
			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));

			String line;
			while ((line = input.readLine()) != null)
				System.out.println(line);
			input.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	/*
	 * get classify result
	 * save knn code int0 dirchange(-knn)/file.getname
	 * @Param filePath filePath ended with "/"
	 * */
	public static int ClassInfo(String filePath) {
		File atempFile=new File(filePath);
		String	atempFilepath=atempFile.getPath();
		String filePathRoot=atempFilepath.substring(0,atempFilepath.indexOf(atempFile.getName()));

	
		return ClassInfo(filePath, DirController.DirChanger(filePathRoot, "-knn")+atempFile.getName());

	}
	public static int ClassInfo(String filePath,String knnCodeFilePath) {
	
		@SuppressWarnings("unused")
		File outFile=new File(knnCodeFilePath);
		//if (!outFile.exists()) 
		TF2Vector.SinglefileExector(filePath, knnCodeFilePath);
		return TestKNN.getAns(knnCodeFilePath);

	}

	public static void exector(String fileTFDirPath) {
System.out.println("exector to classify the files at: "+fileTFDirPath);
		int sum = 0, svm = 0;
		String knnDir=DirController.DirChanger(fileTFDirPath, "-knn");
		File knnFile=new File(knnDir);
		if(!knnFile.isDirectory())
			knnFile.mkdirs();
		File TFdir = new File(fileTFDirPath);
		File[] TFfiles = TFdir.listFiles();

		for (File tempTFfile : TFfiles) {
			
			if (tempTFfile.getName().contains("Merge"))
				continue;
			
				sum++;
				int tempFlag=ClassInfo(tempTFfile.getPath(),knnDir+tempTFfile.getName());
				if (1==tempFlag) {
					//printFile(DirController.DirRoot(fileTFDirPath)+tempTFfile.getName());
					System.out.println(svm++ + " " + sum);
				}
				else{
				
					String fileRoot=tempTFfile.getName();
					File tempaaaaafile=new File(DirController.DirRoot(fileTFDirPath)+fileRoot);
					if (tempaaaaafile.exists())
					{//printFile(DirController.DirRoot(fileTFDirPath)+fileRoot);
						
					}
					else
						System.err.println(tempaaaaafile.getName());
				}
				ArrayList<String> classResultArray=new ArrayList<>();
				File tempOFile=new File(DirController.DirRoot(fileTFDirPath)+tempTFfile.getName());
				classResultArray.add(tempOFile.getPath());classResultArray.add(tempFlag+"");
				SQLInfoWriter.SQLStringListWriter("classifyResult", classResultArray);
				
			
		}
		System.out.println(svm + " " + sum + " " + (double) svm / (double) sum);

	}

}
