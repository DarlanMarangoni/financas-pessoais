package com.darlanmarangoni.financas.autenticacao.service;

import com.darlanmarangoni.financas.autenticacao.config.JwtService;
import com.darlanmarangoni.financas.autenticacao.dto.LoginRequest;
import com.darlanmarangoni.financas.autenticacao.dto.LoginResponse;
import com.darlanmarangoni.financas.autenticacao.dto.UsuarioDTO;
import com.darlanmarangoni.financas.autenticacao.model.Usuario;
import com.darlanmarangoni.financas.autenticacao.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );
        
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow();
        
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuarioRepository.save(usuario);
        
        String jwtToken = jwtService.generateToken(usuario);
        
        return LoginResponse.builder()
                .token(jwtToken)
                .usuario(mapToUsuarioDTO(usuario))
                .build();
    }
    
    private UsuarioDTO mapToUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .dataCriacao(usuario.getDataCriacao())
                .ultimoAcesso(usuario.getUltimoAcesso())
                .ativo(usuario.isAtivo())
                .build();
    }
}
