package com.chaosdemo.product.controller;

import com.chaosdemo.feignclient.ODSProductClient;
import com.chaosdemo.product.model.Product;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Timed(value = "Product")
public class ProductController {

    @Autowired
    ODSProductClient odsProductClient;

    @PostMapping
    public Product add(@RequestBody Product product) {
        return odsProductClient.add(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return odsProductClient.add(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(odsProductClient.findById(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return odsProductClient.findAll();
    }

}
