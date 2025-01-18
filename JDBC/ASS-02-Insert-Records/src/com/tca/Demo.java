package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Demo {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		BufferedReader br = null;
		
		//final String url = "jdbc:postgresql://localhost/mydb";
		final String url = "jdbc:postgresql://[2409:40c2:104e:af81:3495:ddf9:6370:ebe]:5432/mydb";
		final String user = "dnyaneshwar";
		final String password = "root@3121"; 
		
		
		try {
			
			br = new BufferedReader(new InputStreamReader(System.in));
			
			//Loading Driver Class
			//Class.forName("org.postgresql.Driver");
			//new Driver();
			
			//Forming Connection to the database
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the database !");
			
			//Prepare SQL query
			System.out.print("Enter the roll number : ");
			int rno = Integer.parseInt(br.readLine());
			System.out.print("Enter the name        : ");
			String name = br.readLine();
			System.out.print("Enter the percentage  : ");
			double per = Double.parseDouble(br.readLine());
		
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
			e.printStackTrace();
		}
	}
}
