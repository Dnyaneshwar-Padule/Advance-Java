package com.tca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayStudentRecords {

	public static final String DB_URL = "jdbc:postgresql://localhost/mydb";
	public static final String DB_USER = "dnyaneshwar";
	public static final String DB_PASSWORD = "root@3121";

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM student");
			
			while(rs.next()) {
				System.out.println("\nStudent Name      : " + rs.getString("name"));
				System.out.println("Student Roll no.  : " + rs.getString("rno"));
				System.out.println("Student Percentage: " + rs.getString("per"));
				System.out.println("--------------------------------------------");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause().getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause().getMessage());
			}
		}
	}
}
