package com.example.myaccount.service.impl;

import com.example.myaccount.constants.AccountConstants;
import com.example.myaccount.dto.AccountsDto;
import com.example.myaccount.dto.CustomerDto;
import com.example.myaccount.entity.Account;
import com.example.myaccount.entity.Customer;
import com.example.myaccount.exception.CustomerAlreadyExistsException;
import com.example.myaccount.exception.ResourceNotFoundException;
import com.example.myaccount.mapper.AccountsMapper;
import com.example.myaccount.mapper.CustomerMapper;
import com.example.myaccount.repository.AccountRepository;
import com.example.myaccount.repository.CustomerRepository;
import com.example.myaccount.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto object
     *
     */
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
    }

    /**
     * @param mobNumber - input mobile number
     * @return account details based on Mobile number
     */
    @Override
    public CustomerDto fetchCustomer(String mobNumber) {
        Customer customer =customerRepository.findByMobNumber(mobNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","Mobile Number",mobNumber)
        );

        Account account =accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Customer","Mobile Number", mobNumber)
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null){
           Account account = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account","AccountNumber",String.valueOf(accountsDto.getAccountNumber()))
            );
            AccountsMapper.mapToAccount(accountsDto,account);
            account = accountRepository.save(account);

            int customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer","CustomerId",String.valueOf(customerId))
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @get all customers detail list
     */
    @Override
    public List<Customer> fetchAllCustomersList() {
        return customerRepository.findAll();
    }

    /**
     * @param mobNumber - input mobile number
     * @return boolean including if the delete of Account detail is successfully or not
     */
    @Override
    public boolean deleteAccount(String mobNumber) {
        Customer customer =customerRepository.findByMobNumber(mobNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","Mobile Number",mobNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
