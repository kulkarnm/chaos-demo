package com.chaosdemo.feignclient;

import com.chaosdemo.customer.model.Customer;
import com.chaosdemo.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ods-simulation-service", fallback = ODSCustomerClient.ODSCustomerClientFallBack.class)
public interface ODSCustomerClient {
    @PostMapping("/customers")
    public Customer add(@RequestBody Customer customer);
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Integer id);
    @PutMapping("/customers")
    public Customer update(@RequestBody Customer customer);
    @Component
    public static class ODSCustomerClientFallBack implements ODSCustomerClient {
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
            System.out.println("Customer CHAOSDB--findById");
            return new ResponseEntity<Customer>(repository.findById(id).get(), HttpStatus.OK);
        }
    }
}
