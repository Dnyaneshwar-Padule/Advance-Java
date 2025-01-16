package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectJavaApplicationWithDatabase {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pst = null;

		// final String userName = "dnyaneshwar";
		// final String password = "root@3121";

		// final String query = "CREATE TABLE student(rno int, name varchar(20), per
		// float)";
		final String query = "insert into student(rno,name,per) values(?, ?, ?)";
		final String userName = "vishal";
		final String password = "root@123";

		try {

			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://192.168.253.120:5432/mydb", userName, password);

			if (con != null) {
				System.out.println("Connection is successfull !");
				pst = con.prepareStatement(query);

				int rno = 103;
				String name = "CCC";
				double per = 78.89;

				pst.setInt(1, rno);
				pst.setString(2, name);
				pst.setDouble(3, per);

				pst.executeUpdate();
				System.out.println("Table created !!!\n[" + query + "]");

				con.close();
			} else {
				System.out.println("Connection failed !");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}

	}
}
