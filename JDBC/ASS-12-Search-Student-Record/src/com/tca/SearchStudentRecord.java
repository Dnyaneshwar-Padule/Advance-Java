package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchStudentRecord {

	public static final String DB_URL = "jdbc:postgresql://localhost/mydb";
	public static final String DB_USER = "dnyaneshwar";
	public static final String DB_PASSWORD = "root@3121";

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			ps = con.prepareStatement("SELECT * FROM student WHERE rno = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);;
			int rno = 0;
			
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				System.out.print("Enter the roll number : ");
				rno = Integer.parseInt(br.readLine());
				ps.setInt(1, rno);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return;
			}
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("\nStudent Name      : " + rs.getString("name"));
				System.out.println("Student Roll no.  : " + rs.getString("rno"));
				System.out.println("Student Percentage: " + rs.getString("per"));
				System.out.println("--------------------------------------------");
			}
			else {
				System.out.println("Record not found for roll number:" + rno);
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
