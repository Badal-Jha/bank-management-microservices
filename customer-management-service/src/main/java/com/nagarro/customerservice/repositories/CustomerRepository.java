package com.nagarro.customerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.customerservice.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
