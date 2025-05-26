CREATE TABLE IF NOT EXISTS lancamento (
    id UUID PRIMARY KEY,
    data DATE NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    valor_previsto DECIMAL(19, 2),
    valor_realizado DECIMAL(19, 2),
    categoria_id UUID,
    data_cadastro TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP,
    CONSTRAINT fk_lancamento_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (id)
);

-- Creating indexes for better performance
CREATE INDEX idx_lancamento_data ON lancamento(data);
CREATE INDEX idx_lancamento_categoria ON lancamento(categoria_id);
