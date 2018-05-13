package com.spring.rest.service;

import java.util.List;

import com.spring.rest.bean.Employee;
import com.spring.rest.exception.EmployeeObjectNotFoundException;

public interface EmployeeService {

	public Employee getEmployee(long id) throws EmployeeObjectNotFoundException;
	
	public List<Employee> getEmployees() throws EmployeeObjectNotFoundException;
	
	public Employee createEmployee(Employee employee) throws EmployeeObjectNotFoundException ;
	
	public Employee updateEmployee(Employee employee) throws EmployeeObjectNotFoundException ;
	
}
