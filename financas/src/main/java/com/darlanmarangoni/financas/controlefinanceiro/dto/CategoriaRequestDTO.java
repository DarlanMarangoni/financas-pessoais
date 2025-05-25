package com.darlanmarangoni.financas.controlefinanceiro.dto;

public record CategoriaRequestDTO(String nome, String descricao) {
    // Java Record automaticamente cria getters, equals, hashCode e toString

    // Factory method para suportar o padrão Builder que usávamos com Lombok
    public static Builder builder() {
        return new Builder();
    }

    // Builder pattern implementado manualmente (opcional)
    public static class Builder {
        private String nome;
        private String descricao;

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public CategoriaRequestDTO build() {
            return new CategoriaRequestDTO(nome, descricao);
        }
    }
}
