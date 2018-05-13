package com.spring.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.bean.Employee;
import com.spring.rest.dao.EmployeeDAOService;
import com.spring.rest.exception.EmployeeObjectNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAOService employeeDAOService;
	
	public Employee getEmployee(long id) throws EmployeeObjectNotFoundException {
		Employee emp = employeeDAOService.getEmployee(id);
		if(emp==null){
			throw new EmployeeObjectNotFoundException("Employee Object not found for id "+id);
		}
		return emp;
	}

	public List<Employee> getEmployees() throws EmployeeObjectNotFoundException {
		List<Employee> employeeList = employeeDAOService.getEmployees();
		if(employeeList==null || employeeList.size()<=0){
			throw new EmployeeObjectNotFoundException("Employee list doesn't found ");
		}
		return employeeList;
	}

	public Employee createEmployee(Employee employee) throws EmployeeObjectNotFoundException {
		Employee emp = employeeDAOService.createEmployee(employee);
		if(emp==null){
			throw new EmployeeObjectNotFoundException("Employee Object not created for email "+employee.getEmail());
		}
		return emp;
	}

	public Employee updateEmployee(Employee employee) throws EmployeeObjectNotFoundException{
		Employee emp = employeeDAOService.updateEmployee(employee);
		if(emp==null){
			throw new EmployeeObjectNotFoundException("Employee Object not found for id "+employee.getId());
		}
		return emp;

	}

}
