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

import com.Suirui.DataStructurer.InfoFinder.CityFinder;
import com.Suirui.DataStructurer.KNN.*;
import com.Suirui.DataStructurer.SQL.SQLInfoWriter;
import com.Suirui.DataStructurer.Util.FileIO;
import com.mysql.fabric.xmlrpc.base.Array;

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
		
		
		String fileDirPath = "E:/data/china/textsQ34-TF/";
		
		//Preprocessor.word2TF(fileDirPath);
		
		//exector(DirController.DirChanger(fileDirPath, "-TF"));
		//exectorToSQL(DirController.DirChanger(fileDirPath, "-TF"));
		//exectorToFile(DirController.DirChanger(fileDirPath, "-TF"),"E:/data/china/rankingTrain/classifyResult.txt");
		titleExe("E:/data/china/texts/");

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
	
		try {
			File temptfFile=new File(filePath);
		
			BufferedReader inputCityInfo = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(DirController.DirRoot(temptfFile.getParent())+temptfFile.getName())), "utf-8"));
			String line;int tempFlag=0;
			while((line=inputCityInfo.readLine())!=null){
				if(line.contains("标讯库搜索_中国政府采购网")){
					tempFlag=3;
					break;
				}
				if(line.contains("视频会议")) {
				
					tempFlag=1;
					break;
				}
			
				
			}
			//if(tempFlag!=1) return 0;
			//else return 1;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		File outFile=new File(knnCodeFilePath);
		//if (!outFile.exists()) 
		TF2Vector.SinglefileExector(filePath, knnCodeFilePath);
		return TestKNN.getAns(knnCodeFilePath);

	}
	public static String ClassInfoWithURL(String filePath,String knnCodeFilePath) {
		String url=null;
		try {
			File temptfFile=new File(filePath);
		
			BufferedReader inputCityInfo = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(DirController.DirRoot(temptfFile.getParent())+temptfFile.getName())), "utf-8"));
			String line;int tempFlag=0;
			while((line=inputCityInfo.readLine())!=null){
				if(url==null){
					url=line;
				}
				if(line.contains("标讯库搜索_中国政府采购网")){
					tempFlag=3;
					break;
				}
				if(line.contains("视频会议")) {
				
					tempFlag=1;
					
				}
				
			
				
			}
			if(tempFlag!=1) return null;
		
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		File outFile=new File(knnCodeFilePath);
		//if (!outFile.exists()) 
		TF2Vector.SinglefileExector(filePath, knnCodeFilePath);
		if(TestKNN.getAns(knnCodeFilePath)==0) return null;
		else return url;

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
					printFile(DirController.DirRoot(fileTFDirPath)+tempTFfile.getName());
					System.out.println(svm++ + " " + sum);
					ArrayList<String> classResultArray=new ArrayList<>();
					File tempOFile=new File(DirController.DirRoot(fileTFDirPath)+tempTFfile.getName());
					classResultArray.add(tempOFile.getPath());classResultArray.add(tempFlag+"");
					SQLInfoWriter.SQLStringListWriter("classifyResult", classResultArray);
				
				}
				else{
				if(sum%500==0) System.out.println(svm + " " + sum);
					String fileRoot=tempTFfile.getName();
					File tempaaaaafile=new File(DirController.DirRoot(fileTFDirPath)+fileRoot);
					if (tempaaaaafile.exists())
					{//printFile(DirController.DirRoot(fileTFDirPath)+fileRoot);
						
					}
					else
						System.err.println(tempaaaaafile.getName());
				}
				
				
			
		}
		System.out.println("判定为1个数： "+svm + " 总数： " + sum + " 比例： " + (double) svm / (double) sum);

	}
	
	public static void exectorToSQL(String fileTFDirPath) {
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
				String URL=ClassInfoWithURL(tempTFfile.getPath(),knnDir+tempTFfile.getName());
				if (null!=URL) {
					printFile(DirController.DirRoot(fileTFDirPath)+tempTFfile.getName());
					System.out.println(svm++ + " " + sum);
					ArrayList<String> classResultArray=new ArrayList<>();
					File tempOFile=new File(DirController.DirRoot(fileTFDirPath)+tempTFfile.getName());
					BufferedReader inputCityInfo;
					String fileContent=null;
					try {
						inputCityInfo = new BufferedReader(
								new InputStreamReader(new FileInputStream(tempOFile), "utf-8"));
						String line;int tempFlag=0;
						while((line=inputCityInfo.readLine())!=null){
							if(fileContent==null) {fileContent="";continue;}
							else
							fileContent+=line+'\n';
						}
						
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					classResultArray.add(tempTFfile.getName());
					SQLInfoWriter.SQLStringListWriter("Q34UID", classResultArray);
				}
				else{
				if(sum%500==0) System.out.println(svm + " " + sum);
					String fileRoot=tempTFfile.getName();
					File tempaaaaafile=new File(DirController.DirRoot(fileTFDirPath)+fileRoot);
					if (tempaaaaafile.exists())
					{
						//printFile(DirController.DirRoot(fileTFDirPath)+fileRoot);
					}
					else
						System.err.println(tempaaaaafile.getName());
				}
				
			
			
		}
		System.out.println("判定为1个数： "+svm + " 总数： " + sum + " 比例： " + (double) svm / (double) sum);

	}
	
	public static void exectorToFile(String fileTFDirPath,String outputfile){

		System.out.println("exector to classify the files at: "+fileTFDirPath);
		int sum = 0, svm = 0;
		String knnDir=DirController.DirChanger(fileTFDirPath, "-knn");
		File knnFile=new File(knnDir);
		if(!knnFile.isDirectory())
			knnFile.mkdirs();
		File TFdir = new File(fileTFDirPath);
		File[] TFfiles = TFdir.listFiles();
String filenameString="";
		for (File tempTFfile : TFfiles) {
			
			if (tempTFfile.getName().contains("Merge"))
				continue;
			
				sum++;
				String URL=ClassInfoWithURL(tempTFfile.getPath(),knnDir+tempTFfile.getName());
				if (null!=URL) {
					printFile(DirController.DirRoot(fileTFDirPath)+tempTFfile.getName());
					System.out.println(svm++ + " " + sum);
					ArrayList<String> classResultArray=new ArrayList<>();
					File tempOFile=new File(DirController.DirRoot(fileTFDirPath)+tempTFfile.getName());
					BufferedReader inputCityInfo;
					String fileContent=null;
					try {
						inputCityInfo = new BufferedReader(
								new InputStreamReader(new FileInputStream(tempOFile), "utf-8"));
						String line;int tempFlag=0;
						while((line=inputCityInfo.readLine())!=null){
							if(fileContent==null) {fileContent="";continue;}
							else
							fileContent+=line+'\n';
						}
						
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					filenameString+=tempTFfile.getName()+'\n';
				}
				else{
				if(sum%500==0) System.out.println(svm + " " + sum);
					String fileRoot=tempTFfile.getName();
					File tempaaaaafile=new File(DirController.DirRoot(fileTFDirPath)+fileRoot);
					if (tempaaaaafile.exists())
					{
						//printFile(DirController.DirRoot(fileTFDirPath)+fileRoot);
					}
					else
						System.err.println(tempaaaaafile.getName());
				}
				
			
			
		}
		FileIO.saveintoFile(outputfile, filenameString);
		System.out.println("判定为1个数： "+svm + " 总数： " + sum + " 比例： " + (double) svm / (double) sum);

	
	}
	
	public static void titleExe(String fileDirPath){


		System.out.println("exector to classify the files at: "+fileDirPath);
		int sum = 0, svm = 0;
		String oriDir=DirController.DirRoot(fileDirPath);
		
		File TFdir = new File(oriDir);
		File[] TFfiles = TFdir.listFiles();

		for (File tempTFfile : TFfiles) {
			sum++;
			if (tempTFfile.getName().contains("Merge"))
				continue;
			ArrayList<String> fileContent=FileIO.getLinesArray(tempTFfile.getAbsolutePath());
			if(fileContent.get(0).contains("视频会议")){
				svm++;
				ArrayList<String> classResultArray=new ArrayList<>();
				classResultArray.add(tempTFfile.getName());
				System.out.println(fileContent.get(0)+'\n'+svm +"/"+sum);
				SQLInfoWriter.SQLStringListWriter("Q12TitleUID", classResultArray);
			}
			if(sum%50==0){
				System.out.println('\n'+svm +"/"+sum);
			}
		}
		
		System.out.println("判定为1个数： "+svm + " 总数： " + sum + " 比例： " + (double) svm / (double) sum);

	
	
	}

}
