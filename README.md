![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger)
![H2 Database](https://img.shields.io/badge/Database-H2-blue?style=for-the-badge)

# 🕵️‍♂️ API de Gestão de Operações Táticas (Agente API)

## 🎯 Objetivo do Projeto
O **Agente API** atua como o cérebro digital de uma agência de inteligência, orquestrando operações táticas e missões confidenciais. Desenvolvido para gerenciar uma logística crítica e segura, o sistema controla a alocação de agentes de campo, organiza esquadrões de elite e monitora a infraestrutura das bases operacionais.

Quando uma missão secreta é deflagrada, a API assume o fluxo de ponta a ponta: validando credenciais e níveis de autorização, despachando a equipe tática adequada e garantindo o arquivamento do relatório de debriefing ao fim da operação. Esta aplicação foi construída com o framework **Spring Boot**, focando em automatizar regras de negócio complexas através de uma arquitetura RESTful moderna, robusta e escalável.

---

## ⚙️ Funcionamento Básico e Como Executar

A API é inicializada localmente na porta `8080` e utiliza um banco de dados relacional H2 configurado em memória (*resetado a cada nova inicialização*).

Para rodar e testar a aplicação localmente:

1. **Execução:** Inicie o projeto executando a classe principal `AgenteApplication` através da sua IDE ou terminal.
2. **Acesso ao Swagger:** Com o servidor de pé, acesse a interface interativa em:  
   👉 `http://localhost:8080/swagger-ui/index.html`
3. **Fluxo de Teste do CRUD:** Devido às restrições de integridade relacional, os cadastros via método `POST` devem seguir esta ordem lógica:
   - 1º Cadastre um **Cargo** e uma **Base Operacional**;
   - 2º Cadastre o **Esquadrão** (*vinculando ao ID da Base*);
   - 3º Cadastre a **Missão**;
   - 4º Cadastre o **Agente** (*vinculando aos IDs de Cargo, Esquadrão e Missão*);
   - 5º Cadastre o **Relatório** (*vinculando ao ID da Missão encerrada*).
4. **Navegação Hipermídia (HATEOAS):** Nas requisições de listagem (`GET`), a API retorna os dados com paginação nativa e links `_links` embutidos no JSON de resposta.
5. **Monitoramento (Actuator):**
   - Saúde da API e Banco: `http://localhost:8080/actuator/health`
   - Métricas do Sistema: `http://localhost:8080/actuator/metrics`

---

## 🗂️ Entidades e Relacionamentos

O sistema mapeia e gerencia 6 entidades interconectadas:

* **Cargo:** Define atribuições e níveis de autorização.
* **Base Operacional:** Instalação física que abriga as equipes de campo.
* **Esquadrão:** Equipes táticas vinculadas a uma Base Operacional (`@ManyToOne`).
* **Agente:** Integrantes associados a um Cargo (`@ManyToOne`) e a um Esquadrão (`@ManyToOne`).
* **Missão:** Operações confidenciais que envolvem múltiplos agentes (`@ManyToMany`).
* **Relatório:** Documentação oficial gerada após o encerramento de uma Missão (`@OneToOne`).

---

## 🛠️ Requisitos Técnicos Implementados

- **Spring Data JPA:** Camada de persistência integrada ao banco H2.
- **Paginação e Ordenação:** Configurada nativamente nos métodos de busca principal (`Pageable`).
- **HATEOAS:** Links de navegação dinâmica injetados diretamente na resposta das entidades.
- **Spring Cache:** Cache habilitado nas consultas com a anotação `@Cacheable`.
- **Spring Boot Actuator:** Exposição de métricas de saúde da aplicação.
- **Swagger / OpenAPI:** Documentação de API automatizada e testável diretamente pelo navegador.
