package com.chaosdemo.product.controller;

import com.chaosdemo.feignclient.ODSProductClient;
import com.chaosdemo.product.model.Product;
import com.chaosdemo.product.repository.ProductRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Timed(value = "Product")
public class ProductController {

    @Autowired
    ODSProductClient odsProductClient;

    @Autowired
    ProductRepository repository;

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product) {
        Product product1 = null;
        try {
            repository.save(product);
            product1 = odsProductClient.add(product);
            return new ResponseEntity(product1,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/all")
    public  ResponseEntity<List<Product>> add(@RequestBody List<Product> products) {
        try {
            for(Product c: products) {
                repository.save(c);
                odsProductClient.add(c);
            }
            return new ResponseEntity(products,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {
        Product product1 = null;
        try {
            repository.save(product);
            product1 = odsProductClient.add(product);
            return new ResponseEntity(product1,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable<Product> products = null;
        try {
            products= odsProductClient.findAll();
            return new ResponseEntity(products,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
