package com.example.myaccount.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity{
    @Id
    @Column
    private int accountNumber;
    @Column
    private String accountType;
    @Column
    private String branchAddress;
    @Column
    private int customerId;

}
