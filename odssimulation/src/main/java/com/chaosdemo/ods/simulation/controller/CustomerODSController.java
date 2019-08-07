package com.chaosdemo.ods.simulation.controller;

import com.chaosdemo.ods.simulation.model.Customer;
import com.chaosdemo.ods.simulation.repository.CustomerRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odscustomers")
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
    public Customer findById(@PathVariable("id") Integer id) {
        return repository.findById(id).get();
    }

} 
