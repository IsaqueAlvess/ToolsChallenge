# 💳 API de Pagamentos - Tools Challenge

![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9.9-C71A36?logo=apachemaven&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-lightgrey?logo=open-source-initiative&logoColor=white)
![Build](https://img.shields.io/badge/Build-passing-success?logo=githubactions&logoColor=white)
![Status](https://img.shields.io/badge/Status-Estável-brightgreen)

---

### 🧾 Descrição

API REST desenvolvida em **Java 17 + Spring Boot 3**, responsável por gerenciar **transações de pagamento e estorno**,  
seguindo **boas práticas de arquitetura, validação, documentação e tratamento de erros**.

O projeto foi desenvolvido como parte do **Tools Challenge**, com foco em:
- ✅ Design limpo (camadas bem separadas)
- ✅ DTOs de entrada e saída
- ✅ Testes automatizados com JUnit 5 + Mockito
- ✅ Documentação via Swagger (OpenAPI)
- ✅ Banco em memória H2 para testes

---

### 📚 Principais Tecnologias

| Stack | Descrição |
|--------|------------|
| ☕ **Java 17** | Linguagem base |
| 🌱 **Spring Boot 3.5.7** | Framework para APIs REST |
| 🗃️ **Spring Data JPA** | Persistência com ORM |
| 🧩 **H2 Database** | Banco em memória para testes |
| 🧾 **Lombok** | Redução de boilerplate |
| 🧠 **Swagger / Springdoc OpenAPI** | Documentação interativa |
| 🧪 **JUnit 5 + Mockito** | Testes unitários e de integração |

---

## 🏗️ Arquitetura
A API segue uma arquitetura **camada limpa**, separando responsabilidades:


---

## ⚙️ Funcionalidades

- Swagger UI: http://localhost:8080/swagger-ui.html

| Funcionalidade | Método | Endpoint | Descrição |
|----------------|--------|-----------|------------|
| 🧾 Criar pagamento | `POST` | `/api/v1/pagamentos` | Cria e processa um novo pagamento |
| 🔍 Consultar todos | `GET` | `/api/v1/pagamentos` | Lista todas as transações |
| 🔎 Consultar por ID | `GET` | `/api/v1/pagamentos/{id}` | Retorna os dados de uma transação |
| 🔁 Estornar pagamento | `POST` | `/api/v1/pagamentos/{id}/estorno` | Cancela (estorna) uma transação existente |

---

## 💾 Instalação e Execução

### 🔹 Pré-requisitos
- Java 17+
- Maven 3.8+
- IDE: IntelliJ / Eclipse / VS Code

### 🔹 Passos para executar

```bash
# 1. Clone o repositório
git clone https://github.com/IsaqueAlvess/ToolsChallenge

# 2. Acesse o diretório
cd ToolsChallenge

# 3. Execute o projeto
mvn spring-boot:run

http://localhost:8080
