package com.badal.customerservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.badal.customerservice.dto.AccountDto;

@FeignClient(name = "account-service", path = "/api/account")
public interface AccountClient {

	@PostMapping
	public ResponseEntity<String> createAccount(@RequestBody AccountDto accountDto);

	@DeleteMapping("/customer/{customerId}")
	public void deleteAccounts(@PathVariable("customerId") Long customerId);
}
