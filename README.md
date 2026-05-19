# API de Gestão de Operações Táticas (Agente API)

## 🎯 Objetivo do Projeto
O **Agente API** atua como o cérebro digital de uma agência de inteligência, orquestrando operações táticas e missões confidenciais. Desenvolvido para gerenciar uma logística crítica e segura, o sistema controla a alocação de agentes de campo, organiza esquadrões de elite e monitora a infraestrutura das bases operacionais dos agentes.

Quando uma missão secreta é deflagrada, a API assume o fluxo de ponta a ponta: validando credenciais e níveis de autorização, despachando a equipe tática adequada e garantindo o arquivamento do relatório de debriefing ao fim da operação. Essa aplicação foi construída com o framework Spring Boot, com foco em automatizar regras de negócio complexas através de uma arquitetura RESTful moderna, robusta e escalável.

## ⚙️ Funcionamento Básico e Como Executar
A API é inicializada localmente na porta `8080` e utiliza um banco de dados relacional H2 configurado para rodar totalmente em memória (os dados são resetados a cada nova inicialização do servidor).

Para rodar e testar a aplicação localmente, siga o fluxo abaixo:

1. **Execução**: Inicie o projeto executando a classe principal `AgenteApplication` através da sua IDE.
2. **Acesso ao Swagger**: Com o servidor de pé, abra o seu navegador e acesse a interface gráfica interativa em: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
3. **Fluxo de Teste do CRUD**: Como o banco possui restrições de integridade pelos relacionamentos, os cadastros via método `POST` devem seguir rigorosamente esta ordem lógica:
    * **1º** Cadastre um **Cargo** e uma **Base Operacional**;
    * **2º** Cadastre o **Esquadrão** (vinculando ao ID da Base);
    * **3º** Cadastre a **Missão**;
    * **4º** Cadastre o **Agente** (vinculando aos IDs de Cargo, Esquadrão e Missão);
    * **5º** Cadastre o **Relatório** (vinculando ao ID da Missão encerrada).
4. **Navegação Hipermídia**: Ao efetuar as requisições de listagem (`GET`), a API retornará os dados com paginação nativa e links **HATEOAS** (`_links`) embutidos no JSON de resposta, orientando o cliente sobre os próximos caminhos disponíveis de navegação.
5. **Monitoramento**: Os endpoints do Actuator para auditoria do sistema podem ser validados diretamente pelas URLs:
    * Saúde da API e Banco: [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
    * Métricas de Hardware e Requisições: [http://localhost:8080/actuator/metrics](http://localhost:8080/actuator/metrics)

## 🗂️ Entidades e Relacionamentos do CRUD
O sistema mapeia e gerencia 6 entidades interconectadas:

1. **Cargo**: Define as atribuições e níveis de autorização dos colaboradores da agência.
2. **Base Operacional**: Instalação física que abriga as equipes de campo.
3. **Esquadrao**: Equipes táticas vinculadas a uma Base Operacional específica (`@ManyToOne`).
4. **Agente**: Integrantes da agência, associados obrigatoriamente a um Cargo (`@ManyToOne`) e a um Esquadrão (`@ManyToOne`).
5. **Missao**: Operações de campo confidenciais. Vários agentes podem atuar em múltiplas missões simultaneamente (`@ManyToMany`).
6. **Relatorio**: Documentação oficial e debriefing gerado obrigatoriamente após o encerramento de uma Missão específica (`@OneToOne`).

## 🛠️ Requisitos Técnicos Implementados
* **Spring Data JPA**: Camada de persistência robusta integrada ao banco de dados H2.
* **Paginação e Ordenação**: Configurada nativamente nos métodos de busca principal (`Pageable`) para otimização de performance.
* **HATEOAS**: Links de navegação dinâmica injetados diretamente na resposta das entidades via método `toEntityModel()`.
* **Spring Cache**: Mecanismo de cache habilitado estrategicamente com a anotação `@Cacheable` nas operações de consulta de dados.
* **Spring Boot Actuator**: Exposição configurada dos endpoints de gerenciamento e métricas de saúde da aplicação.
* **Swagger / OpenAPI**: Documentação de API automatizada, visual e testável diretamente pelo navegador.