package com.darlanmarangoni.financas.infrastructure.persistence.mapper;

import com.darlanmarangoni.financas.domain.model.Transaction;
import com.darlanmarangoni.financas.domain.model.TransactionType;
import com.darlanmarangoni.financas.infrastructure.persistence.entity.TransactionEntity;
import com.darlanmarangoni.financas.infrastructure.persistence.entity.TransactionTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    
    public Transaction toDomain(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Transaction transaction = new Transaction();
        transaction.setId(entity.getId());
        transaction.setDescription(entity.getDescription());
        transaction.setAmount(entity.getAmount());
        transaction.setCategory(entity.getCategory());
        transaction.setDate(entity.getDate());
        
        if (entity.getType() != null) {
            transaction.setType(TransactionType.valueOf(entity.getType().name()));
        }
        
        return transaction;
    }
    
    public TransactionEntity toEntity(Transaction domain) {
        if (domain == null) {
            return null;
        }
        
        TransactionEntity entity = new TransactionEntity();
        entity.setId(domain.getId());
        entity.setDescription(domain.getDescription());
        entity.setAmount(domain.getAmount());
        entity.setCategory(domain.getCategory());
        entity.setDate(domain.getDate());
        
        if (domain.getType() != null) {
            entity.setType(TransactionTypeEntity.valueOf(domain.getType().name()));
        }
        
        return entity;
    }
}
