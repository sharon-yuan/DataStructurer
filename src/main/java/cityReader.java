import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class cityReader {
public static void main(String []args){
	try {
		BufferedReader input = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File("forCity.txt")), "utf-8"));
	String line,prov=null,city=null;
	while ((line=input.readLine()) !=null){
		if(line.startsWith("2")) prov=line.substring(1);
		else
			if(line.startsWith("1"))city=line.substring(1);
			else {
				String[] contries=line.split(" ");
				for(String atempString:contries)
					System.out.println(atempString+" "+city+" "+prov);
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
	
}
}
