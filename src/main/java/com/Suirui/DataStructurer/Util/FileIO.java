package com.Suirui.DataStructurer.Util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileIO {
	/**
	 * save into file
	 * @param filePath path+filename
	 * @param content
	 * @return
	 */
	public static boolean saveintoFile(String filePath,String content){
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

}
