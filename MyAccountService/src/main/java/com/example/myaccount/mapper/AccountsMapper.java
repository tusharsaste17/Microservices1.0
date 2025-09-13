package com.example.myaccount.mapper;

import com.example.myaccount.dto.AccountsDto;
import com.example.myaccount.entity.Account;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Account account, AccountsDto accountsDto){
        accountsDto.setAccountNumber(account.getAccountNumber());
        accountsDto.setAccountType(account.getAccountType());
        accountsDto.setBranchAddress(account.getBranchAddress());
        return accountsDto;
    }

    public static Account mapToAccount(AccountsDto accountsDto,Account account){
        account.setAccountNumber(accountsDto.getAccountNumber());
        account.setBranchAddress(accountsDto.getBranchAddress());
        account.setAccountType(accountsDto.getAccountType());
        return account;
    }

}
