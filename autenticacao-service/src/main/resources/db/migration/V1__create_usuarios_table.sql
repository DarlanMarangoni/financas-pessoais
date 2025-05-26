CREATE TABLE IF NOT EXISTS usuarios (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    ultimo_acesso TIMESTAMP,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

-- Create index for email lookups
CREATE INDEX idx_usuarios_email ON usuarios(email);
