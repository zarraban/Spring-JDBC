package org.example.app.service.customer;

import lombok.extern.slf4j.Slf4j;
import org.example.app.dto.CustomerDTOrequest;
import org.example.app.entity.Customer;
import org.example.app.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service("customerService")
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(@Qualifier("customerRepository") CustomerRepository customerRepository){
    this.repository = customerRepository;
    }


    @Override
    @Transactional
    public boolean create(CustomerDTOrequest request) {
        log.info("Start creating customer");
        boolean result = repository.create(request);
        if(result){
            log.info("Successfully created customer");
            return true;
        }
        else {
            log.info("Not successfully created customer");
            return false;
        }
    }

    @Override
    public List<Customer> readAll() {
        log.info("Start reading customers");
        return repository.read().orElse(Collections.emptyList());
    }

    @Override
    public Customer readById(Long id) {
        log.info("Start reading customer by id: {}", id);
        return repository.readById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        log.info("Start deleting customer by id: {}", id);
        return repository.deleteById(id);
    }


    @Override
    @Transactional
    public boolean updateById(Long id, CustomerDTOrequest request) {
        log.info("Start updating customer by id: {}", id);
        return repository.updateById(id, request);
    }

    @Override
    public Customer getLastEntity() {
        log.info("Start getting last customer");
        return repository.getLastEntity().orElse(null);
    }
}
