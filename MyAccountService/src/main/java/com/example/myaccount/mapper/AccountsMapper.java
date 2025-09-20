package com.example.myaccount.mapper;

import com.example.myaccount.dto.AccountsDto;
import com.example.myaccount.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts account, AccountsDto accountsDto){
        accountsDto.setAccountNumber(account.getAccountNumber());
        accountsDto.setAccountType(account.getAccountType());
        accountsDto.setBranchAddress(account.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccount(AccountsDto accountsDto,Accounts account){
        account.setAccountNumber(accountsDto.getAccountNumber());
        account.setBranchAddress(accountsDto.getBranchAddress());
        account.setAccountType(accountsDto.getAccountType());
        return account;
    }

}
