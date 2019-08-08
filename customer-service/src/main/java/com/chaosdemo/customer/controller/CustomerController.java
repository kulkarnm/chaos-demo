package com.chaosdemo.customer.controller;

import com.chaosdemo.feignclient.ODSCustomerClient;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping
	public List<Customer> add(@RequestBody List<Customer> customers) {
		return customers.stream().map(
				customer -> odsCustomerClient.add(customer)).collect(Collectors.toList());
	}
	
	@PutMapping
	public Customer update(@RequestBody Customer customer) {
		return odsCustomerClient.update(customer);
	}
	
	@GetMapping("/{id}")
	public Customer findById(@PathVariable("id") Integer id) {
		return odsCustomerClient.findById(id);
	}
	
}
