package com.spring.rest.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	public String sayHello() {
		return "Hello World!";
	}

}
