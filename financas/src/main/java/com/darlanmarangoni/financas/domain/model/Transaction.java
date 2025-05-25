package com.darlanmarangoni.financas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private String category;
    private LocalDate date;
}
