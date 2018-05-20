package com.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.service.HelloService;

@RestController
public class HelloWorldCotroller {

	@Autowired
	private HelloService helloService;
	
	@GetMapping(value="/api/v1/hello")
	public String sayHello(){
		return helloService.sayHello();
	}

}
