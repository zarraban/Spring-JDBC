package org.example.app.controller.customer;


import org.example.app.dto.AppError;
import org.example.app.dto.AppSuccess;
import org.example.app.dto.CustomerDTOrequest;
import org.example.app.entity.Customer;
import org.example.app.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(@Qualifier("customerService") CustomerService customerService){
        this.customerService = customerService;

    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> list = customerService.readAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(),
                            "There are no customers saved!"),
                    HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/last")
    public ResponseEntity<?> getLastCustomers(){
        Customer customer = customerService.getLastEntity();
        if(customer == null){
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(),
                            "There are no customers saved!"),
                    HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.readById(id);
        if (customer == null) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(),
                            "There is no customer with id= " + id),
                    HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("id") Long id){
        boolean result = customerService.deleteById(id);
        if (!result) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(),
                            "There is no customer with id " + id),
                    HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new AppSuccess(HttpStatus.OK.value(), "Successfully deleted user with id= "+ id), HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTOrequest customerDTOrequest){
        boolean result = customerService.create(customerDTOrequest);
        if (!result) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Couldn't add customer"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(new AppSuccess(HttpStatus.OK.value(), "Successfully created customer"), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTOrequest customerDTOrequest){
        boolean result = customerService.updateById(id, customerDTOrequest);
        if (!result) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Couldn't update customer with id "+ id),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(new AppSuccess(HttpStatus.OK.value(), "Customer with id= "+id+" successfully updated"), HttpStatus.OK);
        }
    }

}
