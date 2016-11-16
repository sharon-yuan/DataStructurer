package com.Suirui.DataStructurer.Processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import com.Suirui.DataStructurer.SQL.SQLInfoReader;
import com.Suirui.DataStructurer.Util.FileIO;

/**
 * 将表格grade中的分类信息存成文本形式，以grade-5表示 评分为5，此文件中存入所有符合描述的标书的UID
 * 
 * @author wangsy
 *
 */
public class getListofAttri {
	// UID, URL, fileContent, grade, renew, scope, location, aptitude
	public static void main(String[] args) {
		String[] filedNames = { "grade", "renew", "scope", "location", "aptitude" };
		String Path = "E:/data/china/grade/";

		int[] filedScope = { 5, 2, 4, 3, 3 };
		for (int i = 0; i < filedNames.length; i++)
			for (int j = 1; j <= filedScope[i]; j++) {
				File dir=new File(Path + filedNames[i] + "-" + j + "/");
				if(!dir.isDirectory()){
					dir.mkdirs();
				}
				ArrayList<String> tempFileList = getListofOneAttri(filedNames[i], j + "");
				for (String fileName : tempFileList) {
					String content = FileIO.readfromFile("E:/data/china/gradeResult/"+fileName);
					FileIO.saveintoFile(Path + filedNames[i] + "-" + j + "/" + fileName, content);
				}

				// FileIO.saveintoFile(filedNames[i]+"-"+j,
				// getListofOneAttri(filedNames[i],j+"").toString());
			}

	}

	public static ArrayList<String> getListofOneAttri(String attri, String val) {
		ArrayList<String> result = new ArrayList<>();
		HashMap<String, String> infoMap = getMapFromSQL(attri);
		System.out.println("map size" + infoMap.size());
		Iterator iter = infoMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String vale = (String) entry.getValue();
			if (Integer.valueOf(vale.trim()) == Integer.valueOf(val)) {

				result.add((String) entry.getKey());
			}

		}
		return result;

	}

	public static HashMap<String, String> getMapFromSQL(String attr) {

		ArrayList<String> attris = new ArrayList<>();
		attris.add(attr);
		HashMap<String, String> result = SQLInfoReader.SQLinfoSaver("grade", attris, null, "UID");
		return result;

	}

}
