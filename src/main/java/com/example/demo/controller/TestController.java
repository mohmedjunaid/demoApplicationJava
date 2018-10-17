package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(value = "",method = RequestMethod.GET)
	private String getTest() {
		System.out.println("test");
		return "index";
	}
}
