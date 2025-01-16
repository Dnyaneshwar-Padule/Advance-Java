package com.tca.untils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.tca.entities.Student;
import com.tca.exceptions.ParseStudentException;

public class StudentOperations {

	public static ArrayList<Student> getAllStudentsFromFile(String filePath) {
		try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {

			ArrayList<Student> studentList = new ArrayList<Student>();
			String line = null;

			while ((line = br.readLine()) != null) {
				try {
					studentList.add(parseStudent(line));
				}
				catch(ParseStudentException e) {
					System.out.println(e.getMessage());
				}
			}
			return studentList;
		
		} catch (FileNotFoundException e) {
			System.out.println("Please check the file path !");
			System.out.println(e.getMessage());
			return null;
		} catch (IOException e) {
			System.out.println("Something went wrong while doing I/O operation !");
			System.out.println(e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static Student parseStudent(String studentData) throws ParseStudentException {
		if (studentData == null || studentData.equals("")) {
			throw new ParseStudentException("Student data is missing or empty.");
		}

		String tokens[] = studentData.split(",");
		if (tokens.length != 3) {
			throw new ParseStudentException("Invalid student data format: " + studentData);
		}

		try {
			Student ob = new Student(tokens[0], Short.parseShort(tokens[1]), Float.parseFloat(tokens[2]));
			if(ob.getPer() > 100) {
				throw new ParseStudentException("Invalid student percentage: " + ob.getPer()); 
			}
			return ob;
		} catch (NumberFormatException e) {
			throw new ParseStudentException("Invalid student data: " + e.getMessage(), e);
		}
	}
	
}
