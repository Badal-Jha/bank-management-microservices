package com.nagarro.customerservice.services;

import com.nagarro.customerservice.dto.CustomerDto;
import com.nagarro.customerservice.dto.CustomerResponse;

public interface CustomerService {

	CustomerResponse addCustomer(CustomerDto customerDto);
}
