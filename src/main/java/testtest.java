import java.sql.Date;
import java.util.ArrayList;

import com.Suirui.DataStructurer.InfoFinder.DateFinder;

public class testtest {
public static void main(String []args) {
	ArrayList<Date> Datelist=DateFinder.dateFinder("1990-3-26 1991-3-26");
	
		System.out.println(Datelist.get(0).before(Datelist.get(1)));
	
}
}
