package com.Suirui.DataStructurer.SQL;

import com.Suirui.DataStructurer.Util.FileIO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLInfoReader {

	public static List<ArrayList<String[]>> CityReader() {

		List<ArrayList<String[]>> Loactions = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> Cities = new ArrayList<String[]>();
		ArrayList<String[]> Counties = new ArrayList<String[]>();
		Connection conn = SQLbasic.getConn();
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

	/**
	 * Read info from sql table
	 * @param tableName
	 * @param attr
	 * @return
	 */
	public static ArrayList<String> stringFormReader(String tableName, ArrayList<String> attr) {
		ArrayList<String> resultArray = new ArrayList<>();
		Connection conn = SQLbasic.getConn();

		try {
			Statement stmt = conn.createStatement();
			;
			String sql = "SELECT * FROM " + tableName;
			System.out.println(sql);
			stmt.execute(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String aTempString = "";
				for (int index = 0; index < attr.size(); index++)
					aTempString = aTempString + rs.getString(attr.get(index)) + " ";
				resultArray.add(aTempString);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return resultArray;

	}

	public static ArrayList<String> LocationsReader() {
		ArrayList<String> attri = new ArrayList<>();
		attri.add("County");
		attri.add("City");
		attri.add("Province");

		System.out.println("locationReader");
		return stringFormReader("Locations", attri);

	}

	/**
	 * read data form SQL table name is(formName), save data into dir(path),
	 * filename is name(which is the val of the raw named name)
	 * 
	 * @param formName
	 *            city, grade....
	 * @param attr
	 *            not included UID(filename)
	 * @param Path
	 *            ended with"/",if Path==null doesn't save data into local, save it into hashmap and return it
	 * @param name
	 *            filename(UID in table)
	 */
	public static HashMap<String, String> SQLinfoSaver(String formName, ArrayList<String> attr, String Path, String name) {
		// ArrayList<String> resultArray=new ArrayList<>();
		Connection conn = SQLbasic.getConn();
		HashMap<String, String> result = new HashMap<>();
		String filename = "";
		try {
			Statement stmt = conn.createStatement();
			String sumAttrString = name;
			for (String tempAttrsString : attr) {
				sumAttrString += ", " + tempAttrsString;
			}
			String sql = "SELECT " + sumAttrString + " FROM " + formName;
			System.out.println(sql);
			stmt.execute(sql);
			ResultSet rs = stmt.executeQuery(sql);
			// ﻿id, URL, fileContent, grade, renew, scope, location, aptitude
			while (rs.next()) {
				filename = rs.getString(name);
				String aTempString = "";
				for (String tapAttrString : attr)
					aTempString += rs.getString(tapAttrString) + " ";
				if (Path != null)
					FileIO.saveintoFile(Path + filename, aTempString);
				else {
					result.put(filename, aTempString);
				}
				// resultArray.add(aTempString);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	return result;}
}
