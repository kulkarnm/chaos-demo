package com.chaosdemo.ods.simulation.controller;

import com.chaosdemo.ods.simulation.model.Product;
import com.chaosdemo.ods.simulation.repository.ProductRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@Timed ( value= "ODSProduct")
public class ProductODSController {

	@Autowired
	ProductRepository repository;
	
	@PostMapping
	public ResponseEntity<Product> add(@RequestBody Product product) {
		try {
			repository.save(product);
			return new ResponseEntity(product, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<Product> update(@RequestBody Product product) {
		try {
			repository.save(product);
			return new ResponseEntity(product, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity(repository.findById(id).get(), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> findAll() {
		try {
			return new ResponseEntity(repository.findAll(), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
