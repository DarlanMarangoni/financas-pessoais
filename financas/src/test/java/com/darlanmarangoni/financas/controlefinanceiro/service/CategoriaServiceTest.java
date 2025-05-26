package com.darlanmarangoni.financas.controlefinanceiro.service;

import com.darlanmarangoni.financas.controlefinanceiro.domain.Categoria;
import com.darlanmarangoni.financas.controlefinanceiro.dto.CategoriaRequestDTO;
import com.darlanmarangoni.financas.controlefinanceiro.dto.CategoriaResponseDTO;
import com.darlanmarangoni.financas.controlefinanceiro.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;
    private UUID id;
    private CategoriaRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        categoria = new Categoria();
        categoria.setId(id);
        categoria.setNome("Alimentação");
        categoria.setDescricao("Gastos com alimentação");
        categoria.setDataCadastro(now);
        categoria.setDataAtualizacao(now);

        requestDTO = new CategoriaRequestDTO("Alimentação", "Gastos com alimentação");
    }

    @Test
    void deveListarTodasAsCategorias() {
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

        List<CategoriaResponseDTO> result = categoriaService.listarTodas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(categoria.getId(), result.get(0).id());
        assertEquals(categoria.getNome(), result.get(0).nome());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarCategoriaPorId() {
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));

        CategoriaResponseDTO result = categoriaService.buscarPorId(id);

        assertNotNull(result);
        assertEquals(categoria.getId(), result.id());
        assertEquals(categoria.getNome(), result.nome());
        verify(categoriaRepository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoEncontrada() {
        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoriaService.buscarPorId(id));
        verify(categoriaRepository, times(1)).findById(id);
    }

    @Test
    void deveCriarCategoria() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        CategoriaResponseDTO result = categoriaService.criar(requestDTO);

        assertNotNull(result);
        assertEquals(categoria.getId(), result.id());
        assertEquals(categoria.getNome(), result.nome());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void deveAtualizarCategoria() {
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        CategoriaRequestDTO novosDados = new CategoriaRequestDTO(
                "Alimentação Atualizada",
                "Descrição atualizada"
        );

        CategoriaResponseDTO result = categoriaService.atualizar(id, novosDados);

        assertNotNull(result);
        verify(categoriaRepository, times(1)).findById(id);
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void deveExcluirCategoria() {
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));
        doNothing().when(categoriaRepository).delete(any(Categoria.class));

        categoriaService.excluir(id);

        verify(categoriaRepository, times(1)).findById(id);
        verify(categoriaRepository, times(1)).delete(any(Categoria.class));
    }
}
