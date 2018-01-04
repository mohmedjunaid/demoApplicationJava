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
import com.example.demo.response.ProductsResponse;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public ProductsResponse getProducts(String text) {
		ProductsResponse response = new ProductsResponse();
		System.out.println("service");
		try {
			/* Document doc = Jsoup.connect("http://www.shopclues.com/").get(); */
			Response response1 = Jsoup.connect("http://www.shopclues.com/search?q=" + text).method(Method.GET)
					.execute();
			Document doc = response1.parse();
			System.out.println(doc.title());
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
}