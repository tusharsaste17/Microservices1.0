package com.example.myaccount.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDto {
    @NotEmpty(message = "name can not be null or empty")
    private  String name;
    @Pattern(regexp="(^$|[0-9]{10})",message = "mobile number must be 10 digits")
    private String mobNumber;
    @NotEmpty(message = "name can not be null or empty")
    @Email(message = "email address should be valid value")
    private String email;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private AccountsDto accountsDto;
}
