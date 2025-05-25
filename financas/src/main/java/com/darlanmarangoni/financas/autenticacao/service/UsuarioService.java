package com.darlanmarangoni.financas.autenticacao.service;

import com.darlanmarangoni.financas.autenticacao.dto.AtualizacaoUsuarioRequest;
import com.darlanmarangoni.financas.autenticacao.dto.CadastroUsuarioRequest;
import com.darlanmarangoni.financas.autenticacao.dto.UsuarioDTO;
import com.darlanmarangoni.financas.autenticacao.model.Role;
import com.darlanmarangoni.financas.autenticacao.model.Usuario;
import com.darlanmarangoni.financas.autenticacao.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO cadastrarUsuario(CadastroUsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .role(Role.USER) // Por padrão, novos usuários têm o papel USER
                .build();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return mapToUsuarioDTO(usuarioSalvo);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
        return mapToUsuarioDTO(usuario);
    }

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToUsuarioDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO atualizarUsuario(Long id, AtualizacaoUsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));

        if (request.getNome() != null && !request.getNome().isBlank()) {
            usuario.setNome(request.getNome());
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            if (!usuario.getEmail().equals(request.getEmail()) && usuarioRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Email já está em uso por outro usuário");
            }
            usuario.setEmail(request.getEmail());
        }

        if (request.getSenha() != null && !request.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        }

        if (request.getAtivo() != null) {
            usuario.setAtivo(request.getAtivo());
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return mapToUsuarioDTO(usuarioAtualizado);
    }

    public void excluirUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO mapToUsuarioDTO(Usuario usuario) {
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
