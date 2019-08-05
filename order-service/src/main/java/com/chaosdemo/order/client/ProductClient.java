package com.chaosdemo.order.client;

import com.chaosdemo.order.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductClient {

	@PutMapping("/products")
    Product update(@RequestBody Product product);
	
	@GetMapping("/products/{id}")
	Product findById(@PathVariable("id") Integer id);
	
}
