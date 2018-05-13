package com.spring.rest.bean;

public class Employee {

	private long id;
	
	private String fName;
	
	private String lName;
	
	private String dob;
	
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee(long id, String fName, String lName, String dob, String email) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.dob = dob;
		this.email = email;
	}
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fName=" + fName + ", lName=" + lName + ", dob=" + dob + ", email=" + email
				+ "]";
	}
	
	
	
	
}
