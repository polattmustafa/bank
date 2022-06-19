package com.banka.bank.repository;

import com.banka.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transaction WHERE to_account_id = ?1", nativeQuery = true)
    List<Transaction> getIslemTarihce(long accountId);
}
