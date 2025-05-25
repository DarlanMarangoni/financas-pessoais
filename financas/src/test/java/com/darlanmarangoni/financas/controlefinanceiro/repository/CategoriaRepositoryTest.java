package com.darlanmarangoni.financas.controlefinanceiro.repository;

import com.darlanmarangoni.financas.controlefinanceiro.domain.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void deveSalvarCategoria() {
        // Arrange
        Categoria categoria = Categoria.builder()
                .nome("Alimentação")
                .descricao("Gastos com alimentação")
                .build();

        // Act
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        // Assert
        assertThat(categoriaSalva.getId()).isNotNull();
        assertThat(categoriaSalva.getNome()).isEqualTo("Alimentação");
        assertThat(categoriaSalva.getDescricao()).isEqualTo("Gastos com alimentação");
        assertThat(categoriaSalva.getDataCadastro()).isNotNull();
    }

    @Test
    void deveBuscarCategoriaPorId() {
        // Arrange
        Categoria categoria = categoriaRepository.save(Categoria.builder()
                .nome("Alimentação")
                .descricao("Gastos com alimentação")
                .build());
        UUID id = categoria.getId();

        // Act
        Optional<Categoria> resultado = categoriaRepository.findById(id);

        // Assert
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNome()).isEqualTo("Alimentação");
    }

    @Test
    void deveListarTodasAsCategorias() {
        // Arrange
        categoriaRepository.save(Categoria.builder()
                .nome("Alimentação")
                .descricao("Gastos com alimentação")
                .build());
                
        categoriaRepository.save(Categoria.builder()
                .nome("Transporte")
                .descricao("Gastos com transporte")
                .build());

        // Act
        List<Categoria> categorias = categoriaRepository.findAll();

        // Assert
        assertThat(categorias).hasSize(2);
        assertThat(categorias.get(0).getNome()).isIn("Alimentação", "Transporte");
        assertThat(categorias.get(1).getNome()).isIn("Alimentação", "Transporte");
    }

    @Test
    void deveVerificarSeExisteCategoriaPorNome() {
        // Arrange
        categoriaRepository.save(Categoria.builder()
                .nome("Alimentação")
                .descricao("Gastos com alimentação")
                .build());

        // Act
        boolean existe = categoriaRepository.existsByNome("Alimentação");
        boolean naoExiste = categoriaRepository.existsByNome("Categoria Inexistente");

        // Assert
        assertThat(existe).isTrue();
        assertThat(naoExiste).isFalse();
    }

    @Test
    void deveDeletarCategoria() {
        // Arrange
        Categoria categoria = categoriaRepository.save(Categoria.builder()
                .nome("Alimentação")
                .descricao("Gastos com alimentação")
                .build());
        UUID id = categoria.getId();

        // Act
        categoriaRepository.delete(categoria);
        Optional<Categoria> resultado = categoriaRepository.findById(id);

        // Assert
        assertThat(resultado).isEmpty();
    }
}
