package com.example.demo.response;

import java.util.List;

import com.example.demo.model.Product;

public class ProductsResponse extends BaseResponse{

	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
