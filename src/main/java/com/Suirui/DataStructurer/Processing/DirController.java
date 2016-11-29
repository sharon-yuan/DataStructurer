package com.Suirui.DataStructurer.Processing;

import java.io.File;

public class DirController {
/**
 * dir change to -seg -knn or -TF OR ""
 * @param inputPath
 * @param type ""什么都不做
 * @return
 */
	public static String DirChanger(String inputPath, String type) {
		if(type.equals(""))return inputPath;
		if (type.equals("-seg") || type.equals("-TF")||type.equals("-knn"))
			;
		else {
			System.err.println("DirChange type error!");
			return null;
		}
		
		if(inputPath.endsWith("\\"))
			return DirChangerII(inputPath,type);
		String tempAstring= DirRoot(inputPath);
		
File resultDir=new File( tempAstring.substring(0,tempAstring.length()-1) + type + "/");
if(resultDir.exists()&&(!resultDir.isDirectory())){
	System.err.println("target Dir exist as a file "+resultDir.getPath()+"inputPath is"+inputPath);
	return null;
}
else resultDir.mkdirs();
		return  tempAstring.substring(0,tempAstring.length()-1) + type + "/";

	}

	private static String DirChangerII(String inputPath, String type) {
		String tempAstring= DirRootII(inputPath);
		
		File resultDir=new File( tempAstring.substring(0,tempAstring.length()-1) + type + "\\");
		if(resultDir.exists()&&(!resultDir.isDirectory())){
			System.err.println("target Dir exist as a file "+resultDir.getPath()+"inputPath is"+inputPath);
			return null;
		}
		else resultDir.mkdirs();
				return  tempAstring.substring(0,tempAstring.length()-1) + type + "\\";

		
	}

	private static String DirRootII(String inputPath) {

		String dirPath = inputPath;
		if (inputPath.endsWith("\\"))
			dirPath = dirPath.substring(0, dirPath.length() - 1);
		
		if (dirPath.endsWith("-seg"))
			return dirPath.substring(0, dirPath.indexOf("-seg")) + "\\";
		else {
			if (dirPath.endsWith("-TF"))
				return dirPath.substring(0, dirPath.indexOf("-TF")) + "\\";
			else
				return dirPath + "\\";
		}

	
		
	}

	public static String DirRoot(String inputPath) {
		String dirPath = inputPath;
		if (inputPath.endsWith("/"))
			dirPath = dirPath.substring(0, dirPath.length() - 1);
		
		if (dirPath.endsWith("-seg"))
			return dirPath.substring(0, dirPath.indexOf("-seg")) + "/";
		else {
			if (dirPath.endsWith("-TF"))
				return dirPath.substring(0, dirPath.indexOf("-TF")) + "/";
			else
				return dirPath + "/";
		}

	}
	
	
	public static void main(String []args){
		String inputPath="E:/data/contentForYes";
		System.out.println(DirRoot(inputPath));
		System.out.println(DirChanger(inputPath,"-seg"));
	}

}
