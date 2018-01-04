package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.annotation.ApiRestController;
import com.example.demo.response.ProductsResponse;
import com.example.demo.service.ProductService;

@ApiRestController
public class ProductController{
	@Autowired
	private ProductService productService;
	
	@GetMapping("/getProducts")
	public ResponseEntity<ProductsResponse> getProducts(@RequestParam(name = "text") String text) {
		System.out.println("d="+text);
		return new ResponseEntity(productService.getProducts(text), HttpStatus.OK);
	}
}
