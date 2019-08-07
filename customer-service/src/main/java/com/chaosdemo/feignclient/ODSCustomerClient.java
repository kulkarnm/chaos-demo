package com.chaosdemo.feignclient;

import com.chaosdemo.customer.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ods-simulation-service")
public interface ODSCustomerClient {
    @PostMapping("/customers")
    public Customer add(@RequestBody Customer customer);
    @GetMapping("/customers/{id}")
    public Customer findById(@PathVariable("id") Integer id);
    @PutMapping("/customers")
    public Customer update(@RequestBody Customer customer);

}
