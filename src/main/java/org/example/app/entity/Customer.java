package org.example.app.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private Long id;
    private String name;
    private String phone;
    private String address;

    public Customer(){

    }
    public Customer(String name, String phone, String address){
        this.address = address;
        this.phone = phone;
        this.name = name;

    }


}
