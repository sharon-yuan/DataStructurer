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

	public static void f2TF(String inputFilePath,String outputFilePath) {
		//System.out.println("freqToTF->f2TF "+inputFilePath);
		try {
			List<String> contentList=new ArrayList<String>();
			List<Double> timesList=new ArrayList<Double>();
			String str;
			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(inputFilePath)), "utf-8"));
			double sum=0.0;
			while ((str = input.readLine()) != null) {
				
				if(str.matches("[\u4E00-\u9FA5][\u4E00-\u9FA5]+ [0-9]?[0-9]?")) ;else {//System.out.println(str );
					continue;
				}
				
				String []strings=new String[2];
				strings=str.split(" ");
				contentList.add(strings[0]);
				timesList.add(Double.valueOf(strings[1]));
				sum+=Double.valueOf(strings[1]);
				
			}
					
			input.close();
			
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outputFilePath)),"utf-8"));
			for(int i=0;i<timesList.size();i++){
			
				output.write(contentList.get(i)+" "+(timesList.get(i)/sum)+'\n');
				
			}	
			output.close();
		}catch(

	UnsupportedEncodingException e)
	{

		e.printStackTrace();
	}catch(
	FileNotFoundException e)
	{

		e.printStackTrace();
	}catch(
	IOException e)
	{

		e.printStackTrace();
	}

	}
	
	
public static void main(String []args){

	String fileDirPath = "E:/data/china/dfgk/";
	
	//Preprocessor.word2TF(fileDirPath);
	File dir = new File(DirController.DirChanger(fileDirPath, "-seg"));
	String outDir = DirController.DirChanger(fileDirPath, "-TF");
	File outDirFile = new File(outDir);
	if (!outDirFile.isDirectory())
		outDirFile.mkdirs();
	File[] files = dir.listFiles();
	for (File tempF : files) {
		File tempTFF=new File(outDir + tempF.getName());
		if(!tempTFF.exists())
		f2TF(tempF.getPath(), outDir + tempF.getName());
	}
	
}
	
}
