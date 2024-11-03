# API com Spring Boot

## Descrição do Projeto

Este é um projeto de API RESTful desenvolvido em Java utilizando o framework Spring Boot. A API é estruturada para gerenciar e manipular dados de maneira segura e eficiente, oferecendo funcionalidades como persistência de dados com JPA, validação, segurança e suporte a HATEOAS para navegação entre recursos.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.0**
  - **Hibernate**: para integração com banco de dados utilizando JPA/Hibernate.
  - **Spring HATEOAS**: para adicionar links de navegação aos recursos da API.
  - **Spring Security**: para implementação de autenticação e autorização na API.
- **PostgreSQL**: banco de dados relacional utilizado para armazenamento de dados.
- **Maven**: gerenciador de dependências e build.

## Estrutura do Projeto

- `src/main/java`: contém as classes Java, organizadas conforme as camadas de uma arquitetura MVC.
  - **Controller**: gerencia os endpoints e a lógica das requisições HTTP.
  - **Service**: camada de lógica de negócios.
  - **Repository**: camada de acesso ao banco de dados usando Spring Data JPA.
  - **Model**: classes de modelo que representam as entidades.

## Pré-requisitos

Para executar o projeto, é necessário ter os seguintes itens instalados:

- **Java JDK 17** ou superior
- **PostgreSQL**
- **Maven**

## Configuração do Projeto

1. Clone o repositório para sua máquina local.
    ```bash
    git clone https://github.com/Death-Chronos/API-com-Spring.git
    ```

2. Configure o banco de dados PostgreSQL e atualize o arquivo `application.properties` em `src/main/resources/` com as credenciais de acesso.
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
    spring.datasource.username=seu-usuario
    spring.datasource.password=sua-senha
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Execute o projeto usando o Maven:
    ```bash
    mvn spring-boot:run
    ```

4. A API estará disponível em:
    ```
    http://localhost:8080
    ```

## Testes

Este projeto inclui dependências para testes automatizados. Para executar os testes, utilize o comando:
```bash
mvn test
