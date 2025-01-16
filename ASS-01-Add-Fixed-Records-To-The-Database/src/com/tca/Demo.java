package com.tca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Demo {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		
		final String url = "jdbc:postgresql://localhost/mydb";
		final String user = "dnyaneshwar";
		final String password = "root@3121"; 
		
		
		try {
			//Loading Driver Class
			Class.forName("org.postgresql.Driver");
			
			//Forming Connection to the database
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the database !");
			
			//Prepare SQL query
			int rno = 44;
			String name = "Athrva";
			double per = 91.20;
			
			ps = con.prepareStatement("insert into student values(?,?,?)");
			ps.setInt(1, rno);
			ps.setString(2, name);
			ps.setDouble(3, per);
			
			//Execute Query
			int sval = ps.executeUpdate();
			
			if(sval == 0) {
				System.out.println("Record is not inserted !");
			}
			else {
				System.out.println("Record is inserted !");
			}
			
			con.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
