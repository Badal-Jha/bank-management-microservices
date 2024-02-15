package com.nagarro.accountservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.accountservice.entities.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	void deleteAccountsByCustomerId(Long customerId);

}
