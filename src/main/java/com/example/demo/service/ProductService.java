package com.example.demo.service;

import com.example.demo.response.ProductDetailResponse;
import com.example.demo.response.ProductsResponse;

public interface ProductService {

	ProductsResponse getProducts(String text);

	ProductDetailResponse getProductDetail(String url);

}
