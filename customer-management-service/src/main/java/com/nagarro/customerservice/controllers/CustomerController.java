package com.nagarro.customerservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.customerservice.dto.CustomerDto;
import com.nagarro.customerservice.dto.CustomerResponse;
import com.nagarro.customerservice.services.CustomerServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
	private final CustomerServiceImpl customerService;

	// Add customer
	@PostMapping
	public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerDto customerDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customerDto));

	}

	@GetMapping
	public ResponseEntity<CustomerDto[]> getAllCustomers() {

		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
	}

	// Get single Customer Details
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") Long customerId) {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(customerId));
	}

	// Update Customer Details
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable("id") Long customerId,
			@RequestBody CustomerDto customerDto) {
		customerService.updateCustomer(customerId, customerDto);
		return ResponseEntity.status(HttpStatus.OK).body("Customer updated!!");
	}

	// Delete Customer (Deleting customer should also delete the customer account
	// from account management service
	
	@CircuitBreaker(name = "customerService", fallbackMethod = "deleteCustomerFallback")
	@Retry(name="customerService")
	@DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Long id) {
        this.customerService.deleteCustomer(id);
        System.out.println("calling delete account");
        return ResponseEntity.status(HttpStatus.OK).body("Customer with id= " + id + " deleted!!");
    }

    // Fallback method for deleteCustomer endpoint
    public ResponseEntity<String> deleteCustomerFallback(Long customerId, Throwable throwable) {
        String errorMessage = "oops Failed to delete customer with ID: " + customerId+" try after sometime";
        // Log the error message or perform any necessary fallback actions
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);}
}
