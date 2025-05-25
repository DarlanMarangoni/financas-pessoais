package com.darlanmarangoni.financas.controlefinanceiro.service;

import com.darlanmarangoni.financas.controlefinanceiro.domain.Categoria;
import com.darlanmarangoni.financas.controlefinanceiro.dto.CategoriaRequestDTO;
import com.darlanmarangoni.financas.controlefinanceiro.dto.CategoriaResponseDTO;
import com.darlanmarangoni.financas.controlefinanceiro.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDTO buscarPorId(UUID id) {
        return toResponseDTO(getCategoriaById(id));
    }

    @Transactional
    public CategoriaResponseDTO criar(CategoriaRequestDTO requestDTO) {
        Categoria categoria = Categoria.builder()
                .nome(requestDTO.nome())
                .descricao(requestDTO.descricao())
                .build();
        
        return toResponseDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaResponseDTO atualizar(UUID id, CategoriaRequestDTO requestDTO) {
        Categoria categoria = getCategoriaById(id);
        
        categoria.setNome(requestDTO.nome());
        categoria.setDescricao(requestDTO.descricao());
        
        return toResponseDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public void excluir(UUID id) {
        Categoria categoria = getCategoriaById(id);
        categoriaRepository.delete(categoria);
    }

    private Categoria getCategoriaById(UUID id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + id));
    }

    private CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getDataCadastro(),
                categoria.getDataAtualizacao()
        );
    }
}
