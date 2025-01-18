package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateStudent {

	public static final String DB_URL = "jdbc:postgresql://localhost/mydb";
	public static final String DB_USER = "dnyaneshwar";
	public static final String DB_PASSWORD = "root@3121";

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			System.out.println("####  MENU  ####");
			System.out.println("Enter '1' to update percentage.");
			System.out.println("Enter '2' to update name.");
			System.out.println("Enter '3' to exit.");
			System.out.print("Enter :");

			int rno = 0;
			double per = 0;
			String name = null;

			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				switch (Integer.parseInt(br.readLine())) {
				case 1: {
					ps = con.prepareStatement("UPDATE student SET per = ? WHERE rno = ?");

					System.out.print("\nEnter roll number :");
					rno = Integer.parseInt(br.readLine());

					System.out.print("Enter new Percentage :");
					per = Double.parseDouble(br.readLine());

					ps.setDouble(1, per);
					ps.setInt(2, rno);
				}
					break;

				case 2: {
					ps = con.prepareStatement("UPDATE student SET name = ? WHERE rno = ?");

					System.out.print("\nEnter roll number :");
					rno = Integer.parseInt(br.readLine());

					System.out.print("Enter new name :");
					name = br.readLine();

					ps.setString(1, name);
					ps.setInt(2, rno);
				}
					break;

				case 3:
					return;

				default:
					System.out.println("Invalid Input !!");
					return;
				}

				if (ps.executeUpdate() == 1) {
					System.out.println("Record is updated successfully !");
				} else {
					System.out.println("Record not found with roll number:" + rno);
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
