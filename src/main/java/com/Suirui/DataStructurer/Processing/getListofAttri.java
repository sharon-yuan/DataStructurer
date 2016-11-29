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
	// UID, URL, fileContent, grade, renew, scope, size, company, produce
	//将完整的文件夹 分为类别 等级 存入 E:/data/china/grade/
	public static void main(String[] args) {
		String[] dirTypes={"","-seg"};
		//
		String[] filedNames = { "grade", "renew", "scope", "size", "company","produce" };
		//输出路径
		String Path = "E:/data/china/forGrade/";

		int[] filedScope = { 5, 2, 4, 3, 2,2 };
		for(int h=0;h<dirTypes.length;h++)
		for (int i = 0; i < filedNames.length; i++)
			for (int j = 0; j <= filedScope[i]; j++) {
				System.out.println(dirTypes[h]+"- -"+filedNames[i]+"- -"+j+"/"+filedScope[i]);
				ArrayList<String> tempFileList = getListofOneAttri(filedNames[i], j + "");
				System.out.println("wenjiangeshu"+tempFileList.size());
				if(tempFileList.size()==0)continue;
				
				/*File dir=new File(DirController.DirChanger(Path + filedNames[i] + "-" + j + "/",dirTypes[h]));
				if(!dir.isDirectory()){
					dir.mkdirs();
				}*/
				
				
				for (String fileName : tempFileList) {
					
					//更改这个路径作为输入路径
					String content = FileIO.readfromFile(DirController.DirChanger("E:/data/china/gradeTitle/",dirTypes[h])+fileName);
					FileIO.saveintoFile(DirController.DirChanger(Path + filedNames[i] + "Title-" + j + "/",dirTypes[h])+ fileName, content);
					
					 content = FileIO.readfromFile(DirController.DirChanger("E:/data/china/gradeResult/",dirTypes[h])+fileName);
					FileIO.saveintoFile(DirController.DirChanger(Path + filedNames[i] + "-" + j + "/",dirTypes[h])+ fileName, content);
					//System.out.println("saved into"+DirController.DirChanger(Path + filedNames[i] + "Title-" + j + "/",dirTypes[h])+ fileName);
				}

				// FileIO.saveintoFile(filedNames[i]+"-"+j,
				// getListofOneAttri(filedNames[i],j+"").toString());
			}

	}
/**
 * CONNECT SQL get file name list 
 * 从数据库读取相应属性的文件名list
 * @param attri
 * @param val
 * @return
 */
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
		HashMap<String, String> result = SQLInfoReader.SQLinfoSaver("gradeV4", attris, null, "UID");
		return result;

	}

}
