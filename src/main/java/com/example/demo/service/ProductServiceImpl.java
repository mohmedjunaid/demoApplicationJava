package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ResponseStatus;
import com.example.demo.model.Product;
import com.example.demo.model.ProductImage;
import com.example.demo.request.ProductDetailRequest;
import com.example.demo.response.ProductDetailResponse;
import com.example.demo.response.ProductsResponse;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public ProductsResponse getProducts(String text) {
		ProductsResponse response = new ProductsResponse();
		
		try {
			/* Document doc = Jsoup.connect("http://www.shopclues.com/").get(); */
			Response response1 = Jsoup.connect("http://www.shopclues.com/search?q=" + text).method(Method.GET)
					.execute();
			Document doc = response1.parse();
			
			Elements newsHeadlines = doc.getElementsByClass("column col3 search_blocks");
			List<Product> productList = new ArrayList<>();
			for (Element headline : newsHeadlines) {
				Product product = new Product();
				Element productName = headline.getElementsByTag("h2").get(0);
				Element productImage1 = headline.getElementsByClass("img_section").get(0);
				Element productImage = productImage1.getElementsByTag("img").get(0);
				Element productPrice1 = headline.getElementsByClass("prd_p_section").get(0);
				Element productPrice = productPrice1.getElementsByClass("p_price").get(0);
				product.setProductName(productName.text());
				product.setProductPrice(productPrice.text());
				product.setProductImage(productImage.absUrl("src"));
				
				Element productURL = headline.getElementsByTag("a").get(0);
				product.setProductURL(productURL.absUrl("href"));
				productList.add(product);
			}
			response.setProducts(productList);
			response.setStatus(ResponseStatus.SUCCESS);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			response.setProducts(null);
			response.setStatus(ResponseStatus.SUCCESS);
			return response;
		}
	}

	@Override
	public ProductDetailResponse getProductDetail(String url) {
		ProductDetailResponse response=new ProductDetailResponse();
		Response response1;
		try {
			response1 = Jsoup.connect(url).method(Method.GET)
					.execute();
			Document doc = response1.parse();
			
			Elements newsHeadlines = doc.getElementsByClass("prd_mid_info");
			
			for (Element headline : newsHeadlines) {
				
				Element productName = headline.getElementsByTag("h1").get(0);
				Element productId = headline.getElementsByClass("pID").get(0);
				Element productLowPrice = headline.getElementsByClass("f_price").get(0);
				Element productHighPrice = headline.getElementsByClass("o_price").get(0);
				Element productDiscount = headline.getElementsByClass("discount").get(0);
				response.setProductName(productName.text());
				response.setProductId(productId.text());
				response.setProductLowPrice(productLowPrice.text());
				response.setProductHighPrice(productHighPrice.text());
				response.setProductDiscount(productDiscount.text());
				
				try {
					List<ProductImage> productImagesList=new ArrayList<>();
					Element productImagesElement = doc.getElementById("thumblist");
					Elements productImages= productImagesElement.getElementsByTag("a");
					for(Element productImage: productImages) {
						ProductImage image=new ProductImage();
						image.setThumbnail(productImage.absUrl("data-image"));
						image.setImage(productImage.absUrl("data-zoom-image"));
						productImagesList.add(image);
					}
					response.setProductImages(productImagesList);
				} catch (NullPointerException e) {
					
				}
				
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		response.setStatus(ResponseStatus.SUCCESS);
		System.out.println("f="+url);
		return response;
	}
}