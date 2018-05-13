package com.spring.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.spring.rest.bean.Employee;
import com.spring.rest.exception.EntityNotFoundException;
import com.spring.rest.service.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping(value="/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") long id) 
			throws EntityNotFoundException{
		Employee emp = employeeService.getEmployee(id);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@GetMapping 
	public ResponseEntity<List<Employee>> getEmployees() throws EntityNotFoundException{
		List<Employee> employeeList = employeeService.getEmployees();
		return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws Exception{
		Employee emp = employeeService.createEmployee(employee);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK); 
	}
	
	@PostMapping(value="/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") long employeeId,@RequestBody Employee employee) throws Exception{
		employee.setId(employeeId);
		Employee emp = employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK); 
	}
	
	
	
}
