# **Resumo Completo do Projeto e do Desafio**

---

## **1. Objetivo do Projeto**

O desafio consiste em desenvolver uma **API reativa** com **Spring Boot** para gerenciar super-heróis, baseada nas arquiteturas modernas usadas por grandes empresas. Essa API utiliza o **DynamoDB**, um banco de dados NoSQL, para armazenar as informações dos heróis. Ela foi projetada para funcionar localmente usando uma configuração simplificada, mas pode ser adaptada facilmente para um ambiente na nuvem.

## **2. O que é uma API e sua Finalidade?**

- **API** (Interface de Programação de Aplicações) é um conjunto de regras e ferramentas que permite que sistemas diferentes se comuniquem. Pense nela como uma "ponte" que conecta diferentes partes de um software ou até diferentes sistemas.

- **Finalidade da nossa API**:
  - Fornecer endpoints para **criar**, **consultar**, **atualizar** e **excluir** informações de super-heróis.
  - Exemplo de uso prático: um aplicativo ou site de entretenimento que deseja exibir detalhes de heróis da Marvel ou DC Comics pode usar essa API como backend para obter ou gerenciar as informações.

---

## **3. O Que Foi Feito?**

1. **Estrutura Inicial do Projeto**
   - Criamos o projeto utilizando **Maven**, que configurou automaticamente as pastas e arquivos necessários.
   - Adaptamos a estrutura do projeto para seguir boas práticas e incluir as funcionalidades esperadas.

2. **Configuração do Banco de Dados (DynamoDB Local)**
   - Optamos pelo **DynamoDB Local**, que simula o comportamento do banco de dados da AWS no seu computador, evitando custos e simplificando os testes.
   - Configuramos o `application.properties` para apontar para o banco local e garantimos que as propriedades fossem reconhecidas pelo Spring.

3. **Classes Principais Criadas**
   - **DynamoConfig.java**: Configura o acesso ao DynamoDB local.
   - **Heroes.java**: Representa os heróis como um modelo de dados (classe que reflete os campos da tabela no banco de dados).
   - **HeroesController.java**: Contém os endpoints HTTP para interagir com os dados (GET, POST, DELETE).
   - **HeroesService.java**: Lida com a lógica de negócios da aplicação.
   - **HeroesRepository.java**: Interage diretamente com o banco de dados, implementando consultas e operações CRUD.

4. **Melhorias Técnicas**
   - Integração do **Lombok** para reduzir o código repetitivo (getters, setters, etc.).
   - Uso de **propriedades personalizadas** para maior flexibilidade.
   - Configuração de logs com **Slf4j**, permitindo rastreamento das operações realizadas.
   - Testes básicos implementados para garantir a estabilidade do sistema.

5. **Constantes Definidas**
   - Criamos uma classe `HeroesConstant` para centralizar valores como o nome da tabela no DynamoDB e os endpoints da API, facilitando futuras alterações.

---

## **4. Benefícios Técnicos da API**

1. **Desempenho Reativo**:
   - A API utiliza **Spring WebFlux**, uma abordagem não-bloqueante que permite lidar com muitas requisições simultaneamente, ideal para sistemas modernos com alta demanda.

2. **Escalabilidade e Flexibilidade**:
   - Projetada para ser executada localmente, mas facilmente migrável para a nuvem (AWS DynamoDB, por exemplo).
   - Suporte a grandes volumes de dados, graças ao DynamoDB.

3. **Fácil Integração**:
   - Pode ser consumida por qualquer sistema ou aplicação que suporte chamadas HTTP (websites, aplicativos mobile, etc.).

4. **Centralização de Dados**:
   - A API gerencia as informações dos heróis em um único lugar, permitindo que diferentes sistemas acessem dados consistentes.

5. **Manutenção Simples**:
   - Uso de boas práticas como configuração baseada em propriedades e anotações do Lombok tornam o código mais fácil de manter e expandir.

---

## **5. Como Utilizar a API na Prática?**

1. **Subindo a API**:
   - Certifique-se de que o DynamoDB Local está em execução no terminal.
   - Inicie a API com o comando:
     ./mvnw spring-boot:run
   - A API estará acessível no endereço: `http://localhost:8080`.

2. **Endpoints Disponíveis**:
   - **GET /heroes**: Retorna todos os heróis cadastrados.
   - **GET /heroes/{id}**: Retorna informações de um herói específico pelo ID.
   - **POST /heroes**: Adiciona um novo herói. Exemplo de payload JSON:
     {
       "id": "1",
       "name": "Homem de Ferro",
       "universe": "Marvel",
       "films": 3
     }
   - **DELETE /heroes/{id}**: Remove um herói pelo ID.

3. **Ferramentas para Testar**:
   - **Postman** ou **cURL** podem ser usados para enviar requisições e validar o funcionamento da API.
   - Exemplo de comando `cURL` para testar o GET:
     curl -X GET <http://localhost:8080/heroes>

## **6. Próximos Passos**

1. **Testar a API**:
   - Após sua execução, validar cada endpoint e corrigir possíveis problemas.
   - Realizar testes com diferentes entradas para verificar a consistência dos dados.

2. **Documentação**:
   - Considerar o uso do Swagger para gerar documentação automática dos endpoints.

3. **Deploy Futuro**:
   - Com as configurações prontas, o deploy na nuvem pode ser feito sem dificuldades.
