package com.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddRecord {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		final String url = "jdbc:postgresql://localhost/mydb";
		final String user = "dnyaneshwar";
		final String password = "root@3121";
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
			
			int rno = 0;
			String name = null;
			double per = 0;
			
			while(true) {
				
				System.out.print("\nEnter the roll no.   : ");
				rno = Integer.parseInt(br.readLine());
				
				System.out.print("Enter the name       : ");
				name = br.readLine();
				
				System.out.print("Enter the percentage : ");
				per = Double.parseDouble(br.readLine());
				
				pst = conn.prepareStatement("insert into student values(?, ?, ?)");
				pst.setInt(1, rno);
				pst.setString(2, name);
				pst.setDouble(3, per);
				
				if ( pst.executeUpdate() < 1) {
					System.out.println("Problem while saving the record !");
				}
				else {
					System.out.println("Reord is added successfully !");					
				}
				
				System.out.print("Enter 'yes' to continue, 'no' to exit : ");
				
				if( br.readLine().equalsIgnoreCase("no") ) {
					conn.close();
					System.out.println("BYE !!");
					return;
				}
				
			}
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}
