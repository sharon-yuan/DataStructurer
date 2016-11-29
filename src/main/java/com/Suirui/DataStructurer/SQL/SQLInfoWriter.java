package com.Suirui.DataStructurer.SQL;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.Suirui.DataStructurer.InfoFinder.infoFinderExector;

public class SQLInfoWriter {
	public static void main(String[]args){
		List<String> infos = new ArrayList<>();
		infos.add("a");infos.add("a");infos.add("a");
		List<ArrayList<String>>infosII=new ArrayList<>();
		infosII.add((ArrayList<String>) infos);
		for(ArrayList<String>lineofInfosII:infosII)
		SQLStringListWriter("Locations",lineofInfosII );
	}
	public static int SQLStringListWriter(String tableName, ArrayList<String> tempLineString) {
	
		int i = 0;
		
		String sql = "insert into " + tableName + " values(";
		for(int index=0;index<tempLineString.size()-1;index++)
			sql=sql+"?,";
		sql=sql+"?)";
		System.out.println("sql: "+sql);
	
	
		try {
			
				PreparedStatement pstmt ;
				pstmt= infoFinderExector.conn.prepareStatement(sql);
			for(int index=1;index<=tempLineString.size();index++){
				
				pstmt.setString(index, tempLineString.get(index-1));
				}
			
			System.out.println(pstmt.toString());
			i = pstmt.executeUpdate();
			pstmt.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}
	

}
