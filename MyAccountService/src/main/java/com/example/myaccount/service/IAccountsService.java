package com.example.myaccount.service;

import com.example.myaccount.dto.CustomerDto;
import com.example.myaccount.entity.Customer;

import java.util.List;

public interface IAccountsService {
/**
 * @param customerDto - CustomerDto object
 *
*/
    void createAccount(CustomerDto customerDto);
    /**
    * @param mobNumber - input mobile number
    * @return account details based on Mobile number
    */
    public CustomerDto fetchCustomer(String mobNumber);

    /**
     * @param customerDto - CustomerDto object
     * @return boolean including if the update of Account detail is successfully or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * @get all customers detail list
     */
    List<Customer> fetchAllCustomersList();

    /**
     * @param mobNumber - input mobile number
     * @return boolean including if the delete of Account detail is successfully or not
     */
    boolean deleteAccount(String mobNumber);

}
