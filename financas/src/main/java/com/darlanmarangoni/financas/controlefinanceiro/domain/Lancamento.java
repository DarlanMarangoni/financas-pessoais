package com.darlanmarangoni.financas.controlefinanceiro.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {
    @Id
    @Column(nullable = false)
    private UUID id;
    
    @Column(name = "data", nullable = false)
    private LocalDate data;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Column(name = "valor_previsto")
    private BigDecimal valorPrevisto;
    
    @Column(name = "valor_realizado")
    private BigDecimal valorRealizado;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;
    
    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
