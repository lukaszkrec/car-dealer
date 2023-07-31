package com.cardealer.business.dao;

import com.cardealer.domain.Customer;

import java.util.Optional;

public interface CustomerDAO {

    Optional<Customer> findByEmail(String email);

    void issueInvoice(Customer customer);

    void saveServiceRequest(Customer customer);

    Customer saveCustomer(Customer customer);
}
