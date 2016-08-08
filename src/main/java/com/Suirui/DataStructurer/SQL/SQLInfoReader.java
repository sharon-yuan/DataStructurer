package com.Suirui.DataStructurer.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLInfoReader {
	

	private static Connection getConn() {

		Connection conn = null;
		try {
			Class.forName(SQLbasic.JDBC_DRIVER).newInstance();
			
			conn = (Connection) DriverManager.getConnection(SQLbasic.DB_URL, SQLbasic.USER, SQLbasic.PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
		
			e.printStackTrace();
		}
		return conn;
	}

	public static List<ArrayList<String[]>> CityReader() {

		List<ArrayList<String[]>> Loactions = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> Cities = new ArrayList<String[]>();
		ArrayList<String[]> Counties = new ArrayList<String[]>();
		Connection conn = getConn();
		Statement stmt = null;
		try {

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			ArrayList<String> list2level = new ArrayList<String>();
			list2level.add("City");
			list2level.add("County");

			sql = "SELECT * FROM City";
			
			stmt.execute(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Cities.add(new String[] { rs.getString("Name"), rs.getString("Super") });

			}
			sql = "SELECT * FROM County";
			System.out.println(sql);
			stmt.execute(sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Counties.add(new String[] { rs.getString("Name"), rs.getString("Super") });

			}

			// STEP 6: Clean-up environment
			rs.close();

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
			// System.out.println("Goodbye!");
		Loactions.add(Cities);
		Loactions.add(Counties);
		return Loactions;

	
		
	}
}
