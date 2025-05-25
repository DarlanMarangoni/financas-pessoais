package com.darlanmarangoni.financas.autenticacao.controller;

import com.darlanmarangoni.financas.autenticacao.dto.AtualizacaoUsuarioRequest;
import com.darlanmarangoni.financas.autenticacao.dto.CadastroUsuarioRequest;
import com.darlanmarangoni.financas.autenticacao.dto.UsuarioDTO;
import com.darlanmarangoni.financas.autenticacao.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@Valid @RequestBody CadastroUsuarioRequest request) {
        UsuarioDTO usuarioDTO = usuarioService.cadastrarUsuario(request);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or @usuarioService.buscarUsuarioPorId(#id).email == authentication.name")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or @usuarioService.buscarUsuarioPorId(#id).email == authentication.name")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody AtualizacaoUsuarioRequest request
    ) {
        UsuarioDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, request);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
