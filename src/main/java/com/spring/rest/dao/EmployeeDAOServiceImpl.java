package com.spring.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.rest.bean.Employee;

@Repository
public class EmployeeDAOServiceImpl implements EmployeeDAOService {

	private Map<Long, Employee> employeeList = new HashMap<Long, Employee>();
	
	public EmployeeDAOServiceImpl(){
		Employee emp = null;
		for(long i = 123 ;i<=125; i++){
			emp = new Employee(i, "vikas"+i, "patidar"+i, "05052002", "vikas.inu@gmail."+i);
			employeeList.put(i,emp);
		}
	}
	
	public Employee getEmployee(long id) {
		return employeeList.get(id);
	}

	public List<Employee> getEmployees() {
		List<Employee> empList = new ArrayList<>();
		employeeList.entrySet().stream().forEach(e -> empList.add(e.getValue()));
		return empList;
	}

	public Employee createEmployee(Employee employee) {
		Employee emp = employeeList.put(employee.getId(), employee);
		return emp;
	}

	public Employee updateEmployee(Employee employee) {
		Employee emp = employeeList.get(employee.getId());
		if(emp == null){
			return emp;
		}else{
			employeeList.put(employee.getId(), emp);
		}
		return emp;
	}

}
