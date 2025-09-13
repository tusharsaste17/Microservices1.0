package com.example.myaccount.mapper;

import com.example.myaccount.dto.AccountsDto;
import com.example.myaccount.dto.CustomerDto;
import com.example.myaccount.entity.Account;
import com.example.myaccount.entity.Customer;

public class CustomerMapper {
  public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto){
     customerDto.setName(customer.getName());
     customerDto.setEmail(customer.getEmail());
     customerDto.setMobNumber(customer.getMobNumber());
      return customerDto;
  }
    public static Customer mapToCustomer(CustomerDto customerDto,Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobNumber(customerDto.getMobNumber());
        return customer;
    }
}
