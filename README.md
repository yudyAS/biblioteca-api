# 📚 Biblioteca API

API REST desenvolvida com Spring Boot para gerenciamento de livros e categorias de uma biblioteca.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- H2 Database
- PostgreSQL
- Maven
- JUnit 5
- Mockito
- JaCoCo
- Swagger/OpenAPI

---

## 📋 Funcionalidades

### Livros

- Cadastrar livro
- Listar todos os livros
- Buscar livro por ID
- Atualizar livro
- Excluir livro
- Buscar por autor
- Buscar por título
- Listar livros disponíveis

### Categorias

- Associação de livros a categorias
- Busca de categoria por ID
- Busca de categoria por nome

---

## 📂 Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   └── br.com.biblioteca
│   │       ├── controller
│   │       ├── dto
│   │       ├── exception
│   │       ├── mapper
│   │       ├── model
│   │       ├── repository
│   │       └── service
│   └── resources
│       ├── application.properties
│       ├── application-dev.properties
│       └── application-prod.properties
└── test
```

---

## ⚙️ Pré-requisitos

- Java 17 ou superior
- Git
- Maven (opcional, pois o projeto utiliza Maven Wrapper)

---

## ▶️ Executando o Projeto

### Clonar o repositório

```bash
git clone URL_DO_REPOSITORIO
```

### Entrar na pasta

```bash
cd biblioteca-api
```

### Executar aplicação

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Linux/Mac:

```bash
./mvnw spring-boot:run
```

---

## 🗄️ Banco de Dados

### Desenvolvimento

Banco H2 em memória.

Console:

```
http://localhost:8080/h2-console
```

---

## 📖 Documentação Swagger

Após iniciar a aplicação:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📌 Endpoints

### Criar Livro

POST

```
/api/livros
```

Exemplo:

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "9780132350884",
  "anoPublicacao": 2008
}
```

---

### Buscar Livro por ID

GET

```
/api/livros/{id}
```

---

### Listar Livros

GET

```
/api/livros
```

---

### Atualizar Livro

PUT

```
/api/livros/{id}
```

---

### Excluir Livro

DELETE

```
/api/livros/{id}
```

---

### Buscar por Autor

GET

```
/api/livros/autor/{autor}
```

---

### Buscar por Título

GET

```
/api/livros/titulo/{titulo}
```

---

### Buscar Disponíveis

GET

```
/api/livros/disponiveis
```

---

## 🧪 Testes

Executar todos os testes:

Windows:

```powershell
.\mvnw.cmd test
```

Linux/Mac:

```bash
./mvnw test
```

---

## 📊 Cobertura de Testes (JaCoCo)

Gerar relatório:

Windows:

```powershell
.\mvnw.cmd test jacoco:report
```

Relatório:

```
target/site/jacoco/index.html
```

---

## 👨‍💻 Equipe

Projeto desenvolvido para a disciplina de POO.
Nome dos integrantes:
Renan Lucas Innocêncio
Rafael Wenceslau Maximo
Yudy Antunes dos Santos

---

## 📄 Licença

Projeto acadêmico para fins educacionais.