package com.spring.rest.dao;

import java.util.List;

import com.spring.rest.bean.Employee;

public interface EmployeeDAOService {

	public Employee getEmployee(long id);
	
	public List<Employee> getEmployees();
	
	public Employee createEmployee(Employee employee);
	
	public Employee updateEmployee(Employee employee);
}
