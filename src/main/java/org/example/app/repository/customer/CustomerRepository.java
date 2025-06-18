package org.example.app.repository.customer;

import org.example.app.dto.CustomerDTOrequest;

import org.example.app.entity.Customer;
import org.example.app.repository.BaseRepository;

public interface CustomerRepository extends BaseRepository<CustomerDTOrequest, Customer> {
}
