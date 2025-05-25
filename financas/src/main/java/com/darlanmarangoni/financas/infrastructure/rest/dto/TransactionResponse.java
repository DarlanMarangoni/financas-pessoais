package com.darlanmarangoni.financas.infrastructure.rest.dto;

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
public class TransactionResponse {
    private Long id;
    private String description;
    private BigDecimal amount;
    private String type;
    private String category;
    private LocalDate date;
}
