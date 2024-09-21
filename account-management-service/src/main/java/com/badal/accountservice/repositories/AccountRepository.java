package com.badal.accountservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badal.accountservice.entities.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	void deleteAccountsByCustomerId(Long customerId);

}
