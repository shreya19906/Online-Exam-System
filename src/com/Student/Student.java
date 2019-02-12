package com.Student;

public class Student {

	private int sid;
	private String firstname;
	private String lastname;
	private String email;
	private String pwd;
	private int marks;
	public Student(String firstname, String lastname, String email, String pwd) {
		super();
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.pwd = pwd;
	
	}
	public Student(String email, String pwd) {
		super();
		this.email = email;
		this.pwd = pwd;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", pwd=" + pwd + ", marks=" + marks + "]";
	}
	
	
	
	
	
}
