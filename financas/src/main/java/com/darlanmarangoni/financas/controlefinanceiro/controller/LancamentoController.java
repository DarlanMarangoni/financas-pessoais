package com.darlanmarangoni.financas.controlefinanceiro.controller;

import com.darlanmarangoni.financas.controlefinanceiro.dto.LancamentoRequestDTO;
import com.darlanmarangoni.financas.controlefinanceiro.dto.LancamentoResponseDTO;
import com.darlanmarangoni.financas.controlefinanceiro.service.LancamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lancamentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class LancamentoController {

    private final LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity<LancamentoResponseDTO> criarLancamento(@Valid @RequestBody LancamentoRequestDTO requestDTO) {
        LancamentoResponseDTO responseDTO = lancamentoService.criarLancamento(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoResponseDTO> buscarLancamentoPorId(@PathVariable UUID id) {
        LancamentoResponseDTO responseDTO = lancamentoService.buscarLancamentoPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<LancamentoResponseDTO>> listarLancamentos(
            @PageableDefault(size = 10, sort = "data") Pageable pageable) {
        Page<LancamentoResponseDTO> lancamentos = lancamentoService.listarLancamentos(pageable);
        return ResponseEntity.ok(lancamentos);
    }

    @GetMapping("/data")
    public ResponseEntity<List<LancamentoResponseDTO>> buscarLancamentosPorData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<LancamentoResponseDTO> lancamentos = lancamentoService.buscarLancamentosPorData(data);
        return ResponseEntity.ok(lancamentos);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<LancamentoResponseDTO>> buscarLancamentosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<LancamentoResponseDTO> lancamentos = lancamentoService.buscarLancamentosPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(lancamentos);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<LancamentoResponseDTO>> buscarLancamentosPorCategoria(
            @PathVariable UUID categoriaId) {
        List<LancamentoResponseDTO> lancamentos = lancamentoService.buscarLancamentosPorCategoria(categoriaId);
        return ResponseEntity.ok(lancamentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoResponseDTO> atualizarLancamento(
            @PathVariable UUID id,
            @Valid @RequestBody LancamentoRequestDTO requestDTO) {
        LancamentoResponseDTO responseDTO = lancamentoService.atualizarLancamento(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLancamento(@PathVariable UUID id) {
        lancamentoService.excluirLancamento(id);
        return ResponseEntity.noContent().build();
    }
}
