package com.darlanmarangoni.financas.controlefinanceiro.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoriaResponseDTO(
    UUID id,
    String nome,
    String descricao,
    LocalDateTime dataCadastro,
    LocalDateTime dataAtualizacao
) {
    // Java Record automaticamente cria getters, equals, hashCode e toString

    // Factory method para suportar o padrão Builder que usávamos com Lombok
    public static Builder builder() {
        return new Builder();
    }

    // Builder pattern implementado manualmente (opcional)
    public static class Builder {
        private UUID id;
        private String nome;
        private String descricao;
        private LocalDateTime dataCadastro;
        private LocalDateTime dataAtualizacao;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder dataCadastro(LocalDateTime dataCadastro) {
            this.dataCadastro = dataCadastro;
            return this;
        }

        public Builder dataAtualizacao(LocalDateTime dataAtualizacao) {
            this.dataAtualizacao = dataAtualizacao;
            return this;
        }

        public CategoriaResponseDTO build() {
            return new CategoriaResponseDTO(id, nome, descricao, dataCadastro, dataAtualizacao);
        }
    }
}
