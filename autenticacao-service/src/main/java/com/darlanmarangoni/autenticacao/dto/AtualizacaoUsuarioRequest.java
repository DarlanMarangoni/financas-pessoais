package com.darlanmarangoni.autenticacao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizacaoUsuarioRequest {
    
    private String nome;
    
    @Email(message = "Email inválido")
    private String email;
    
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;
    
    private Boolean ativo;
}
