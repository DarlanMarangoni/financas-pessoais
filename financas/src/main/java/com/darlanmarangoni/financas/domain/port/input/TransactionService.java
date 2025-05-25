package com.darlanmarangoni.financas.domain.port.input;

import com.darlanmarangoni.financas.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Optional<Transaction> getTransactionById(Long id);
    List<Transaction> getAllTransactions();
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
}
