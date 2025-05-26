# Authentication Service

Serviço dedicado de autenticação para o sistema de Finanças Pessoais.

## Características

- Autenticação baseada em JWT (JSON Web Tokens)
- Gerenciamento de usuários e perfis
- API REST para integrações
- Segurança com Spring Security

## Tecnologias

- Java 24
- Spring Boot 3.5.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT

## Configuração

### Pré-requisitos

- JDK 24
- Maven
- PostgreSQL

### Variáveis de Ambiente

O serviço utiliza as seguintes variáveis de ambiente que podem ser configuradas:

- `SPRING_DATASOURCE_URL`: URL do banco de dados PostgreSQL
- `SPRING_DATASOURCE_USERNAME`: Usuário do banco de dados
- `SPRING_DATASOURCE_PASSWORD`: Senha do banco de dados
- `APPLICATION_SECURITY_JWT_SECRET_KEY`: Chave secreta para assinatura dos tokens JWT
- `APPLICATION_SECURITY_JWT_EXPIRATION`: Tempo de expiração dos tokens em milissegundos

### Compilando e Executando
