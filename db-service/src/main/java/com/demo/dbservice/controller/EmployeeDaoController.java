package com.demo.dbservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dbservice.model.Employee;
import com.demo.dbservice.repository.EmployeeRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/employee")
public class EmployeeDaoController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/{empId}")
	@HystrixCommand(fallbackMethod = "getEmployeeFallBack")
	public Employee getEmployee(@PathVariable("empId") String empId){
		if(employeeRepository.findByEmpId(empId)==null)
			throw new RuntimeException();
		return employeeRepository.findByEmpId(empId);
	}
	
	public Employee getEmployeeFallBack(String empId){
		return new Employee(empId, null, null, 0);
		
	}
	
	@GetMapping("/")
	@HystrixCommand(fallbackMethod = "getAllEmployeeFallBack")
	public List<Employee> getAllEmployee(){
		return (List<Employee>) employeeRepository.findAll();
	}
	
	public List<Employee> getAllEmployeeFallBack(){
		Employee e = new Employee(null, null, null, 0);
		List<Employee> l = new ArrayList<Employee>();
		l.add(e);
		return l;
		
	}
	
	@PostMapping("/")
	public Employee putEmployee(@RequestBody Employee employee){
		return employeeRepository.save(employee);
	}
	@DeleteMapping("/{empId}")
	public void deleteEmployee(@PathVariable("empId") String empId){
		employeeRepository.deleteById(empId);
	}
}
