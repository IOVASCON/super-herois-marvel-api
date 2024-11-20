# Super-Heróis Marvel API

Bem-vindo ao repositório da **Super-Heróis Marvel API**, um projeto que implementa uma API RESTful reativa utilizando o Spring WebFlux e DynamoDB para gerenciamento de informações de heróis do universo Marvel e DC.

Este projeto foi desenvolvido como parte de um desafio prático com o objetivo de aprender, configurar e integrar tecnologias modernas de desenvolvimento de APIs.

## 📜 Objetivos do Projeto

1. **Criar uma API Reativa**: Implementar endpoints para gerenciar heróis com Spring WebFlux.
2. **Utilizar o DynamoDB**: Configurar e integrar um banco de dados NoSQL (DynamoDB Local) com Spring Data.
3. **Praticar Boas Práticas**: Estruturar o código de maneira modular, clara e escalável.
4. **Aprendizado Hands-on**: Exercitar a configuração de dependências, escrita de código e depuração em projetos Spring Boot.

## 🛠️ Tecnologias Utilizadas

- **Linguagem de Programação**: Java 17
- **Framework Backend**: Spring Boot 3.3.5 com Spring WebFlux
- **Banco de Dados**: DynamoDB Local
- **Bibliotecas**:
  - AWS SDK (DynamoDB)
  - Spring Data DynamoDB
  - Lombok para geração de código
- **Servidor Local**: Netty
- **Gerenciador de Dependências**: Maven

## 📂 Estrutura do Projeto

super-herois-marvel-api ├── src │ ├── main │ │ ├── java/iovascon/projetando │ │ │ ├── config # Configurações do DynamoDB │ │ │ ├── constants # Constantes do projeto │ │ │ ├── document # Modelos (Entidades do DynamoDB) │ │ │ ├── repository # Repositórios (Spring Data DynamoDB) │ │ │ └── controller # Endpoints da API │ │ └── resources │ │ ├── application.properties # Configurações da aplicação │ │ └── data.sql # Scripts de inicialização (opcional) ├── pom.xml # Configurações do Maven e dependências └── README.md # Documentação do projeto

## 📋 Endpoints da API

| Método | Endpoint            | Descrição                                   |
|--------|---------------------|---------------------------------------------|
| GET    | `/heroes`           | Retorna todos os heróis cadastrados.       |
| GET    | `/heroes/{id}`      | Retorna os detalhes de um herói por ID.    |
| POST   | `/heroes`           | Adiciona um novo herói ao banco de dados.  |
| PUT    | `/heroes/{id}`      | Atualiza os dados de um herói existente.   |
| DELETE | `/heroes/{id}`      | Remove um herói pelo ID.                   |

## 🖥️ Comandos Utilizados

### Configurar o DynamoDB Local

1. **Baixar o DynamoDB Local**

    wget <https://s3.us-west-2.amazonaws.com/dynamodb-local/dynamodb_local_latest.zip>

2. **Executar o DynamoDB Local**

java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

## Configurar o Projeto

1. Clonar o Repositório:

    git clone <https://github.com/IOVASCON/super-herois-marvel-api.git>
    cd super-herois-marvel-api

2. Compilar o Projeto

    .mvn clean install

3. Empacotar o Projeto

    ./mvnw clean package

4. Executar a Aplicação

    java -jar target/super-herois-marvel-api-1.0-SNAPSHOT.jar

## Executar o Projeto

1. Executar Localmente:

    mvn spring-boot:run

2. Acessar a API:

- URL base: <http://localhost:8080>

⚙️ **Configurações Importantes**

Arquivo application.properties

## Configuração do DynamoDB Local

amazon.dynamodb.endpoint=<http://localhost:8000>
amazon.dynamodb.region=us-west-2
aws.access_key_id=fakeAccessKey
aws.secret_access_key=fakeSecretKey

## Porta do servidor

server.port=8080

## Configurações do Spring Boot

spring.application.name=super-herois-marvel-api
spring.main.allow-bean-definition-overriding=true

🛠️ Configuração do DynamoDB Local

## Criando a Tabela no DynamoDB

A tabela é configurada automaticamente pelo arquivo HeroesTable.java. Após iniciar o projeto, a tabela será criada no DynamoDB Local com os seguintes atributos:

1. Tabela: Heroes
2. Chave Primária: id (tipo String)

## Dados Iniciais

Dados fictícios são inseridos no DynamoDB Local automaticamente através da classe HeroesData.java.

🚀 **Imagens do Desenvolvimento do Projeto**

![Aplicação Iniciada com Sucesso](src/images/Aplicacao_Iniciada_Sucesso_Porta8080.PNG)
![Banco de Dados Rodando](src/images/DynamoDB_Local_Rodando.PNG)
![Gerando o Pacote](src/images/Limpando_Reconstruindo_Projeto.PNG)
![Gerando MANIFEST.MF](src/images/Confirmando_Adicao_MANIFEST_MF.PNG)

📖 **Aprendizados**

1. Configuração de um banco de dados NoSQL local com DynamoDB.
2. Uso do Spring WebFlux para criar uma API reativa.
3. Gerenciamento de dependências com Maven.
4. Boas práticas na estruturação de projetos Spring Boot.

🚀 **Próximos Passos**

1. Implementar testes unitários e de integração.
2. Adicionar documentação Swagger para a API.
3. Configurar a API para ser implantada em um ambiente de produção.

🤝 **Contribuições**

Sinta-se à vontade para contribuir com melhorias para este projeto. Basta abrir uma issue ou enviar um pull request.

📜 **Licença**

Este projeto está licenciado sob a MIT License. Consulte o arquivo LICENSE para mais informações.
