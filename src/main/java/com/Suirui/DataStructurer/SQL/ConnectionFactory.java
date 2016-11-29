package com.Suirui.DataStructurer.SQL;

import java.sql.Connection;

import com.alibaba.druid.pool.DruidDataSource;

public class ConnectionFactory {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.62.48/Crawler?useUnicode=true&characterEncoding=utf-8";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "redhat";
	private static DruidDataSource dataSource = null;

static {
    try {
        String driver = JDBC_DRIVER;
        String url = DB_URL;
        String user = USER ;
        String password = PASS;

        dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);

        dataSource.setPoolPreparedStatements(false);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static synchronized Connection getConnection() {
    Connection conn = null;
    try {
        conn = dataSource.getConnection();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return conn;
}}