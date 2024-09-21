package com.badal.customerservice.services;

import com.badal.customerservice.dto.CustomerDto;
import com.badal.customerservice.dto.CustomerResponse;

public interface CustomerService {

	CustomerResponse addCustomer(CustomerDto customerDto);
}
