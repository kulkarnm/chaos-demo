package com.chaosdemo.customer.controller;

import com.chaosdemo.feignclient.ODSCustomerClient;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaosdemo.customer.model.Customer;
import com.chaosdemo.customer.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@Timed ( value = "Customer" )
public class CustomerController {

	@Autowired
	ODSCustomerClient odsCustomerClient;

	@Autowired
	CustomerRepository repository;

	@PostMapping
	public  ResponseEntity<Customer> add(@RequestBody Customer customer) {
		try {
			repository.save(customer);
			return new ResponseEntity(odsCustomerClient.add(customer),HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping
	public  ResponseEntity<Customer> update(@RequestBody Customer customer) {
		try {
			repository.save(customer);
			return new ResponseEntity(odsCustomerClient.update(customer),HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity(odsCustomerClient.findById(id), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<Iterable<Customer>> findAll() {
		try {
			return new ResponseEntity(odsCustomerClient.findAll(), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
