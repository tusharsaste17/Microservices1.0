package com.example.myaccount.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="customer")
@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int customerId;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String mobNumber;
}
