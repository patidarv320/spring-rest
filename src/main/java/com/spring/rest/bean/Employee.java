package com.spring.rest.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Employee {


	private long id;
	
	@NotNull(message="First name of employee shouldn't be null or blank")
	@NotBlank(message="First name of employee shouldn't be null or blank")
	private String fName;
	
	@NotNull(message="Last name of employee shouldn't be null or blank")
	@NotBlank(message="Last name of employee shouldn't be null or blank")
	private String lName;
	
	@NotNull(message="DOB of employee shouldn't be null or blank")
	private String dob;
	
	@NotNull
	@NotBlank
	@Email(message="it's not a email address.")
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
