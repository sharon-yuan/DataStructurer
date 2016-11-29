package com.Suirui.DataStructurer.InfoFinder;

import java.io.File;
import java.util.HashMap;

import com.Suirui.DataStructurer.Util.FileIO;
import com.Suirui.DataStructurer.Util.SetSystemProperty;

public class titleFinder {

	public static void main(String[] args) {

		String edgeValueString = SetSystemProperty.readValue("info.properties", "edgeString");
		File fileDir = new File("E:/data/china/gradeResult");
		//HashMap<String, String>titles=new HashMap<>();
		File[] files = fileDir.listFiles();
		for (File file : files) {
			String titleString = FileIO.readfromFilewithEdge(file.getAbsolutePath(), edgeValueString);
			FileIO.saveintoFile("E:/data/china/gradeResultTitle/"+file.getName(), titleString);
		}
	}
}
