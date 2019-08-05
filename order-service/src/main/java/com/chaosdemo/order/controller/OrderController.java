package com.chaosdemo.order.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaosdemo.order.client.CustomerClient;
import com.chaosdemo.order.client.ProductClient;
import com.chaosdemo.order.model.Customer;
import com.chaosdemo.order.model.Order;
import com.chaosdemo.order.model.OrderStatus;
import com.chaosdemo.order.model.Product;
import com.chaosdemo.order.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
@Timed( value= "Order")
public class OrderController {

	@Autowired
	OrderRepository repository;
	@Autowired
	CustomerClient customerClient;
	@Autowired
	ProductClient productClient;
	
	@PostMapping
	public Order add(@RequestBody Order order) {
		System.out.println("@@@@@IN order service add method");
		Product product = productClient.findById(order.getProductId());
		Customer customer = customerClient.findById(order.getCustomerId());
		int totalPrice = order.getProductsCount() * product.getPrice();
		order.setId(UUID.randomUUID().hashCode());
		if (customer != null && customer.getAvailableFunds() >= totalPrice && product.getCount() >= order.getProductsCount()) {

			order.setPrice(totalPrice);
			order.setStatus(OrderStatus.ACCEPTED);
			product.setCount(product.getCount() - order.getProductsCount());
			productClient.update(product);
			customer.setAvailableFunds(customer.getAvailableFunds() - totalPrice);
			customerClient.update(customer);
		} else {
			order.setStatus(OrderStatus.REJECTED);
		}
		return repository.save(order);
	}
	
	@GetMapping("/{id}")
	public Order findById(@PathVariable("id") Integer id) {
		Optional<Order> order = repository.findById(id);
		if (order.isPresent()) {
			Order o = order.get();
			Product product = productClient.findById(o.getProductId());
			o.setProductName(product.getName());
			Customer customer = customerClient.findById(o.getCustomerId());
			o.setCustomerName(customer.getName());
			return o;
		} else {
			return null;
		}
	}
	
	@GetMapping("/customer/{customerId}")
	public List<Order> history(@PathVariable("customerId") Integer customerId) {
		return repository.findByCustomerId(customerId);
	}
	
}
