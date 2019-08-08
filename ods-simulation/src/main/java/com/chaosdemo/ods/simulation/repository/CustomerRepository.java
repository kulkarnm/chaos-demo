package com.chaosdemo.ods.simulation.repository;

import com.chaosdemo.ods.simulation.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	List<Customer> findByName(String name);
	
}
