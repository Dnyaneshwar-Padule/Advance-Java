package com.tca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tca.entities.Student;
import com.tca.untils.StudentOperations;

public class SaveStudentsDataIntoDatabase {

	public static final String DB_URL = "jdbc:postgresql://localhost/mydb";
	public static final String DB_USER = "dnyaneshwar";
	public static final String DB_PASSWORD = "root@3121";

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pst = null;

		try {

			// Loading Driver
			Class.forName("org.postgresql.Driver");

			// Forming Connection with the database
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// Preparing SQL statement
			pst = con.prepareStatement("insert into student values(?, ?, ?)");

			double per = 0;
			int rno = 0;
			String name = null;


			ArrayList<Student> studentList = StudentOperations.getAllStudentsFromFile("resources/student.csv");
			
			if(studentList == null) {
				System.out.println("Student records not found !");
				return;
			}
			
			for(Student ob : studentList) {
				try {
					per = ob.getPer();
					rno = ob.getRollNo();
					name = ob.getName();
					
					//Update values in the prepared query
					pst.setInt(1, rno);
					pst.setString(2, name);
					pst.setDouble(3, per);
									
					if(pst.executeUpdate() < 1) {
						System.out.println("Unable to insert:" + ob.toString());
					}
					else {
						System.out.println("{" + ob.toString() + "} is inserted successfully.");
					}
				}
				catch(SQLException e){
					 //If one record is invalid don't insert it but insert all valid records.
					System.out.println("Unable to insert:" + ob.toString());
					System.out.println(e.getMessage());
				}
			}
			
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
