package com.example.myaccount.service.impl;

import com.example.myaccount.constants.AccountConstants;
import com.example.myaccount.dto.CustomerDto;
import com.example.myaccount.entity.Account;
import com.example.myaccount.entity.Customer;
import com.example.myaccount.exception.CustomerAlreadyExistsException;
import com.example.myaccount.mapper.CustomerMapper;
import com.example.myaccount.repository.AccountRepository;
import com.example.myaccount.repository.CustomerRepository;
import com.example.myaccount.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer =customerRepository.findByMobNumber(customer.getMobNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer is already exist by given mob number "
                    +customerDto.getMobNumber());
        }
        Customer savedCustomer =  customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer){
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        int randomAccNumber = 100000 + new Random().nextInt(90000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;

        //System.out.println("test");
    }
}
