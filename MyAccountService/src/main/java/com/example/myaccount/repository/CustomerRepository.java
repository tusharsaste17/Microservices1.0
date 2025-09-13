package com.example.myaccount.repository;

import com.example.myaccount.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

   Optional<Customer> findByMobNumber(String mobNumber);
}
