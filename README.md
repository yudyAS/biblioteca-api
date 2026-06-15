# 📚 Biblioteca API

## 📖 Descrição do Projeto

A Biblioteca API é uma aplicação REST desenvolvida em Java utilizando Spring Boot para gerenciamento de livros e categorias. O sistema permite cadastrar, consultar, atualizar e remover livros, além de realizar buscas por autor, título e disponibilidade.

O projeto foi desenvolvido para fins acadêmicos na disciplina de Programação Orientada a Objetos (POO), aplicando conceitos de arquitetura em camadas, persistência de dados, testes automatizados e documentação de APIs.

---

## 🚀 Tecnologias Utilizadas

* Java 17
* Spring Boot 3
* Spring Data JPA
* Hibernate
* H2 Database
* PostgreSQL
* Maven
* JUnit 5
* Mockito
* JaCoCo
* Swagger/OpenAPI

---

## 📋 Funcionalidades

### Livros

* Cadastrar livro
* Listar todos os livros
* Buscar livro por ID
* Atualizar livro
* Excluir livro
* Buscar por autor
* Buscar por título
* Listar livros disponíveis

### Categorias

* Associação de livros a categorias
* Busca de categoria por ID
* Busca de categoria por nome

---

## 📂 Estrutura do Projeto

```text
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

* Java 17 ou superior
* Git
* Maven (opcional, pois o projeto utiliza Maven Wrapper)

Verifique as instalações:

```bash
java -version
mvn -version
```

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

```text
http://localhost:8080/h2-console
```

---

## 📖 Documentação Swagger

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 📌 Endpoints e Exemplos cURL

### Criar Livro

POST `/api/livros`

```bash
curl -X POST http://localhost:8080/api/livros \
-H "Content-Type: application/json" \
-d '{
  "titulo":"Clean Code",
  "autor":"Robert C. Martin",
  "isbn":"9780132350884",
  "anoPublicacao":2008
}'
```

### Buscar Livro por ID

GET `/api/livros/{id}`

```bash
curl http://localhost:8080/api/livros/1
```

### Listar Livros

GET `/api/livros`

```bash
curl http://localhost:8080/api/livros
```

### Atualizar Livro

PUT `/api/livros/{id}`

```bash
curl -X PUT http://localhost:8080/api/livros/1 \
-H "Content-Type: application/json" \
-d '{
  "titulo":"Clean Code 2"
}'
```

### Excluir Livro

DELETE `/api/livros/{id}`

```bash
curl -X DELETE http://localhost:8080/api/livros/1
```

### Buscar por Autor

GET `/api/livros/autor/{autor}`

```bash
curl http://localhost:8080/api/livros/autor/Robert%20C.%20Martin
```

### Buscar por Título

GET `/api/livros/titulo/{titulo}`

```bash
curl http://localhost:8080/api/livros/titulo/Clean%20Code
```

### Buscar Disponíveis

GET `/api/livros/disponiveis`

```bash
curl http://localhost:8080/api/livros/disponiveis
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

Linux/Mac:

```bash
./mvnw test jacoco:report
```

Relatório:

```text
target/site/jacoco/index.html
```

---

## 🚀 Deploy

### Gerar o arquivo JAR

Windows:

```powershell
.\mvnw.cmd clean package
```

Linux/Mac:

```bash
./mvnw clean package
```

Arquivo gerado:

```text
target/biblioteca-api.jar
```

### Executar em Produção

```bash
java -jar target/biblioteca-api.jar
```

### Variáveis de Ambiente

Configure as seguintes variáveis:

```env
SPRING_PROFILES_ACTIVE=prod

DB_URL=jdbc:postgresql://localhost:5432/biblioteca
DB_USERNAME=postgres
DB_PASSWORD=senha
```

### Verificação

Após iniciar a aplicação:

```text
http://localhost:8080
```

---

## 👥 Divisão de Tarefas

### Yudy Antunes dos Santos

* Desenvolvimento das entidades (Model)
* Configuração do banco de dados
* Criação dos repositórios

### Renan Lucas Innocêncio

* Implementação dos serviços (Service)
* Regras de negócio
* Testes automatizados

### Rafael Wenceslau Maximo

* Desenvolvimento dos Controllers
* Documentação Swagger
* README e documentação do projeto

---

## 👨‍💻 Equipe

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos (POO).

Integrantes:

* Renan Lucas Innocêncio
* Rafael Wenceslau Maximo
* Yudy Antunes dos Santos

---

## 📄 Licença

Projeto acadêmico desenvolvido exclusivamente para fins educacionais.
