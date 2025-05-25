package com.darlanmarangoni.financas.application.service;

import com.darlanmarangoni.financas.domain.model.Transaction;
import com.darlanmarangoni.financas.domain.port.input.TransactionService;
import com.darlanmarangoni.financas.domain.port.output.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    
    private final TransactionRepository transactionRepository;
    
    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }
    
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {
        if (!transactionRepository.existsById(id)) {
            throw new NoSuchElementException("Transaction not found with id: " + id);
        }
        transaction.setId(id);
        return transactionRepository.save(transaction);
    }
    
    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new NoSuchElementException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
