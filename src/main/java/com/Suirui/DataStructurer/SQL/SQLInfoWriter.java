package com.Suirui.DataStructurer.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

public class SQLInfoWriter {
	public static void main(String[]args){
		List<String> infos = new ArrayList<>();
		infos.add("a");infos.add("a");infos.add("a");
		List<ArrayList<String>>infosII=new ArrayList<>();
		infosII.add((ArrayList<String>) infos);
		SQLStringListWriter("Locations", infosII);
	}
	public static int SQLStringListWriter(String tableName, List<ArrayList<String>> infos) {
		Connection conn = SQLbasic.getConn();
		int i = 0;
		
		String sql = "insert into " + tableName + " values(";
		for(int index=0;index<infos.get(0).size()-1;index++)
			sql=sql+"?,";
		sql=sql+"?)";
		System.out.println("sql: "+sql);
	
	
		try {
			for(int indexJ=0;indexJ<infos.size();indexJ++){
				PreparedStatement pstmt ;
				pstmt= conn.prepareStatement(sql);
			for(int index=1;index<=infos.get(indexJ).size();index++)
				pstmt.setString(index, infos.get(indexJ).get(index-1));
			
			System.out.println(pstmt.toString());
			i = pstmt.executeUpdate();
			pstmt.close();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}

}
