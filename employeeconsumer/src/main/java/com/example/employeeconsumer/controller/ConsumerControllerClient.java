package com.example.employeeconsumer.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConsumerControllerClient {
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@GetMapping(value="/getemployee")
	public String getEmployee(){
		
		ServiceInstance serviceInstance = loadBalancer.choose("employee-producer");
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl+="/employee";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = null;
		try{
			responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET,getHeaders(),String.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return responseEntity.getBody();
	}
	
	private static HttpEntity<?> getHeaders() throws IOException{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
