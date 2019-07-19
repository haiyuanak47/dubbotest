package com.lhy.common.service;

import com.lhy.common.entity.Customer;

import java.util.List;

public interface CustomerService {


	public int saveCustomer(Customer user);

	public List<Customer> getList();

	public Customer getCustomer(long userId);
	
}

