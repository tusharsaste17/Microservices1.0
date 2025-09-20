package com.example.myaccount.repository;

import com.example.myaccount.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {

    /**
     * @param customerId = customerId
    */
    Optional<Accounts> findByCustomerId(int customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(int customerId);
}
