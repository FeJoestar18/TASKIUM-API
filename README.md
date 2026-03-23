<p align="center">
  <h1 align="center">TASKIUM API</h1>
  <p align="center">
    API RESTful de gerenciamento de tarefas e usuários construída com <strong>Java 17</strong> e <strong>Spring Boot 3.3.5</strong>, seguindo os princípios de <strong>Clean Architecture</strong> e <strong>SOLID</strong>.
  </p>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?logo=openjdk" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.5-green?logo=springboot" />
  <img src="https://img.shields.io/badge/MySQL-8.4-blue?logo=mysql" />
  <img src="https://img.shields.io/badge/JWT-Auth0-red?logo=jsonwebtokens" />
  <img src="https://img.shields.io/badge/Swagger-OpenAPI-brightgreen?logo=swagger" />
</p>

---

## 👤 Autor

Desenvolvido por **Felipe Amaro**.

---

## 🔗 Repositórios Auxiliares

| Repositório | Descrição |
|-------------|-----------|
| [TaskIUM-Architecture](https://github.com/FeJoestar18/TaskIUM-Architecture) | Documentação e diagramas da arquitetura do projeto |
| [TaskIUM-Database](https://github.com/FeJoestar18/TaskIUM-Database) | Modelagem e scripts do banco de dados |

---

## 📋 Sobre o Projeto

O **Taskium** é uma API REST completa para gerenciamento de tarefas corporativas, projetada para suportar múltiplos papéis de usuário (Admin, Manager, User) com controle de acesso granular via JWT. O sistema permite a criação e gestão de usuários, tarefas, eventos, conquistas, notificações e muito mais.

### Principais Funcionalidades

-  **Autenticação e Autorização** — Login com JWT (JSON Web Token) e controle de acesso baseado em roles (RBAC)
-  **Gerenciamento de Usuários** — CRUD completo com validações de CPF, e-mail e telefone únicos
-  **Gerenciamento de Tarefas** — Criação de tarefas com status, prioridade, prazo e categorias
-  **Sistema de Conquistas** — Gamificação com conquistas e premiações para os usuários
-  **Eventos** — Gerenciamento de eventos com associação de participantes
-  **Notificações** — Sistema de notificações integrado
-  **Auditoria** — Registro automático de `createdAt` e `updatedAt` em todas as entidades
-  **Documentação Swagger** — Documentação interativa da API via OpenAPI 3.0

---

## 🏗️ Arquitetura — Clean Architecture

### O que é?

O projeto segue a **Clean Architecture** (Arquitetura Limpa), proposta por Robert C. Martin (Uncle Bob). É um padrão arquitetural que organiza o código em **camadas concêntricas**, onde cada camada tem uma responsabilidade bem definida e as dependências sempre apontam **de fora para dentro**.

```
┌─────────────────────────────────────────────────────────────────┐
│                        🌐 API (Controllers)                     │
│          Ponto de entrada HTTP — recebe e responde requests     │
├─────────────────────────────────────────────────────────────────┤
│                  ⚙️ APPLICATION (UseCases + DTOs)               │
│           Orquestra o fluxo — coordena Domain e Infra           │
├─────────────────────────────────────────────────────────────────┤
│                  💎 DOMAIN (Entities + Services)                │
│        Coração do sistema — regras de negócio puras             │
├─────────────────────────────────────────────────────────────────┤
│               🔧 INFRASTRUCTURE (Repos + Security)              │
│       Detalhes técnicos — banco, JWT, configs, frameworks       │
└─────────────────────────────────────────────────────────────────┘
```

### Por que Clean Architecture?

Em projetos tradicionais (MVC simples, por exemplo), é comum que as regras de negócio fiquem espalhadas entre controllers, services e repositórios, tudo misturado com código de framework. Conforme o projeto cresce, isso gera problemas:

- **Acoplamento alto** — mudar o banco de dados, framework ou biblioteca obriga a mexer em dezenas de arquivos.
- **Testes difíceis** — testar uma regra de negócio exige subir banco, contexto do Spring, etc.
- **Manutenção cara** — qualquer alteração pequena tem efeito cascata imprevisível.

A Clean Architecture resolve isso com uma regra simples:

> **Regra de Dependência**: as camadas internas **nunca** conhecem as camadas externas. O domínio não sabe que o Spring existe. Não sabe que o banco é MySQL. Não sabe que a API é REST.

Na prática, isso significa que:

| Situação | O que muda | O que **não** precisa mudar |
|----------|------------|----------------------------|
| Trocar MySQL por MongoDB | Apenas os Adapters na Infrastructure | Domain, Application e Api |
| Trocar REST por GraphQL | Apenas a camada Api (Controllers) | Domain, Application e Infrastructure |
| Adicionar nova regra de negócio | Domain e Application | Infrastructure e Api |
| Trocar JWT por OAuth2 | Apenas Security na Infrastructure | Domain e Application |

### Como funciona na prática — Inversão de Dependência

O ponto-chave da arquitetura é a **inversão de dependência**. O Domain define **interfaces** (contratos) e a Infrastructure fornece as **implementações concretas**.

Exemplo real do projeto — o fluxo de persistência de um usuário:

```
  Domain define o contrato:                Infrastructure implementa:
  ┌──────────────────────┐                 ┌───────────────────────────┐
  │  IUserRepository     │ ◄── implements ─│  UserRepositoryImpl       │
  │  ─────────────────── │                 │  ────────────────────────  │
  │  + save(User)        │                 │  delega para ──▶ UserJpaRepository
  │  + findByEmail(...)  │                 │                 (Spring Data JPA)
  │  + existsByEmail(..) │                 │                      │
  └──────────────────────┘                 └──────────────────────│────┘
                                                                  ▼
                                                               MySQL
```

O `RegisterUserUseCase` (Application) usa `IUserRepository` (Domain) — uma **interface**. Ele não sabe e não precisa saber que por trás existe um `UserJpaRepository` do Spring Data acessando MySQL. Se amanhã o banco mudar para MongoDB, basta criar um novo Adapter — o UseCase e o Domain **permanecem intactos**.

### Fluxo completo de uma requisição

```
HTTP Request  ──▶  POST /users  (JSON body)
    │
    ▼
┌─ API ─────────────────────────────────────────────────────────┐
│  UserController                                                │
│  Recebe o request, valida o JSON (@Valid) e delega ao UseCase │
└───────────────────────────────┬────────────────────────────────┘
                                │
                                ▼
┌─ APPLICATION ─────────────────────────────────────────────────┐
│  RegisterUserUseCase.execute(dto)                              │
│  1. Chama UserValidator (Domain) → valida unicidade            │
│  2. Chama UserService (Domain)  → constrói entidade User       │
│  3. Chama IUserRepository       → persiste no banco            │
│  4. Chama IRoleRepository       → busca a Role                 │
│  5. Chama UserService           → constrói UserDetails         │
│  6. Chama IUserDetailsRepository→ persiste detalhes            │
│  7. Retorna UserResponseDTO                                    │
└───────────────────────────────┬────────────────────────────────┘
                                │
          ┌─────────────────────┼─────────────────────┐
          ▼                     ▼                     ▼
┌─ DOMAIN ──────────┐ ┌─ DOMAIN ──────────┐ ┌─ DOMAIN ──────────┐
│  UserValidator     │ │  UserService      │ │  IUserRepository   │
│  Verifica se email,│ │  Constrói User e  │ │  (interface)       │
│  CPF e telefone    │ │  UserDetails com  │ │  Contrato que a    │
│  já existem        │ │  dados do DTO     │ │  Infra implementa  │
└────────────────────┘ └───────────────────┘ └────────┬───────────┘
                                                      │
                                                      ▼
                                    ┌─ INFRASTRUCTURE ─────────────┐
                                    │  UserRepositoryImpl (Adapter) │
                                    │  Delega para UserJpaRepository│
                                    │  que faz o SQL real no MySQL  │
                                    └──────────────────────────────┘
```

### Por que não usar MVC simples?

O MVC é um padrão válido, mas **não escala bem em complexidade**. Veja a comparação:

| Critério | MVC Tradicional | Clean Architecture (Taskium) |
|----------|-----------------|------------------------------|
| **Regras de negócio** | Misturadas nos Services (que dependem do Spring) | Isoladas no Domain, sem dependência de framework |
| **Testabilidade** | Precisa do contexto do Spring para testar | Domain e UseCases testáveis com JUnit puro (sem Spring) |
| **Troca de banco** | Mexe em Services, Repositories e às vezes Controllers | Mexe **apenas** nos Adapters da Infrastructure |
| **Troca de framework** | Reescreve quase tudo | Domain e Application permanecem iguais |
| **Organização em equipe** | Um dev pode quebrar o fluxo inteiro sem perceber | Camadas bem definidas — cada dev sabe onde mexer |
| **Crescimento do projeto** | Tende a virar "big ball of mud" | Cresce de forma organizada e previsível |

### Os princípios por trás da escolha

A Clean Architecture neste projeto aplica diretamente os **5 princípios SOLID**:

- **S — Single Responsibility**: Cada UseCase faz uma coisa só (`LoginUseCase` autentica, `RegisterUserUseCase` registra). Cada Service tem um propósito claro.
- **O — Open/Closed**: Para adicionar um novo caso de uso (ex: `DeleteUserUseCase`), basta criar uma nova classe — nenhum código existente precisa mudar.
- **L — Liskov Substitution**: `UserRepositoryImpl` implementa `IUserRepository` e pode ser substituído por qualquer outra implementação sem quebrar nada.
- **I — Interface Segregation**: Interfaces pequenas e específicas por contexto (`IUserRepository`, `IRoleRepository`, `IUserService`) em vez de uma interface gigante.
- **D — Dependency Inversion**: Domain e Application dependem de **abstrações** (interfaces). A Infrastructure fornece as implementações. O Spring resolve tudo via injeção de dependência.

---

## 📂 Estrutura de Pastas Detalhada

```
src/main/java/com/taskium/project/
│
├── 📁 Api/                          # Camada de Interface (entrada)
│   ├── Controllers/
│   │   ├── Auth/
│   │   │   └── AuthController.java          # Endpoints de autenticação
│   │   └── User/
│   │       └── UserController.java          # Endpoints de usuários
│   └── Middleware/                           # Middlewares HTTP (futuro)
│
├── 📁 Application/                  # Camada de Aplicação (orquestração)
│   ├── DTO/
│   │   ├── AuthDTO.java                     # Dados de login (request)
│   │   ├── LoginResponseDTO.java            # Resposta do login (token)
│   │   ├── UserRequestDTO.java              # Dados de criação de usuário
│   │   ├── UserResponseDTO.java             # Resposta com dados do usuário
│   │   └── ErrorResponseDTO.java            # Resposta padronizada de erro
│   ├── Policy/                              # Políticas de autorização (futuro)
│   └── UseCases/
│       ├── Auth/
│       │   └── LoginUseCase.java            # Caso de uso: autenticar usuário
│       └── User/
│           └── RegisterUserUseCase.java     # Caso de uso: registrar usuário
│
├── 📁 Domain/                       # Camada de Domínio (regras de negócio)
│   ├── Common/
│   │   ├── Exceptions/
│   │   │   ├── CpflAlreadyExistsException.java
│   │   │   ├── EmailAlreadyExistsException.java
│   │   │   ├── PhoneNumberAlreadyExistsException.java
│   │   │   └── RoleNotFoundException.java
│   │   └── Validators/
│   │       └── UserValidator.java           # Validações de unicidade
│   ├── Entity/
│   │   ├── User.java                        # Entidade principal de usuário
│   │   ├── UserDetails.java                 # Detalhes complementares do usuário
│   │   ├── Task.java                        # Entidade de tarefa
│   │   ├── Role.java                        # Papel do usuário (ADMIN, MANAGER, USER)
│   │   ├── Status.java                      # Status genérico
│   │   ├── TaskStatus.java                  # Status específico de tarefas
│   │   ├── TaskCategory.java                # Categoria de tarefas
│   │   ├── TaskHistory.java                 # Histórico de alterações
│   │   ├── Achievement.java                 # Conquistas do sistema
│   │   ├── AchievementUser.java             # Conquistas por usuário
│   │   ├── Award.java                       # Premiações
│   │   ├── Event.java                       # Eventos
│   │   ├── EventUser.java                   # Participantes de eventos
│   │   ├── Comment.java                     # Comentários
│   │   ├── Note.java                        # Notas
│   │   ├── Notification.java               # Notificações
│   │   ├── Permission.java                  # Permissões
│   │   ├── Preference.java                  # Preferências do sistema
│   │   ├── Regulation.java                  # Regulamentos
│   │   ├── RegulationUser.java              # Regulamentos por usuário
│   │   ├── Audit.java                       # Auditoria
│   │   ├── Attachment.java                  # Anexos
│   │   ├── UserAddress.java                 # Endereço do usuário
│   │   ├── UserNote.java                    # Notas do usuário
│   │   ├── UserPreference.java              # Preferências do usuário
│   │   └── UserTask.java                    # Relação usuário-tarefa
│   ├── Enums/
│   │   └── RoleName.java                    # Enum: ADMIN, USER, MANAGER
│   ├── Interfaces/
│   │   ├── Repository/
│   │   │   ├── IUserRepository.java         # Contrato de repositório de usuários
│   │   │   ├── IRoleRepository.java         # Contrato de repositório de roles
│   │   │   └── IUserDetailsRepository.java  # Contrato de repositório de detalhes
│   │   └── Services/
│   │       ├── Auth/
│   │       │   └── IAuthService.java        # Contrato de serviço de autenticação
│   │       └── User/
│   │           └── IUserService.java        # Contrato de serviço de usuário
│   └── Services/
│       ├── Auth/
│       │   └── AuthService.java             # Implementação: carrega usuário por email
│       └── User/
│           └── UserService.java             # Implementação: cria User e UserDetails
│
├── 📁 Infrastructure/               # Camada de Infraestrutura (detalhes técnicos)
│   ├── Config/
│   │   ├── DataInitializer.java             # Seed de roles no banco ao iniciar
│   │   └── JpaAuditingConfig.java           # Habilita auditoria automática do JPA
│   ├── Docs/
│   │   ├── Http/                            # Arquivos .http para testes manuais
│   │   └── Swagger/
│   │       └── SwaggerConfig.java           # Abre Swagger automaticamente (perfil dev)
│   ├── Exceptions/
│   │   └── GlobalExceptionHandler.java      # Handler global de exceções (@RestControllerAdvice)
│   ├── Persistence/
│   │   └── BaseEntity.java                  # Classe base: id, createdAt, updatedAt
│   ├── Repository/
│   │   ├── Adapters/
│   │   │   ├── UserRepositoryImpl.java      # Adapter: implementa IUserRepository
│   │   │   ├── RoleRepositoryImpl.java      # Adapter: implementa IRoleRepository
│   │   │   └── UserDetailsRepositoryImpl.java # Adapter: implementa IUserDetailsRepository
│   │   └── JPA/
│   │       ├── UserJpaRepository.java       # Spring Data JPA Repository
│   │       ├── RoleJpaRepository.java       # Spring Data JPA Repository
│   │       └── UserDetailsJpaRepository.java # Spring Data JPA Repository
│   └── Security/
│       ├── SecurityConfig.java              # Configuração do Spring Security
│       ├── SecurityFilter.java              # Filtro JWT (valida token a cada request)
│       ├── TokenGenerateService.java        # Geração e validação de tokens JWT
│       └── AuthenticatedUserDetails.java    # Wrapper do User para Spring Security
│
└── 📁 Test/                         # Testes (em src/main — estrutura customizada)
    ├── IntegrationTest/
    └── UnitTest/
```

---

## 📚 Tutorial — Para Que Serve Cada Camada

### 🌐 Camada `Api` — Interface do Mundo Externo

| Aspecto | Descrição |
|---------|-----------|
| **Responsabilidade** | Receber requisições HTTP e devolver respostas |
| **O que contém** | Controllers REST, Middlewares |
| **Depende de** | Application (UseCases e DTOs) |
| **Não deve conter** | Regras de negócio, acesso a banco de dados |

Os **Controllers** são finos: recebem o request, delegam a um UseCase e retornam o resultado. Exemplo:

```java
@PostMapping("/login")
public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthDTO authDTO) {
    var response = loginUseCase.execute(authDTO);
    return ResponseEntity.ok(response);
}
```

---

### ⚙️ Camada `Application` — Orquestração

| Aspecto | Descrição |
|---------|-----------|
| **Responsabilidade** | Coordenar o fluxo da aplicação entre as camadas |
| **O que contém** | Use Cases, DTOs, Policies |
| **Depende de** | Domain (Entities, Interfaces, Services) |
| **Não deve conter** | Detalhes de framework, acesso direto ao banco |

Os **Use Cases** orquestram os passos de uma operação de negócio. Exemplo de `RegisterUserUseCase`:

1. Valida unicidade (email, CPF, telefone)
2. Cria o objeto `User` via `UserService`
3. Persiste no banco via `IUserRepository`
4. Busca a `Role` via `IRoleRepository`
5. Cria `UserDetails` e persiste
6. Retorna `UserResponseDTO`

Os **DTOs** isolam a representação externa (JSON) do modelo interno (Entity), aplicando validações de entrada com Bean Validation (`@NotBlank`, `@Email`, `@Pattern`).

---

### 💎 Camada `Domain` — Coração do Sistema

| Aspecto | Descrição |
|---------|-----------|
| **Responsabilidade** | Regras de negócio puras e modelagem do domínio |
| **O que contém** | Entities, Enums, Interfaces, Services, Validators, Exceptions |
| **Depende de** | Nada externo (camada mais interna) |
| **Não deve conter** | Anotações de framework (idealmente), acesso a I/O |

- **Entities** — Representam os conceitos do negócio (`User`, `Task`, `Role`, `Achievement`, etc.)
- **Interfaces** — Contratos que a infraestrutura deve implementar (`IUserRepository`, `IUserService`)
- **Services** — Lógica de negócio pura (ex: `UserService` constrói entidades, `AuthService` carrega usuários)
- **Validators** — Validações de regras de negócio (ex: `UserValidator` garante unicidade)
- **Exceptions** — Exceções de domínio específicas (ex: `EmailAlreadyExistsException`)
- **Enums** — Valores fixos do domínio (ex: `RoleName`: ADMIN, USER, MANAGER)

---

### 🔧 Camada `Infrastructure` — Detalhes Técnicos

| Aspecto | Descrição |
|---------|-----------|
| **Responsabilidade** | Implementar detalhes técnicos e comunicação com o mundo externo |
| **O que contém** | Repositórios JPA, Adapters, Security, Configs, Exception Handlers |
| **Depende de** | Domain (implementa interfaces do domínio) |
| **Não deve conter** | Regras de negócio |

- **Repository/JPA** — Interfaces do Spring Data JPA (`UserJpaRepository extends JpaRepository`)
- **Repository/Adapters** — Implementam interfaces do Domain, delegando para os JPA Repositories (Adapter Pattern)
- **Security** — Toda a configuração de segurança: Spring Security, filtro JWT, geração/validação de tokens, BCrypt
- **Config** — Configurações da aplicação (`DataInitializer` faz seed de roles, `JpaAuditingConfig` habilita auditoria)
- **Persistence** — `BaseEntity` com `id`, `createdAt`, `updatedAt` herdados por todas as entidades
- **Exceptions** — `GlobalExceptionHandler` trata exceções de forma centralizada e retorna respostas padronizadas
- **Docs** — Configuração do Swagger/OpenAPI

---

## 🛠️ Tecnologias e Dependências

| Tecnologia | Versão | Finalidade |
|---|---|---|
| **Java** | 17 | Linguagem principal |
| **Spring Boot** | 3.3.5 | Framework base |
| **Spring Security** | - | Autenticação e autorização |
| **Spring Data JPA** | - | Persistência e ORM |
| **MySQL** | 8.4 | Banco de dados relacional |
| **Auth0 java-jwt** | 4.5.1 | Geração e validação de tokens JWT |
| **Lombok** | 1.18.32 | Redução de boilerplate |
| **SpringDoc OpenAPI** | 2.5.0 | Documentação Swagger |
| **spring-dotenv** | 4.0.0 | Variáveis de ambiente via `.env` |
| **H2 Database** | - | Banco em memória para desenvolvimento |
| **Testcontainers** | - | Testes de integração com MySQL real via Docker |
| **JUnit 5** | - | Framework de testes |

---

## ⚡ Configuração e Execução

### Pré-requisitos

- Java 17+
- Maven 3.8+
- MySQL 8.x (ou Docker para testes)
- Docker (para testes de integração com Testcontainers)

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/taskium.git
cd taskium
```

### 2. Configure as variáveis de ambiente

Copie o arquivo de exemplo e preencha com seus valores:

```bash
cp .env.example .env
```

Edite o `.env`:

```dotenv
DB_URL=jdbc:mysql://localhost:3306/taskium
DB_USERNAME=root
DB_PASSWORD=sua_senha
DB_DDL_AUTO=update
DB_SHOW_SQL=true
DB_FORMAT_SQL=true
JWT_SECRET=sua-chave-secreta-super-segura
```

> ⚠️ O arquivo `.env` é ignorado pelo Git. **Nunca commite credenciais reais.**

### 3. Execute a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 🔑 Endpoints da API

### Rotas Públicas (sem autenticação)

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/auth/login` | Autenticar e obter token JWT |
| `POST` | `/users` | Registrar novo usuário |
| `GET` | `/swagger-ui.html` | Documentação interativa |

### Rotas Protegidas (JWT obrigatório)

| Método | Rota | Role Necessária | Descrição |
|--------|------|-----------------|-----------|
| `GET` | `/users/**` | `ROLE_ADMIN` | Listar/buscar usuários |
| `PUT` | `/users/**` | `ROLE_ADMIN` ou `ROLE_MANAGER` | Atualizar usuário |
| `DELETE` | `/users/**` | `ROLE_ADMIN` | Remover usuário |

### Exemplo de uso

**Login:**
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "admin@taskium.com", "password": "123456"}'
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

**Acessar rota protegida:**
```bash
curl -X GET http://localhost:8080/users \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIs..."
```

---

## 🧪 Testes

Os testes de integração utilizam **Testcontainers** para subir um container MySQL real via Docker, garantindo que os testes rodem em um ambiente idêntico ao de produção.

### Executar testes

```bash
# Requer Docker rodando
./mvnw test
```

### Estrutura de testes

```
src/test/java/com/taskium/project/
├── AbstractIntegrationTest.java    # Classe base com Testcontainers (MySQL)
└── ProjectApplicationTests.java    # Teste de contexto da aplicação
```

---


##  Licença

Este projeto é de uso pessoal/educacional. Desenvolvido por **Felipe Amaro**.

---

<p align="center">
  Feito com café e dedicação por <strong>Felipe Amaro</strong>
</p>
