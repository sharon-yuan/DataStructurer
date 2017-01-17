package com.Suirui.DataStructurer.Util;

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
import java.util.HashMap;

public class FileIO {
	/**
	 * save into file
	 * @param filePath path+filename
	 * @param content
	 * @return
	 */
	public static boolean saveintoFile(String filePath,String content){
		File file=new File(filePath);
		File dir=new File(file.getParent());
		if(!dir.exists())dir.mkdir();
		BufferedWriter output;
		try {
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"utf-8"));
			output.write(content);
			output.close();
		} catch (UnsupportedEncodingException e) {
		
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (IOException e) {
		
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	/**
	 * FROM FILE TO STRING
	 * @param filePath
	 * @return
	 */
	public static String readfromFile(String filePath){
		String result="",line;
		BufferedReader input;
		try {
			input=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			while ((line=input.readLine()) !=null){
				result+=line+" ";
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
		return result;
		
	}
	
	public static String readfromFilewithEdge(String filePath,String edgeString){
		String result="",line;
		BufferedReader input;
		try {
			input=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			while ((line=input.readLine()) !=null){
				if(line.contains(edgeString)){
					String []alineArray=line.split(edgeString);
					result+=alineArray[0];
					input.close();
					return result;
				}
				else
				result+=line+" ";
			}
			input.close();
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
		
		return result;
		
	}
	

	public static ArrayList<String> getLinesArray(String filePath) {
		ArrayList<String> result=new ArrayList<>();
		BufferedReader input;
		try {
			input=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			String line;
			while ((line=input.readLine()) !=null){
				result.add(line);}
			input.close();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
		
		return result;
		
	}
	public static HashMap<String,Integer> getCountedLinesMap(String filePath) {
		HashMap<String,Integer> result=new HashMap<>();
		
		BufferedReader input;
		try {
			input=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			String line;
			while ((line=input.readLine()) !=null){
				String []tempLine=line.split(" ");
			if(result.containsKey(tempLine[0]))
				result.put(tempLine[0], result.get(tempLine[0])+1);
			else
				result.put(tempLine[0], 1);
			}
			input.close();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
		
		return result;
		
	}



}
