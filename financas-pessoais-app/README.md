# Finanças Pessoais App

Aplicação front-end para controle de finanças pessoais desenvolvida com Angular.

## Características

- Tema escuro moderno
- Interface responsiva
- Sistema de autenticação
- Layout simples e intuitivo

## Arquitetura

O sistema está dividido em três componentes principais:

1. **Frontend (financas-pessoais-app)**: Aplicação Angular que fornece a interface de usuário
2. **Serviço de Autenticação (autenticacao-service)**: Serviço Spring Boot responsável pela autenticação e gerenciamento de usuários (porta 8081)
3. **Serviço de Finanças (financas)**: Serviço Spring Boot que implementa a lógica de negócios para gerenciamento financeiro (porta 8080)

## Pré-requisitos

- Node.js (versão 14.x ou superior)
- npm (normalmente vem com o Node.js)
- Angular CLI (`npm install -g @angular/cli`)
- JDK 24 (para os serviços backend)
- Maven (para compilar os serviços backend)
- PostgreSQL (para armazenamento de dados)

## Instalação

### Frontend

1. Navegue até a pasta do projeto:
   ```
   cd financas-pessoais-app
   ```
2. Instale as dependências:
   ```
   npm install
   ```

### Serviços Backend

#### Serviço de Autenticação
1. Navegue até a pasta do serviço:
   ```
   cd autenticacao-service
   ```
2. Compile o projeto:
   ```
   mvn clean install
   ```
3. Execute o serviço:
   ```
   mvn spring-boot:run
   ```

#### Serviço de Finanças
1. Navegue até a pasta do serviço:
   ```
   cd financas
   ```
2. Compile o projeto:
   ```
   mvn clean install
   ```
3. Execute o serviço:
   ```
   mvn spring-boot:run
   ```

## Desenvolvimento

Para iniciar o servidor de desenvolvimento do frontend:

```bash
ng serve
