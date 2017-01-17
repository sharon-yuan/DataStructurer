package com.Suirui.DataStructurer.Processing;

import java.util.ArrayList;

import com.Suirui.DataStructurer.Util.ExcelIO;
import com.Suirui.DataStructurer.Util.FileIO;

public class Excel2Files {
	public static void main(String[]args) {
		String dirPath="E:/data/china/rankingTrain/";
		ArrayList<ArrayList<String>> a;
		try {
			a=ExcelIO.readExcelData(dirPath+"a.xlsx");
		} catch (Exception e) {
			System.err.println("read xlsx err");
			e.printStackTrace();
			return;
		}
		
		for(ArrayList<String>tmpArray:a){
			FileIO.saveintoFile(dirPath+"content/"+tmpArray.get(0), tmpArray.get(1)+'\n'+tmpArray.get(2));
			String tmpString="";
			for(int i=3;i<tmpArray.size();i++)
				tmpString+=tmpArray.get(i)+'\n';
			FileIO.saveintoFile(dirPath+"grade/"+tmpArray.get(0), tmpString);
		}
	}

}
