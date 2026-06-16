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

### 🐳 Build Docker Local

#### Pré-requisitos

* Docker instalado ([Download](https://www.docker.com/products/docker-desktop))

#### Build da imagem Docker

```bash
docker build -t biblioteca-api:latest .
```

#### Executar container localmente

```bash
# Desenvolvimento (com H2)
docker run -it --rm -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=dev \
  biblioteca-api:latest

# Produção (com PostgreSQL)
docker run -it --rm -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL=jdbc:postgresql://localhost:5432/biblioteca \
  -e DB_USER=postgres \
  -e DB_PASSWORD=sua_senha \
  biblioteca-api:latest
```

#### Testar a aplicação no container

```bash
# Em outro terminal
curl http://localhost:8080/api/categorias

# Swagger UI
http://localhost:8080/swagger-ui/index.html

# H2 Console (apenas desenvolvimento)
http://localhost:8080/h2-console
```

#### Parar o container

```bash
docker stop <container_id>
```

---

### 🌐 Deploy no Render

#### Pré-requisitos

* Conta no [Render](https://render.com)
* Repositório GitHub com o projeto
* Banco de dados PostgreSQL (Render oferece gratuitamente)

#### Passo 1: Preparar o GitHub

1. Faça push do projeto para o GitHub:

```bash
git add .
git commit -m "Preparar projeto para Docker e Render"
git push origin main
```

#### Passo 2: Criar serviço Web no Render

1. Acesse [Render Dashboard](https://dashboard.render.com)
2. Clique em **New +** → **Web Service**
3. Selecione **Build and deploy from a Git repository**
4. Conecte seu repositório GitHub
5. Configure:
   - **Name**: `biblioteca-api`
   - **Region**: Selecione a região mais próxima
   - **Branch**: `main`
   - **Runtime**: `Docker`
   - **Build Command**: (deixe em branco - Dockerfile já tem isso)
   - **Start Command**: (deixe em branco - Dockerfile já tem isso)

#### Passo 3: Configurar variáveis de ambiente

No Render Dashboard, na seção **Environment**:

```env
SPRING_PROFILES_ACTIVE=prod
PORT=8080
JAVA_OPTS=-Xmx512m -Xms256m
```

#### Passo 4: Configurar banco de dados PostgreSQL

1. No Render Dashboard, clique em **New +** → **PostgreSQL**
2. Crie um banco de dados
3. Copie as credenciais fornecidas
4. No seu serviço Web, adicione as variáveis:
   ```env
   DB_URL=postgresql://<username>:<password>@<host>:<port>/<dbname>
   DB_USER=<username>
   DB_PASSWORD=<password>
   ```

> **Nota**: Render fornece as informações de conexão automaticamente. Use-as para preencher as variáveis acima.

#### Passo 5: Deploy

1. Após configurar tudo, clique em **Deploy**
2. Monitore os logs em **Logs**
3. Assim que o deployment terminar, o serviço estará disponível em:
   ```
   https://<seu-servico-name>.onrender.com
   ```

#### Testar após deploy

```bash
# Listar categorias
curl https://<seu-servico-name>.onrender.com/api/categorias

# Swagger UI
https://<seu-servico-name>.onrender.com/swagger-ui/index.html
```

#### Monitorar a aplicação

- **Logs**: Render Dashboard → Seu serviço → **Logs**
- **Métricas**: Render Dashboard → Seu serviço → **Metrics**
- **Health Check**: O Dockerfile inclui verificação de saúde automática

---

### 🛠️ Troubleshooting - Deploy no Render

#### Erro: "Port is already in use"

- Render define a porta automaticamente via variável `PORT`
- Certifique-se de que `server.port=${PORT:8080}` está em `application.properties`

#### Erro: "Database connection refused"

- Verifique se as variáveis `DB_URL`, `DB_USER`, `DB_PASSWORD` estão corretas
- Certifique-se de que o banco PostgreSQL está em execução
- Verifique os logs no Render Dashboard

#### Aplicação muito lenta no Render

- Aumente a memória: `JAVA_OPTS=-Xmx1024m -Xms512m`
- Use um plano pago no Render com mais recursos

---

### 📦 Gerar o arquivo JAR localmente

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
target/biblioteca-api-0.0.1-SNAPSHOT.jar
```

### ▶️ Executar em Produção

```bash
# Com H2 (desenvolvimento)
java -jar target/biblioteca-api-*.jar

# Com PostgreSQL (produção)
java -jar target/biblioteca-api-*.jar \
  --spring.profiles.active=prod \
  --spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca \
  --spring.datasource.username=postgres \
  --spring.datasource.password=senha
```

### 🔧 Variáveis de Ambiente

Configure as seguintes variáveis para produção:

```env
SPRING_PROFILES_ACTIVE=prod
PORT=8080
DB_URL=jdbc:postgresql://host:5432/database
DB_USER=username
DB_PASSWORD=password
JAVA_OPTS=-Xmx512m -Xms256m
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
