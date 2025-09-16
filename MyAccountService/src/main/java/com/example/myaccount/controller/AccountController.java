package com.example.myaccount.controller;

import com.example.myaccount.constants.AccountConstants;
import com.example.myaccount.dto.CustomerDto;
import com.example.myaccount.dto.ResponseDto;
import com.example.myaccount.entity.Customer;
import com.example.myaccount.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountsService iAccountsService;

   @GetMapping("/hello")
    public String sayHello(){
        return "hello world..!!";
    }

    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetchDetail")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})", message = "mob number should be 10 digit")
            String mobNumber){
        CustomerDto customerDto = iAccountsService.fetchCustomer(mobNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @GetMapping("/fetchAllCustomers")
    public ResponseEntity<List<Customer>> fetchAllCustomerDetails(){
        List<Customer> customers = iAccountsService.fetchAllCustomersList();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @PutMapping("/updateDetails")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<ResponseDto> deleteAccountDetails(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})", message="mob number should be 10 digit")
            String mobNumber){
        boolean isDeleted = iAccountsService.deleteAccount(mobNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }
    }
}
