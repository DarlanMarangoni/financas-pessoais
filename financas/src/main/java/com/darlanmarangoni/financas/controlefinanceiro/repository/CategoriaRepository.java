package com.darlanmarangoni.financas.controlefinanceiro.repository;

import com.darlanmarangoni.financas.controlefinanceiro.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    boolean existsByNome(String nome);
}
