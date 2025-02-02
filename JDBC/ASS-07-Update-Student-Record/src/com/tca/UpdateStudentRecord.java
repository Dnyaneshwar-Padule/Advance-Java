package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateStudentRecord {

	public static final String DB_URL = "jdbc:postgresql://localhost/mydb";
	public static final String DB_USER = "dnyaneshwar";
	public static final String DB_PASSWORD = "root@3121";

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			ps = con.prepareStatement("UPDATE student SET per = ? WHERE rno = ?");
			int rno = 0;
			double per = 0;
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				System.out.print("Enter the roll number of the student: ");
				rno = Integer.parseInt(br.readLine());

				System.out.print("Enter updated Percentage : ");
				per = Double.parseDouble(br.readLine());

				ps.setDouble(1, per);
				ps.setInt(2, rno);

				if (ps.executeUpdate() == 1) {
					System.out.println("Record is updated successfully !");
				} else {
					System.out.println("Record not found for roll number :" + rno);
				}
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
