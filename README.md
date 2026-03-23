# API - TASKIUM

Project By Felipe Amaro

## Configuração

1. Copie o arquivo de exemplo e preencha com seus valores:

```bash
cp .env.example .env
```

2. Edite o `.env` com as credenciais do seu ambiente:

```dotenv
DB_URL=jdbc:mysql://localhost:3306/taskium
DB_USERNAME=root
DB_PASSWORD=
DB_DDL_AUTO=update
DB_SHOW_SQL=true
DB_FORMAT_SQL=true
JWT_SECRET=chave-secreta-dev
```

> O arquivo `.env` é ignorado pelo Git. Nunca commite credenciais reais.

## Execução

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

### Rotas públicas
- `POST /auth/login`
- `POST /users`
- Swagger: `/swagger-ui.html`

### Rotas protegidas (JWT obrigatório)
- `GET /users/**` → `ROLE_ADMIN`
- `PUT /users/**` → `ROLE_ADMIN` ou `ROLE_MANAGER`
- `DELETE /users/**` → `ROLE_ADMIN`

## Testes

Os testes de integração usam Testcontainers (MySQL). É necessário ter Docker rodando:

```bash
./mvnw test
```
