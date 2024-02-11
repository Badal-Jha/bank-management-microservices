package com.nagarro.customerservice.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.customerservice.dto.CustomerDto;
import com.nagarro.customerservice.dto.CustomerResponse;
import com.nagarro.customerservice.entities.Customer;
import com.nagarro.customerservice.repositories.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;

	@Override
	public CustomerResponse addCustomer(CustomerDto customerDto) {

		Customer customer = modelMapper.map(customerDto, Customer.class);

		CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
		customerRepository.save(customer);
		log.info("customer {} saved", customer);
		return customerResponse;
	}

	public CustomerDto[] getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();

		return modelMapper.map(customers, CustomerDto[].class);
	}

	public CustomerResponse getCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId).get();

		return modelMapper.map(customer, CustomerResponse.class);
	}

	public void updateCustomer(Long customerId, CustomerDto customerDto) {

		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new EntityNotFoundException("Customer not found"));

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

}
