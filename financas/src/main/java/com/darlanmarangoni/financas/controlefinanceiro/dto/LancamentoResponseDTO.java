package com.darlanmarangoni.financas.controlefinanceiro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoResponseDTO {
    
    private UUID id;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    
    private String descricao;
    
    private BigDecimal valorPrevisto;
    
    private BigDecimal valorRealizado;
    
    private CategoriaResponseDTO categoria;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCadastro;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAtualizacao;

}
