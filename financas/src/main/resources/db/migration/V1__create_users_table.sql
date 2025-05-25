CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP,
    ultimo_acesso TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE,
    CONSTRAINT uk_email UNIQUE (email)
);

-- Insere um usuário administrador padrão (senha: admin123)
INSERT INTO usuarios (nome, email, senha, role, data_criacao, ativo)
VALUES ('Administrador', 'admin@financaspessoais.com', 
        '$2a$10$uuFbHkHYE4bK1YjBtI5l9u3rEO.m7QLuWAj6C7RzY1JMm/GCjbQqO', 
        'ADMIN', CURRENT_TIMESTAMP, TRUE);

-- Insere um usuário comum para testes (senha: user123)
INSERT INTO usuarios (nome, email, senha, role, data_criacao, ativo)
VALUES ('Usuário Teste', 'user@example.com', 
        '$2a$10$sGfnyPnJ8a0b9R.vqIphPu19wQm7EstTYLTXz1yPuFEPQMG5jAULW', 
        'USER', CURRENT_TIMESTAMP, TRUE);
