package com.badal.customerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badal.customerservice.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
