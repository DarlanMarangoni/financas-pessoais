package com.darlanmarangoni.financas.autenticacao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoUsuarioRequest {
    
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    private String nome;
    
    @Email(message = "Formato de email inválido")
    private String email;
    
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    private String senha;
    
    private Boolean ativo;
}
