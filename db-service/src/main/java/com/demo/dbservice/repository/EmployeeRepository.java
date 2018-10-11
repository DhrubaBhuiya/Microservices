package com.demo.dbservice.repository;

import com.demo.dbservice.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,String>{
	public Employee findByEmpId(String empId);
}
