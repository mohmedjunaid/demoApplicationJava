package com.example.demo.response;

import java.util.List;

import com.example.demo.model.ProductImage;

public class ProductDetailResponse extends BaseResponse{
	private String productName;
	private String productId;
	private String productLowPrice;
	private String productHighPrice;
	private String productDiscount;
	private List<ProductImage> productImages;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductLowPrice() {
		return productLowPrice;
	}
	public void setProductLowPrice(String productLowPrice) {
		this.productLowPrice = productLowPrice;
	}
	public String getProductHighPrice() {
		return productHighPrice;
	}
	public void setProductHighPrice(String productHighPrice) {
		this.productHighPrice = productHighPrice;
	}
	public String getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}
	public List<ProductImage> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}
	
	
}
