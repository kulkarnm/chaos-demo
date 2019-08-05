package com.chaosdemo.product.repository;

import java.util.List;

import com.chaosdemo.product.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByName(String name);
	
}
