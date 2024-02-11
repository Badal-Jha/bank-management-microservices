package com.nagarro.accountservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nagarro.accountservice.dto.CustomerResponse;

@FeignClient(name = "customer-service", path = "/api/customer")
public interface CustomerClient {
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("customerId") Long customerId);
}
