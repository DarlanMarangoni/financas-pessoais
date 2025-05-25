package com.darlanmarangoni.financas.infrastructure.rest.mapper;

import com.darlanmarangoni.financas.domain.model.Transaction;
import com.darlanmarangoni.financas.domain.model.TransactionType;
import com.darlanmarangoni.financas.infrastructure.rest.dto.TransactionRequest;
import com.darlanmarangoni.financas.infrastructure.rest.dto.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoMapper {
    
    public Transaction toDomain(TransactionRequest request) {
        if (request == null) {
            return null;
        }
        
        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setCategory(request.getCategory());
        transaction.setDate(request.getDate());
        
        if (request.getType() != null) {
            transaction.setType(TransactionType.valueOf(request.getType()));
        }
        
        return transaction;
    }
    
    public TransactionResponse toResponse(Transaction domain) {
        if (domain == null) {
            return null;
        }
        
        TransactionResponse response = new TransactionResponse();
        response.setId(domain.getId());
        response.setDescription(domain.getDescription());
        response.setAmount(domain.getAmount());
        response.setCategory(domain.getCategory());
        response.setDate(domain.getDate());
        
        if (domain.getType() != null) {
            response.setType(domain.getType().name());
        }
        
        return response;
    }
}
