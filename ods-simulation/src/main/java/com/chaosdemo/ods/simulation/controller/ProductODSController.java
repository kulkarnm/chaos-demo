package com.chaosdemo.ods.simulation.controller;

import com.chaosdemo.ods.simulation.model.Product;
import com.chaosdemo.ods.simulation.repository.ProductRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@Timed ( value= "ODSProduct")
public class ProductODSController {

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
