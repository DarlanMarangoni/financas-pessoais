package com.darlanmarangoni.financas.infrastructure.persistence.repository;

import com.darlanmarangoni.financas.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
