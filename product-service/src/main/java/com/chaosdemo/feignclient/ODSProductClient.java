package com.chaosdemo.feignclient;

import com.chaosdemo.product.model.Product;
import com.chaosdemo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ods-simulation-service", fallback = ODSProductClient.ODSProductClientFallBack.class)
public interface ODSProductClient {
    @PostMapping("/products")
    public Product add(@RequestBody Product product);
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer id);
    @PutMapping("/products")
    public Product update(@RequestBody Product product);
    @GetMapping
    public Iterable<Product> findAll();

    @Component
    public static class ODSProductClientFallBack implements ODSProductClient {
        @Autowired
        ProductRepository repository;

        @PostMapping
        public Product add(@RequestBody Product product) {
            return repository.save(product);
        }

        @PutMapping
        public Product update(@RequestBody Product product) {
            return repository.save(product);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {

            return new ResponseEntity<Product>(repository.findById(id).get(), HttpStatus.OK);
        }

        @GetMapping
        public Iterable<Product> findAll() {
            return repository.findAll();
        }

    }
}
