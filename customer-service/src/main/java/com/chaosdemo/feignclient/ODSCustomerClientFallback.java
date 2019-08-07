package com.chaosdemo.feignclient;

import com.chaosdemo.customer.model.Customer;
import com.chaosdemo.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
@Component
public class ODSCustomerClientFallback implements ODSCustomerClient {
        @Autowired
        CustomerRepository repository;

        public Customer add(@RequestBody Customer customer) {
            return repository.save(customer);
        }

        public Customer update(@RequestBody Customer customer) {
            return repository.save(customer);
        }

        public Customer findById(@PathVariable("id") Integer id) {
            return repository.findById(id).get();
        }

}
