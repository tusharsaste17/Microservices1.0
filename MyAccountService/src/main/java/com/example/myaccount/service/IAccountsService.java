package com.example.myaccount.service;

import com.example.myaccount.dto.CustomerDto;

public interface IAccountsService {
/**
 * @param customerDto - CustomerDto object
 *
*/
    void createAccount(CustomerDto customerDto);

}
