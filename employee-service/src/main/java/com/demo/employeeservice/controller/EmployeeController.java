package com.demo.employeeservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.employeeservice.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
    /*@Autowired
    private DiscoveryClient discoveryClient;*/
    
    @Autowired
    LoadBalancerClient loadBalancer;

	@GetMapping("/getname/{empId}")
    public String getEmployeeName(@PathVariable("empId") final String empId) {
		/*List<ServiceInstance>instances=discoveryClient.getInstances("db-service");
        ServiceInstance serviceInstance=instances.get(0);*/
		ServiceInstance serviceInstance=loadBalancer.choose("db-service");
        String baseUrl=serviceInstance.getUri().toString();
        baseUrl=baseUrl+"/employee/"+empId;
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Employee> responseEntity=null;
        try
        {
        	responseEntity=restTemplate.exchange(baseUrl, HttpMethod.GET,getHeaders(),Employee.class);
        }
        catch(Exception e)
        {
        	System.out.println(e);
        }
		return responseEntity.getBody().getEmpName();
	}
	
	@GetMapping("/getnames")
    public List<String> getEmployeeNames() {
		/*List<ServiceInstance>instances=discoveryClient.getInstances("db-service");
        ServiceInstance serviceInstance=instances.get(0);*/
		ServiceInstance serviceInstance=loadBalancer.choose("db-service");
        String baseUrl=serviceInstance.getUri().toString();
        baseUrl=baseUrl+"/employee/";
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<List<Employee>> responseEntity=null;
        try
        {
        	responseEntity=restTemplate.exchange(baseUrl, HttpMethod.GET,getHeaders(),new ParameterizedTypeReference<List<Employee>>() {
            });
        }
        catch(Exception e)
        {
        	System.out.println(e);
        }
		return responseEntity.getBody()
				.stream()
				.map(employee->employee.getEmpName())
				.collect(Collectors.toList());
	}
	
	private static HttpEntity<?> getHeaders() throws IOException{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
