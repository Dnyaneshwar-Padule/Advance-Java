package com.tca.entities;

public class Student {
	
	private short rollNo;
	private String name;
	private float per;

	public Student() {}
	
	public Student(String name,short rollNo, float per) {
		super();
		this.name = name;
		this.rollNo = rollNo;
		this.per = per;
	}

	public short getRollNo() {
		return rollNo;
	}

	public void setRollNo(short rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPer() {
		return per;
	}

	public void setPer(float per) {
		this.per = per;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", per=" + per + "]";
	}

}
