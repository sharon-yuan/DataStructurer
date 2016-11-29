package com.Suirui.DataStructurer.WordSeg;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;

import com.Suirui.DataStructurer.Util.FileIO;

public class CountDocumentWhereTermAppear {

	private static HashMap<String, Integer> countDocMap;

	public CountDocumentWhereTermAppear() {
		countDocMap = new HashMap<>();
	}

	public static boolean getCountDocumentWhereTermAppearFile(String DirPath) {
		countDocMap = new HashMap<>();
		File dir = new File(DirPath);
		if (!dir.isDirectory())
			return false;
		File[] files = dir.listFiles();
		for (File tempfile : files) {
			System.out.println(tempfile.getName());
			if(tempfile.getName().contains("Merged"))continue;
			String tempString = "";
			String[] content = FileIO.readfromFile(tempfile.getAbsolutePath()).split("[ '/t''/n']");
			for (int i = 0; i < content.length; i++) {
				if (i % 2 == 0)
					tempString = content[i];
				else {
					if (!countDocMap.containsKey(tempString))
						countDocMap.put(tempString, 1);
					else {
						countDocMap.put(tempString, countDocMap.get(tempString) + 1);
					}
				}
			}
		}
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(countDocMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue() - o1.getValue();
			}

		});
		String mergerdResultString = "";
		for (Map.Entry<String, Integer> mapping : list) {
			if (StopWord.JugStopWord(mapping.getKey())) {
				mergerdResultString += mapping.getKey() + " " + mapping.getValue() + '\n';
			}
		}
		FileIO.saveintoFile(DirPath+"/Merged-seg", mergerdResultString);
		return true;
	}

	public static void main(String[] args) {
		String dir = "E:/data/china/dfgk-seg";
		getCountDocumentWhereTermAppearFile(dir);

	}
}
