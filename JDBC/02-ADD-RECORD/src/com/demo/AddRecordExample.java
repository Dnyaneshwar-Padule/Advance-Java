package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddRecordExample {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		
		final String url = "jdbc:postgresql://localhost/mydb";
		final String user = "dnyaneshwar";
		final String password = "root@3121"; 
	
		try {
			
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			int rno = 101;
			String name = "AAA";
			double per  = 89.89;
			
			pst = conn.prepareStatement("insert into student(name,per) values(?, ?, ?)");
			pst.setInt(1, rno);
			pst.setString(2, name);
			pst.setDouble(3, per);
			pst.executeUpdate();
			
			conn.close();
			System.out.println("Data is added successfully!");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
