package com.darlanmarangoni.financas.infrastructure.rest.controller;

import com.darlanmarangoni.financas.domain.model.Transaction;
import com.darlanmarangoni.financas.domain.port.input.TransactionService;
import com.darlanmarangoni.financas.infrastructure.rest.dto.TransactionRequest;
import com.darlanmarangoni.financas.infrastructure.rest.dto.TransactionResponse;
import com.darlanmarangoni.financas.infrastructure.rest.mapper.TransactionDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    
    private final TransactionService transactionService;
    private final TransactionDtoMapper dtoMapper;
    
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest request) {
        Transaction transaction = dtoMapper.toDomain(request);
        Transaction created = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(dtoMapper.toResponse(created), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(transaction -> ResponseEntity.ok(dtoMapper.toResponse(transaction)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<TransactionResponse> transactions = transactionService.getAllTransactions().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactions);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Long id,
            @RequestBody TransactionRequest request) {
        try {
            Transaction transaction = dtoMapper.toDomain(request);
            Transaction updated = transactionService.updateTransaction(id, transaction);
            return ResponseEntity.ok(dtoMapper.toResponse(updated));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
