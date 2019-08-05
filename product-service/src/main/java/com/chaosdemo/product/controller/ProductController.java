package com.chaosdemo.product.controller;

import com.chaosdemo.product.model.Product;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaosdemo.product.repository.ProductRepository;

@RestController
@RequestMapping("/products")
@Timed ( value= "Product")
public class ProductController {

	@Autowired
	ProductRepository repository;
	
	@PostMapping
	public Product add(@RequestBody Product product) {
		return repository.save(product);
	}
	
	@PutMapping
	public Product update(@RequestBody Product product) {
		return repository.save(product);
	}
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable("id") Integer id) {
		return repository.findById(id).get();
	}
	
	@GetMapping
	public Iterable<Product> findAll() {
		return repository.findAll();
	}
	
}