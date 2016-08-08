package com.Suirui.DataStructurer.InfoFinder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFinder {
public static ArrayList<Date> dateFinder (String tempLine) {
String RexTime = "(\\d{4} ?年 ?((\\d{2})|(\\d{1})) ?月 ?\\d{2} ?日)|(\\d{4} ?- ?((\\d{2})|(\\d{1})) ?- ?\\d{2})",rexg="[0-9]{4}-";
ArrayList<java.sql.Date> times = new ArrayList<>();
	Pattern pat = Pattern.compile(RexTime);
	Matcher mat = pat.matcher(tempLine);
	while (mat.find()) {
	// System.out.println("_-_-_-_-_------>"+mat.group().toString());
	if (times.isEmpty() || !times.contains(mat.group()))
		times.add(Fstring2Long(mat.group()));
}
	
	
	return times;
}

private static java.sql.Date Fstring2Long(String aString) {
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
