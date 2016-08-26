import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.Suirui.DataStructurer.SQL.SQLInfoWriter;

public class cityReader {
public static void main(String []args){
	List<ArrayList<String>>locationArray=new ArrayList<>();
	try {
		@SuppressWarnings("resource")
		BufferedReader input = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File("forCity.txt")), "utf-8"));
	String line,prov=null,city=null;
	while ((line=input.readLine()) !=null){
		if(line.startsWith("2")) prov=line.substring(1).trim();
		else
			if(line.startsWith("1"))city=line.substring(1).trim();
			else {
				String[] contries=line.split(" ");
				for(String atempString:contries){
					ArrayList<String> county=new ArrayList<>();
					if(atempString.startsWith(" ")){
						System.err.println(atempString);
					}
					county.add(atempString.trim());county.add(city);county.add(prov);
					locationArray.add(county);
					System.out.println(atempString+" "+city+" "+prov);}
				
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
	for(ArrayList<String >aList :locationArray){
		
		for(String aString:aList)
			System.out.print (aString+" ");
		System.out.println();
		SQLInfoWriter.SQLStringListWriter("Locations", aList);
	}
	
}
}
