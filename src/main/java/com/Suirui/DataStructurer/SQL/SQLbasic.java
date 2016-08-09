package com.Suirui.DataStructurer.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLbasic {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.62.48/Crawler?useUnicode=true&characterEncoding=utf-8";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "redhat";
	
	public static Connection getConn() {

		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
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
}
