package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteStudentRecord {

	public static final String DB_URL = "jdbc:postgresql://localhost/mydb";
	public static final String DB_USER = "dnyaneshwar";
	public static final String DB_PASSWORD = "root@3121";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			ps = con.prepareStatement("DELETE FROM student WHERE rno = ?");
			int rno = 0;

			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				System.out.print("Enter the roll no. of the student to delete his/her record : ");
				rno = Integer.parseInt(br.readLine());
				ps.setInt(1, rno);

				if (ps.executeUpdate() == 1) {
					System.out.println("Record of student with roll number :" + rno + " is deleted successfully !");
				} else {
					System.out.println("Student with roll number:" + rno + " is not found !");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause().getMessage());
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
