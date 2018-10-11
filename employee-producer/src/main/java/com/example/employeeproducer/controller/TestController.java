package com.example.employeeproducer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeproducer.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class TestController {
	@RequestMapping(value="/employee" ,method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public Employee firstPage()
	{
		Employee emp1=new Employee();
		emp1.setName("emp1");
		emp1.setDesignation("Analyst");
		emp1.setEmpId("155566_IN");
		emp1.setSalary(15000);
		if(emp1.getName().equalsIgnoreCase("emp1"))
			throw new RuntimeException();

		return emp1;
		
	}
public Employee getDataFallBack() {
		
		Employee emp = new Employee();
		emp.setName("fallback-emp1");
		emp.setDesignation("fallback-manager");
		emp.setEmpId("fallback-1");
		emp.setSalary(3000);

		return emp;
		
	}

}
