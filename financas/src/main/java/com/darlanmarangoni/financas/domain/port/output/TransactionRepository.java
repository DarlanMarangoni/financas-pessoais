package com.darlanmarangoni.financas.domain.port.output;

import com.darlanmarangoni.financas.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    List<Transaction> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
