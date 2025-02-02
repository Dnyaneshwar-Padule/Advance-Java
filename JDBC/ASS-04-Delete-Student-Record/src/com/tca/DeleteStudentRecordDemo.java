package com.tca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteStudentRecordDemo {
	
	public static final String DB_URL = "jdbc:postgresql://localhost/mydb";
	public static final String DB_USER = "dnyaneshwar";
	public static final String DB_PASSWORD = "root@3121";
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			int rno = 101;
			ps = con.prepareStatement("DELETE FROM student WHERE rno = ?");
			ps.setInt(1, rno);
			
			if( ps.executeUpdate() == 1 ) {
				System.out.println("Record deleted successfully !");
			}
			else {
				System.out.println("Record not found !");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause().getMessage());
		}
		finally {
			try {
				con.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause().getMessage());
			}
		}
	}
}
