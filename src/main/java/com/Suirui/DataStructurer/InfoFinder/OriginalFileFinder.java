package com.Suirui.DataStructurer.InfoFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Suirui.DataStructurer.Processing.Classify;
import com.Suirui.DataStructurer.Processing.DirController;

public class OriginalFileFinder {
	private static String RexTime = "(\\d{4} ?年 ?\\d{2} ?月 ?\\d{2} ?日)|(\\d{4} ?- ?\\d{2} ?- ?\\d{2})";

	public static List<ArrayList<String>> findTimeAndPerson(String filePath) {
		// 1st->times 2nd->person 3rd->URL 4th->svm
		List<ArrayList<String>> TAP = new ArrayList<ArrayList<String>>();

		ArrayList<String> times = new ArrayList<String>();

		ArrayList<String> persons = new ArrayList<String>();

		ArrayList<String> URL = new ArrayList<String>();
		ArrayList<String> svm = new ArrayList<String>();
		

		String tempLine;
		try {
			@SuppressWarnings("resource")
			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8"));
			while ((tempLine = input.readLine()) != null) {
				if (URL.size() == 0) {
					URL.add(tempLine);
					//System.out.println(tempLine);
				}
				Pattern pat = Pattern.compile(RexTime);
				Pattern patForPerson = Pattern.compile("采购人(名称)?(:|：| )[\u4E00-\u9FA5]{0,50}"),
						patForPerson2 = Pattern.compile("(受[\u4E00-\u9FA5]+委托)"),
						patForPersonPrefix = Pattern.compile("采购人(名称)?(:| |：)?");
				Matcher matForPerson = patForPerson.matcher(tempLine), matForPerson2 = patForPerson2.matcher(tempLine);
				Matcher mat = pat.matcher(tempLine);

				while (mat.find()) {
					// System.out.println("_-_-_-_-_------>"+mat.group().toString());
					if (times.isEmpty() || !times.contains(mat.group()))
						times.add(mat.group());
				}
				ArrayList<Integer> indexsss = new ArrayList<Integer>();
				if (matForPerson.find()) {
					Matcher matForPersonPrefix = patForPersonPrefix.matcher(matForPerson.group());
					while (matForPersonPrefix.find()) {
						indexsss.add(matForPersonPrefix.start());
						indexsss.add(matForPersonPrefix.end());
					}
					if (indexsss.size() > 2)
						persons.add(matForPerson.group().substring(indexsss.get(1), indexsss.get(2)));
					else
						persons.add(matForPerson.group().substring(indexsss.get(1)));
				}
				while (matForPerson2.find()) {
					String tempString = matForPerson2.group().substring(1, matForPerson2.group().length() - 2);
					if (tempString.matches("采购人(的?)")) {
						tempLine = "";
						continue;
					}
					if (tempString.matches(".*的"))
						tempString = tempString.substring(0, tempString.length() - 1);

					persons.add(tempString);

				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		URL.add("1111");
		TAP.add(times);// 1st->times 2nd->person 3rd->URL 4th->svm
		TAP.add(persons);// 2nd->person
		TAP.add(URL);// 3rd->URL
		
		int tempsvm = Classify.ClassInfo(filePath);
		
		svm.add(tempsvm + "");
		TAP.add(svm);// 4th->svm
		// System.out.println("size---"+TAP.size());
		return TAP;
	}
	
	private static java.sql.Date Fstring2Long(String aString) {
		//System.out.println(aString);

		aString = aString.replaceAll("[\u4E00-\u9FA5]", "-").replaceAll("\\s*", "");
		if (aString.endsWith("-"))
			aString = aString.substring(0, aString.length() - 1);

		java.text.SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
		java.text.SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-M-d");
		java.text.SimpleDateFormat formatter12 = new SimpleDateFormat("yyyy-M-dd");
		java.text.SimpleDateFormat formatter21 = new SimpleDateFormat("yyyy-MM-d");

		java.util.Date qwertty;
		try {
			if (aString.matches("\\d{4}-\\d{2}-\\d{2}"))
				qwertty = formatter22.parse(aString);
			else if (aString.matches("\\d{4}-\\d{1}-\\d{2}"))
				qwertty = formatter12.parse(aString);
			else if (aString.matches("\\d{4}-\\d{2}-\\d{1}"))
				qwertty = formatter21.parse(aString);
			else if (aString.matches("\\d{4}-\\d{1}-\\d{1}"))
				qwertty = formatter11.parse(aString);
			else
				return null;
			java.sql.Date dateJava = new java.sql.Date(qwertty.getTime());
			//System.out.println("dateSQL " + dateJava.toString());
			return dateJava;
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;

	}

}
