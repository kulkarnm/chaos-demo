package com.chaosdemo.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chaosdemo.order.model.Customer;

@FeignClient(name = "customer-service")
public interface CustomerClient {

	@GetMapping("/customers/{id}")
	public Customer findById(@PathVariable("id") Integer id);
	@PutMapping("/customers")
	public Customer update(@RequestBody Customer customer);
	
}
