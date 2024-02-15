package com.nagarro.customerservice.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.customerservice.dto.CustomerDto;
import com.nagarro.customerservice.dto.CustomerResponse;
import com.nagarro.customerservice.entities.Customer;
import com.nagarro.customerservice.exceptions.CustomException;
import com.nagarro.customerservice.feign.AccountClient;
import com.nagarro.customerservice.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;
	private final AccountClient accountClient;

	@Override
	public CustomerResponse addCustomer(CustomerDto customerDto) {

		try {
			Customer customer = modelMapper.map(customerDto, Customer.class);

			CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
			customerRepository.save(customer);
			log.info("customer {} saved", customer);
			return customerResponse;
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), 400);
		}
	}

	public CustomerDto[] getAllCustomers() {
		try {
			List<Customer> customers = customerRepository.findAll();
			return modelMapper.map(customers, CustomerDto[].class);
		} catch (EmptyResultDataAccessException e) {
			throw new CustomException("No customers found", HttpStatus.NOT_FOUND.value());
		} catch (Exception e) {
			throw new CustomException("Failed to fetch customers", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	public CustomerResponse getCustomer(Long customerId) {
		try {
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new CustomException("Customer not exist", HttpStatus.NOT_FOUND.value()));
			return modelMapper.map(customer, CustomerResponse.class);
		} catch (CustomException e) {

			throw e;
		} catch (Exception e) {
			throw new CustomException("Failed to fetch customer", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	public void updateCustomer(Long customerId, CustomerDto customerDto) {

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomException("customer not exist", HttpStatus.BAD_REQUEST.value()));

		if (customerDto.getAddress() != null) {
			customer.setAddress(customerDto.getAddress());
		}
		if (customerDto.getEmail() != null) {
			customer.setEmail(customerDto.getEmail());
		}
		if (customerDto.getContactNumber() != null) {
			customer.setContactNumber(customerDto.getContactNumber());
		}
		if (customerDto.getFirstName() != null) {
			customer.setFirstName(customerDto.getFirstName());
		}
		if (customerDto.getLastName() != null) {
			customer.setLastName(customerDto.getLastName());
		}

		customerRepository.save(customer);
	}

	public void deleteCustomer(Long customerId) {
		try {
			this.customerRepository.deleteById(customerId);

			// Also delete all the accounts associated with the customer
			try {
				this.accountClient.deleteAccounts(customerId);
			} catch (Exception e) {

				log.error("Failed to delete accounts for customer with ID {}: {}", customerId, e.getMessage());
				throw new CustomException("Failed to delete accounts for customer: " + e.getMessage(),
						HttpStatus.UNAUTHORIZED.value());
			}
		} catch (CustomException e) {

			throw e;
		} catch (Exception e) {

			log.error("Failed to delete customer with ID {}: {}", customerId, e.getMessage());
			throw new CustomException("Failed to delete customer", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

}
