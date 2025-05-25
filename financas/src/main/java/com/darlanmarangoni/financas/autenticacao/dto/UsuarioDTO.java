package com.darlanmarangoni.financas.autenticacao.dto;

import com.darlanmarangoni.financas.autenticacao.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    
    private Long id;
    private String nome;
    private String email;
    private Role role;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimoAcesso;
    private boolean ativo;
}
