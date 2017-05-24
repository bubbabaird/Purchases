package com.theironyard.charlotte;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
//    Customer findByName(String currentCustomer);

//    Customer findByEmail(String currentEmail);
}
