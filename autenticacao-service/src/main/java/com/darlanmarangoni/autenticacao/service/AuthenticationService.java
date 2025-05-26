package com.darlanmarangoni.autenticacao.service;

import com.darlanmarangoni.autenticacao.config.JwtService;
import com.darlanmarangoni.autenticacao.dto.AuthenticationRequest;
import com.darlanmarangoni.autenticacao.dto.AuthenticationResponse;
import com.darlanmarangoni.autenticacao.dto.UsuarioDTO;
import com.darlanmarangoni.autenticacao.exception.CredenciaisInvalidasException;
import com.darlanmarangoni.autenticacao.model.Usuario;
import com.darlanmarangoni.autenticacao.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getSenha()
                    )
            );
        } catch (AuthenticationException e) {
            throw new CredenciaisInvalidasException("Credenciais inválidas");
        }

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        usuario.setUltimoAcesso(LocalDateTime.now());
        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario);
        
        return AuthenticationResponse.builder()
                .token(token)
                .usuario(mapToDTO(usuario))
                .build();
    }
    
    private UsuarioDTO mapToDTO(Usuario usuario) {
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
