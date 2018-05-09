package com.spring.rest.controlller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldCotroller {

	@GetMapping(value="/hello")
	public String sayHello(){
		return "Hello World !";
	}
	
}
