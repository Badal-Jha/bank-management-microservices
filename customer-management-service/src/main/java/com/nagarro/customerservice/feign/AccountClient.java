package com.nagarro.customerservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nagarro.customerservice.dto.AccountDto;

@FeignClient(name = "account-service", path = "/api/account")
public interface AccountClient {

	@PostMapping
	public ResponseEntity<String> createAccount(@RequestBody AccountDto accountDto);
}
