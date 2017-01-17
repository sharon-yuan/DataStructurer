import java.io.File;
import java.util.*;

import com.Suirui.DataStructurer.InfoFinder.CityFinder;
import com.Suirui.DataStructurer.InfoFinder.DateFinder;
import com.Suirui.DataStructurer.InfoFinder.DepartmentAndDateFinder;
import com.Suirui.DataStructurer.Util.*;
import com.mysql.fabric.xmlrpc.base.Array;
public class test {
	public static void main(String args[]) {

		ArrayList<String>result=FileIO.getLinesArray("E:/data/china/rankingTrain/train-all.txt");
		String trainList="";
		String testlist="";
		String valilist="";
		for(int i=0;i<result.size();i++){
			if(i<100)
				trainList+=result.get(i)+'\n';
			else if(i<200)
				valilist+=result.get(i)+'\n';
			else testlist+=result.get(i)+'\n';
		}
		
		FileIO.saveintoFile("E:/data/china/rankingTrain/train.txt", trainList);
		FileIO.saveintoFile("E:/data/china/rankingTrain/test.txt", testlist);
		FileIO.saveintoFile("E:/data/china/rankingTrain/vali.txt", valilist);
		
	}


	

	
}
