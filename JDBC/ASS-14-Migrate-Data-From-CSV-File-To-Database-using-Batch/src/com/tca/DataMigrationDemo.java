package com.tca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tca.config.DatabaseConfig;

public class DataMigrationDemo {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			// Loading Driver Class
			Class.forName("org.postgresql.Driver");

			// Getting Connection object
			con = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER,
					DatabaseConfig.DB_PASSWORD);

			// Setting autoCommit false
			con.setAutoCommit(false);

			// Creating SQL query to insert records
			ps = con.prepareStatement("INSERT INTO student VALUES(?,?,?)");

			try (FileReader fr = new FileReader("resources/data.csv"); BufferedReader reader = new BufferedReader(fr)) {
				// To store a line which is read from the file
				String line = null;

				// Variable used to hold student details
				int rno = 0;
				String name = null;
				double per = 0;

				// loop which reads file line by line
				// and creates SQL for each record and adds it into the batch
				while ((line = reader.readLine()) != null) {
					String tokens[] = line.split(",");

					// If there are less than or more than 3 tokens, then it's a invalid record
					if (tokens.length != 3) {
						System.out.println("Invalid Record : " + line);
						continue; //If record is invalid, go to the next record
					}

					// Exception could occur during parsing
					// If exception occurs during data parsing, then it's a invalid record
					try {
						rno = Integer.parseInt(tokens[0]);
						name = tokens[1];
						per = Double.parseDouble(tokens[2]);
					} catch (Exception e) {
						System.out.println("Invalid Record : " + line);
						continue;////If record is invalid, go to the next record
					}

					// Crafting query for each record
					ps.setInt(1, rno);
					ps.setString(2, name);
					ps.setDouble(3, per);

					// Adding final query to the batch
					ps.addBatch();
				}

			} catch (Exception e) {
				System.out.println("Something went wrong while doing file operations !");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			// Executing batch
			ps.executeBatch();

			// Committing, if no problem has encountered
			con.commit();

			System.out.println("Data is migrated successfully !");

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
