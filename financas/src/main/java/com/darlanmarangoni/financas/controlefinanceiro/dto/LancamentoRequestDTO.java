package com.darlanmarangoni.financas.controlefinanceiro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoRequestDTO {

    @NotNull(message = "A data é obrigatória")
    @PastOrPresent(message = "A data não pode ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 3, max = 255, message = "A descrição deve ter entre 3 e 255 caracteres")
    private String descricao;

    private BigDecimal valorPrevisto;

    private BigDecimal valorRealizado;

    private UUID categoriaId;
}
