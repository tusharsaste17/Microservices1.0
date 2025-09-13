package com.example.myaccount.controller;

import com.example.myaccount.constants.AccountConstants;
import com.example.myaccount.dto.CustomerDto;
import com.example.myaccount.dto.ResponseDto;
import com.example.myaccount.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountController {

    private IAccountsService iAccountsService;

   /* @GetMapping("/hello")
    public String sayHello(){
        return "hello world..!!";
    }*/

    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }
}
