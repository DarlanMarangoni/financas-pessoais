package com.darlanmarangoni.autenticacao.service;

import com.darlanmarangoni.autenticacao.dto.AtualizacaoUsuarioRequest;
import com.darlanmarangoni.autenticacao.dto.CadastroUsuarioRequest;
import com.darlanmarangoni.autenticacao.dto.UsuarioDTO;
import com.darlanmarangoni.autenticacao.exception.EmailJaExisteException;
import com.darlanmarangoni.autenticacao.exception.RecursoNaoEncontradoException;
import com.darlanmarangoni.autenticacao.model.Role;
import com.darlanmarangoni.autenticacao.model.Usuario;
import com.darlanmarangoni.autenticacao.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
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
            throw new EmailJaExisteException("Email já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .role(Role.USER) // Por padrão, novos usuários têm o papel USER
                .build();

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return mapToDTO(savedUsuario);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o id: " + id));
        return mapToDTO(usuario);
    }

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, AtualizacaoUsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o id: " + id));

        // Verificar se o email está sendo alterado e se já existe
        if (request.getEmail() != null && !request.getEmail().equals(usuario.getEmail()) 
                && usuarioRepository.existsByEmail(request.getEmail())) {
            throw new EmailJaExisteException("Email já cadastrado");
        }

        // Atualizar campos se fornecidos
        if (request.getNome() != null) {
            usuario.setNome(request.getNome());
        }
        
        if (request.getEmail() != null) {
            usuario.setEmail(request.getEmail());
        }
        
        if (request.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        }
        
        if (request.getAtivo() != null) {
            usuario.setAtivo(request.getAtivo());
        }

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return mapToDTO(updatedUsuario);
    }

    @Transactional
    public void excluirUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com o id: " + id);
        }
        usuarioRepository.deleteById(id);
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
