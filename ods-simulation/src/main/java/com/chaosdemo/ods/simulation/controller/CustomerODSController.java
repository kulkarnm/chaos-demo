package com.chaosdemo.ods.simulation.controller;

import com.chaosdemo.ods.simulation.model.Customer;
import com.chaosdemo.ods.simulation.repository.CustomerRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@Timed( value = "OdsCustomers" )
public class CustomerODSController {

    @Autowired
    CustomerRepository repository;

    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Integer id) {
        try {
            System.out.println("CustomerODSController--findById");
            return new ResponseEntity(repository.findById(id).get(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


} 
