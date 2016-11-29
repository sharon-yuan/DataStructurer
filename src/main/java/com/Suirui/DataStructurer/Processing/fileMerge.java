package com.Suirui.DataStructurer.Processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.Suirui.DataStructurer.WordSeg.LexicalAnalyzer;

public class fileMerge {
	public static void Merge(String className)  {
		String filePath = "C:/Users/wangsy/Desktop/biding/content"+className+"/";
		File dir=new File(filePath);
		File []files=dir.listFiles();

		BufferedWriter output;
		try {
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath + "Merged" + ".txt"),"utf-8"));
		
		
		for(File f:files) {
			
			
			if(f.getName().contains("Merged"))continue;
			
				
			String str = new String();
			String s1 = new String();
			
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8"));

			while ((str = input.readLine()) != null) {
				if(str.matches("[^\u4E00-\u9FA5]+")) continue;
				s1 += new String((str + "\n"));
				
			
		input.close();
		output.write(s1);
			}
		
	
		}
		output.close();
		} catch ( IOException e) {
		
			e.printStackTrace();
		}
	}
	/**
	 * 合并seg文件夹
	 * @param segFileDir
	 */
	public static void MergeSegDir(String segFileDir){

		
		File dir=new File(segFileDir);
		File []files=dir.listFiles();

		BufferedWriter output;
		try {
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(segFileDir + "\\Merged" ),"utf-8"));
		
		
		for(File f:files) {
			
			
			if(f.getName().contains("Merged"))continue;

				
			String str = new String();
			String s1 = new String();
			
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8"));

			while ((str = input.readLine()) != null) {
				if(str.matches("[^\u4E00-\u9FA5]+")) continue;
				s1 += new String((str + "\n"));
				
			}
		input.close();
		output.write(s1);
			
	
		}
		System.out.println("文件夹集合文件保存至"+segFileDir + "\\Merged" );
		
		output.close();
		} catch ( IOException e) {
			
			e.printStackTrace();
		}
		LexicalAnalyzer.wordSegFromFile(segFileDir + "\\Merged" );
		System.out.println("merged seg @"+segFileDir + "\\Merged" );
	}
	
	public static void main(String []args){

		File supDir=new File("E:/data/china/grade");
	File []dirs=supDir.listFiles();
	for(File tempDir:dirs){
		
		MergeSegDir(tempDir.getAbsolutePath());
		LexicalAnalyzer.wordSegFromFile(tempDir.getPath()+"/"+"Merged");
	}
	
		
		
		
	}
public static void getAns(String className){
		
		String path = "C:/Users/wangsy/Desktop/biding/content" + className + "/";
		Merge(className);
		LexicalAnalyzer.wordSegFromFile(path+"Merged");
		
	}

}
