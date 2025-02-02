package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNRecordsWithBatchUpdate {
	public static final String DB_URL = "jdbc:postgresql://localhost/mydb";
	public static final String DB_USER = "dnyaneshwar";
	public static final String DB_PASSWORD = "root@3121";
	
	public static void main(String[] args) {
	
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			//Loading Driver
			Class.forName("org.postgresql.Driver");
			
			//Getting Connection object
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Setting auto commit false
			con.setAutoCommit(false);
			
			//Preparing SQL to insert student Records
			ps = con.prepareStatement("INSERT INTO student VALUES(?,?,?)");
			
			//Variables to hold student data
			int rno = 0;
			double per = 0;
			String name = null;
			
			//Flag to identify if, at-least one record is inserted
			boolean isRecordGivenToInsertIntoDatabase = false;
			
			//Accepting student records
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
				//To store user choice
				String choice = null;
				
				//Loop to accept N records
				while(true) {
					System.out.print("Do you want to insert the record [Yes/No] :");
					choice  = br.readLine();
					
					if(choice.equalsIgnoreCase("yes")) {
						System.out.print("\tEnter the roll no.   : ");
						rno = Integer.parseInt(br.readLine());
						
						System.out.print("\tEnter the name       : ");
						name = br.readLine();
						
						System.out.print("\tEnter the percentage : ");
						per = Double.parseDouble(br.readLine());
						
						//Crafting query for each record
						ps.setInt(1, rno);
						ps.setString(2, name);
						ps.setDouble(3, per);
						
						//Adding query into a batch
						ps.addBatch();
						
						isRecordGivenToInsertIntoDatabase = true;
					}
					else if(choice.equalsIgnoreCase("no")) {
						break;
					}
					else {
						System.out.println("\t*** Invalid Input ! ***");
					}
				}
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause().getMessage());
			}
			
			//inserting all records at once
			ps.executeBatch();
			
			//Committing only if no problem has been encountered 
			con.commit();
			
			if(isRecordGivenToInsertIntoDatabase)
				System.out.println("All records are inserted successfully !");
			
		}catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
			System.out.println(e.getCause().getMessage());
		}
		finally {
			try {
				con.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause().getMessage());
			}
		}
	}
}
