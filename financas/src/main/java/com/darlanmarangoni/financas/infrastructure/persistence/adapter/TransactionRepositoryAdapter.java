package com.darlanmarangoni.financas.infrastructure.persistence.adapter;

import com.darlanmarangoni.financas.domain.model.Transaction;
import com.darlanmarangoni.financas.domain.port.output.TransactionRepository;
import com.darlanmarangoni.financas.infrastructure.persistence.mapper.TransactionMapper;
import com.darlanmarangoni.financas.infrastructure.persistence.repository.JpaTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepository {
    
    private final JpaTransactionRepository jpaRepository;
    private final TransactionMapper mapper;
    
    @Override
    public Transaction save(Transaction transaction) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(transaction)));
    }
    
    @Override
    public Optional<Transaction> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
    
    @Override
    public List<Transaction> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
