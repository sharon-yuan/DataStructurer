import java.io.File;
import java.util.*;

import com.Suirui.DataStructurer.InfoFinder.CityFinder;
import com.Suirui.DataStructurer.InfoFinder.DateFinder;
import com.Suirui.DataStructurer.InfoFinder.DepartmentAndDateFinder;
import com.Suirui.DataStructurer.Util.*;
public class test {
	public static void main(String args[]) {
		File dir=new File("E:/data/china/texts/");
		File[]files=dir.listFiles();
		int i=0;
		for(File tempFile:files){
			i++;
			ArrayList<String> fileContentList=FileIO.getLinesArray(tempFile.getAbsolutePath());
			System.out.println(fileContentList.get(0));
			System.out.println(DepartmentAndDateFinder.departmentStringFinder(fileContentList.get(0)));
			System.out.println(DateFinder.dateFinder(fileContentList.get(1)));
			String []resultCity=CityFinder.forTitile(fileContentList);
			for(String tempccitu:resultCity)
				System.out.println(tempccitu);
			if(i==20)break;
		}
	}


	

	
}
