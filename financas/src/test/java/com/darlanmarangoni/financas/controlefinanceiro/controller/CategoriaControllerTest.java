package com.darlanmarangoni.financas.controlefinanceiro.controller;

import com.darlanmarangoni.financas.controlefinanceiro.dto.CategoriaRequestDTO;
import com.darlanmarangoni.financas.controlefinanceiro.dto.CategoriaResponseDTO;
import com.darlanmarangoni.financas.controlefinanceiro.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoriaController.class)
@ActiveProfiles("test")
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoriaService categoriaService;

    private CategoriaRequestDTO requestDTO;
    private CategoriaResponseDTO responseDTO;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        requestDTO = new CategoriaRequestDTO("Alimentação", "Gastos com alimentação");
        responseDTO = new CategoriaResponseDTO(id, "Alimentação", "Gastos com alimentação", now, now);
    }

    @Test
    void deveListarTodasAsCategorias() throws Exception {
        when(categoriaService.listarTodas()).thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/api/categorias"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(id.toString()))
                .andExpect(jsonPath("$[0].nome").value("Alimentação"));

        verify(categoriaService, times(1)).listarTodas();
    }

    @Test
    void deveBuscarCategoriaPorId() throws Exception {
        when(categoriaService.buscarPorId(id)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/categorias/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.nome").value("Alimentação"));

        verify(categoriaService, times(1)).buscarPorId(id);
    }

    @Test
    void deveRetornarNotFoundQuandoCategoriaNaoEncontrada() throws Exception {
        when(categoriaService.buscarPorId(id)).thenThrow(new EntityNotFoundException("Categoria não encontrada"));

        mockMvc.perform(get("/api/categorias/{id}", id))
                .andExpect(status().isNotFound());

        verify(categoriaService, times(1)).buscarPorId(id);
    }

    @Test
    void deveCriarCategoria() throws Exception {
        when(categoriaService.criar(any(CategoriaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.nome").value("Alimentação"));

        verify(categoriaService, times(1)).criar(any(CategoriaRequestDTO.class));
    }

    @Test
    void deveAtualizarCategoria() throws Exception {
        when(categoriaService.atualizar(eq(id), any(CategoriaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/categorias/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.nome").value("Alimentação"));

        verify(categoriaService, times(1)).atualizar(eq(id), any(CategoriaRequestDTO.class));
    }

    @Test
    void deveExcluirCategoria() throws Exception {
        doNothing().when(categoriaService).excluir(id);

        mockMvc.perform(delete("/api/categorias/{id}", id))
                .andExpect(status().isNoContent());

        verify(categoriaService, times(1)).excluir(id);
    }
}
