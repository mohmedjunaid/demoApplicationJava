package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.annotation.ApiRestController;
import com.example.demo.request.ProductDetailRequest;
import com.example.demo.request.RegistrationRequest;
import com.example.demo.response.ProductDetailResponse;
import com.example.demo.response.ProductsResponse;
import com.example.demo.response.RegistrationResponse;
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
	
	@GetMapping("/getProductDetail")
	public ResponseEntity<ProductDetailResponse> getProductDetail(@RequestParam(name = "url") String url) {
		return new ResponseEntity<ProductDetailResponse>(productService.getProductDetail(url), HttpStatus.OK);
	}
}
