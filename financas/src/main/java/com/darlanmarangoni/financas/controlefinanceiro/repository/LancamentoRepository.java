package com.darlanmarangoni.financas.controlefinanceiro.repository;

import com.darlanmarangoni.financas.controlefinanceiro.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, UUID> {
    List<Lancamento> findByData(LocalDate data);
    
    List<Lancamento> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
    
    List<Lancamento> findByCategoriaId(UUID categoriaId);
}
