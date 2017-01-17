package com.Suirui.DataStructurer.WordSeg;

import java.io.File;

import com.Suirui.DataStructurer.Processing.DirController;

public class Executor {
	
	public static void main(String []args){
	
	 String input="E:/data/contentForNo/",output="E:/data/contentForNoFreg/";
	 if(args.length==2) {input=args[0];output=args[1];}
		
		dirsWordSeg(input,output);
		
	}
	public static void dirsWordSeg(String inputDirPath ){
		dirsWordSeg(inputDirPath,DirController.DirChanger(inputDirPath, "-seg"));
	}
public static void dirsWordSeg(String inputDirPath,String outputDirPath) {
	System.out.println("dir file seg. from:"+inputDirPath+" to:"+outputDirPath);
	File outDir=new File(outputDirPath);
	if(!outDir.isDirectory()) outDir.mkdirs();
	File inDir=new File(inputDirPath);
	if(!inDir.isDirectory()){System.err.println("input-file-path doestn't exist!");}
	File dir = new File(inputDirPath);
	File[] files = dir.listFiles();
	for(File tempF:files){
		File tempOutputF=new File(outputDirPath+tempF.getName());
		if(!tempOutputF.exists())
		WordFrequencyStatistics.executor(tempF.getPath(), outputDirPath+tempF.getName());
	}
	
}
}
