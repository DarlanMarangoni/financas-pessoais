package com.darlanmarangoni.financas.controlefinanceiro.service;

import com.darlanmarangoni.financas.controlefinanceiro.domain.Categoria;
import com.darlanmarangoni.financas.controlefinanceiro.domain.Lancamento;
import com.darlanmarangoni.financas.controlefinanceiro.dto.CategoriaResponseDTO;
import com.darlanmarangoni.financas.controlefinanceiro.dto.LancamentoRequestDTO;
import com.darlanmarangoni.financas.controlefinanceiro.dto.LancamentoResponseDTO;
import com.darlanmarangoni.financas.controlefinanceiro.exception.RecursoNaoEncontradoException;
import com.darlanmarangoni.financas.controlefinanceiro.repository.CategoriaRepository;
import com.darlanmarangoni.financas.controlefinanceiro.repository.LancamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public LancamentoResponseDTO criarLancamento(LancamentoRequestDTO requestDTO) {
        Categoria categoria = null;
        if (requestDTO.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(requestDTO.getCategoriaId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com o ID: " + requestDTO.getCategoriaId()));
        }

        Lancamento lancamento = Lancamento.builder()
                .data(requestDTO.getData())
                .descricao(requestDTO.getDescricao())
                .valorPrevisto(requestDTO.getValorPrevisto())
                .valorRealizado(requestDTO.getValorRealizado())
                .categoria(categoria)
                .build();

        Lancamento savedLancamento = lancamentoRepository.save(lancamento);
        return mapToResponseDTO(savedLancamento);
    }

    public LancamentoResponseDTO buscarLancamentoPorId(UUID id) {
        Lancamento lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Lançamento não encontrado com o ID: " + id));
        
        return mapToResponseDTO(lancamento);
    }

    public Page<LancamentoResponseDTO> listarLancamentos(Pageable pageable) {
        return lancamentoRepository.findAll(pageable)
                .map(this::mapToResponseDTO);
    }

    public List<LancamentoResponseDTO> buscarLancamentosPorData(LocalDate data) {
        return lancamentoRepository.findByData(data).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<LancamentoResponseDTO> buscarLancamentosPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return lancamentoRepository.findByDataBetween(dataInicio, dataFim).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<LancamentoResponseDTO> buscarLancamentosPorCategoria(UUID categoriaId) {
        return lancamentoRepository.findByCategoriaId(categoriaId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public LancamentoResponseDTO atualizarLancamento(UUID id, LancamentoRequestDTO requestDTO) {
        Lancamento lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Lançamento não encontrado com o ID: " + id));

        Categoria categoria = null;
        if (requestDTO.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(requestDTO.getCategoriaId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com o ID: " + requestDTO.getCategoriaId()));
        }

        lancamento.setData(requestDTO.getData());
        lancamento.setDescricao(requestDTO.getDescricao());
        lancamento.setValorPrevisto(requestDTO.getValorPrevisto());
        lancamento.setValorRealizado(requestDTO.getValorRealizado());
        lancamento.setCategoria(categoria);

        Lancamento updatedLancamento = lancamentoRepository.save(lancamento);
        return mapToResponseDTO(updatedLancamento);
    }

    @Transactional
    public void excluirLancamento(UUID id) {
        if (!lancamentoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Lançamento não encontrado com o ID: " + id);
        }
        lancamentoRepository.deleteById(id);
    }

    private LancamentoResponseDTO mapToResponseDTO(Lancamento lancamento) {
        CategoriaResponseDTO categoriaDTO = null;
        if (lancamento.getCategoria() != null) {
            categoriaDTO = CategoriaResponseDTO.builder()
                    .id(lancamento.getCategoria().getId())
                    .nome(lancamento.getCategoria().getNome())
                    .build();
        }

        return LancamentoResponseDTO.builder()
                .id(lancamento.getId())
                .data(lancamento.getData())
                .descricao(lancamento.getDescricao())
                .valorPrevisto(lancamento.getValorPrevisto())
                .valorRealizado(lancamento.getValorRealizado())
                .categoria(categoriaDTO)
                .dataCadastro(lancamento.getDataCadastro())
                .dataAtualizacao(lancamento.getDataAtualizacao())
                .build();
    }
}
