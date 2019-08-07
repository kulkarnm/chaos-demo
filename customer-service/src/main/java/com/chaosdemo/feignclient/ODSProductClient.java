package com.chaosdemo.feignclient;

import com.chaosdemo.customer.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ods-simulation-service")
public interface ODSProductClient {
    @PostMapping("/products")
    public Customer add(@RequestBody Customer customer);
    @GetMapping("/products/{id}")
    public Customer findById(@PathVariable("id") Integer id);
    @PutMapping("/products")
    public Customer update(@RequestBody Customer customer);

}
