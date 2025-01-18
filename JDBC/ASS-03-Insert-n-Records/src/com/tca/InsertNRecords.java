package com.tca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNRecords {
	public static final String DB_URL = "jdbc:postgresql://localhost/mydb";
	public static final String DB_USER = "dnyaneshwar";
	public static final String DB_PASSWORD = "root@3121";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;

		// BufferedReader to take input from the user
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			// Loading Driver
			Class.forName("org.postgresql.Driver");

			// Forming Connection with the database
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// variables which will be used to hold student info (roll_no, name, percentage)
			int rno = 0;
			String name = null;
			double per = 0;

			// Preparing SQL statement
			pst = con.prepareStatement("insert into student values(?, ?, ?)");

			System.out.print("How many records : ");
			int noOfRecords = Integer.parseInt(br.readLine());

			if (noOfRecords < 0) {
				System.out.println("Please enter valid number !");
				return;// Finally block will still gets executed
			}

			// loop which will insert n records
			for (int i = 1; i <= noOfRecords; ++i) {
				System.out.println("\nAccepting data for student no. " + i);

				System.out.print("Enter the roll no.   : ");
				rno = Integer.parseInt(br.readLine());

				System.out.print("Enter the name       : ");
				name = br.readLine();

				System.out.print("Enter the percentage : ");
				per = Double.parseDouble(br.readLine());

				pst.setInt(1, rno);
				pst.setString(2, name);
				pst.setDouble(3, per);
				
				if (pst.executeUpdate() < 1) {
					System.out.println("\nProblem while inserting the record ! Please try again !");
					--i;
				}
			}

			if (noOfRecords != 0)
				System.out.println("\nAll records are inserted successfully !");

		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			System.out.println("Please enter valid number !");
		} catch (ClassNotFoundException e) {
			System.out.println(
					"Please make sure, you have added postgresql.jar file in classpath or modulepath of your project.");
			System.out.println("Please check Driver class's location !");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Unable to close the connection with database !");
				e.printStackTrace();
			}
		}

	}

}
