# Biblioteca API

API REST desenvolvida com Spring Boot para gerenciamento de livros e categorias.

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3
* Spring Data JPA
* Bean Validation
* PostgreSQL
* H2 Database
* Lombok
* Swagger/OpenAPI
* JUnit 5
* Mockito
* Maven

## Funcionalidades

### Livros

* Cadastrar livro
* Listar todos os livros
* Buscar livro por ID
* Atualizar livro
* Excluir livro
* Buscar livros por autor
* Buscar livros por título
* Buscar livros disponíveis

### Categorias

* Associação de livros a categorias
* Busca de categoria por ID
* Busca de categoria por nome

## Estrutura do Projeto

```text
src/main/java/br/com/biblioteca

├── config
├── controller
├── dto
├── exception
├── mapper
├── model
├── repository
└── service
```

## Tratamento de Exceções

A aplicação possui tratamento global de exceções através do `GlobalExceptionHandler`.

Exceções tratadas:

* LivroNotFoundException
* CategoriaNotFoundException
* DuplicateIsbnException
* MethodArgumentNotValidException
* Exception

## Documentação Swagger

Após iniciar a aplicação:

http://localhost:8080/swagger-ui/index.html

## Endpoints

| Método | Endpoint                    |
| ------ | --------------------------- |
| POST   | /api/livros                 |
| GET    | /api/livros                 |
| GET    | /api/livros/{id}            |
| PUT    | /api/livros/{id}            |
| DELETE | /api/livros/{id}            |
| GET    | /api/livros/autor/{autor}   |
| GET    | /api/livros/titulo/{titulo} |
| GET    | /api/livros/disponiveis     |

## Testes

Testes implementados utilizando:

* JUnit 5
* Mockito
* Spring Boot Test

Classes de teste:

* LivroServiceImplTest
* LivroControllerTest

## Profiles

### Desenvolvimento

Profile: dev

Banco:

* H2 Database

### Produção

Profile: prod

Banco:

* PostgreSQL

## Executando a Aplicação

```bash
mvn spring-boot:run
```

## Executando os Testes

```bash
mvn test
```

## Gerando o Build

```bash
mvn clean package
```
