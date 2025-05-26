package com.darlanmarangoni.autenticacao.controller;

import com.darlanmarangoni.autenticacao.dto.AuthenticationRequest;
import com.darlanmarangoni.autenticacao.dto.AuthenticationResponse;
import com.darlanmarangoni.autenticacao.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // Em uma implementação stateless com JWT, não há necessidade de uma operação no servidor
        // O cliente simplesmente descarta o token
        return ResponseEntity.ok().build();
    }
}
