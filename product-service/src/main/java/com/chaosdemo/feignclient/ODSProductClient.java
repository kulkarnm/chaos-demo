package com.chaosdemo.feignclient;

import com.chaosdemo.product.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ods-product-service")
public interface ODSProductClient {
    @PostMapping
    public Product add(@RequestBody Product customer);
    @PutMapping("/products")
    Product update(@RequestBody Product product);

    @GetMapping("/products/{id}")
    Product findById(@PathVariable("id") Integer id);

}
