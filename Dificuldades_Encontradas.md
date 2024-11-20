# **Resumo das Dificuldades e Erros Enfrentados no Desenvolvimento**

Segue um relato estruturado das principais dificuldades e erros encontrados no desenvolvimento do desafio até o momento, acompanhado das soluções aplicadas:

---

## **1. Configuração Inicial do Projeto com Maven**

- **Problema**: Durante a criação inicial do projeto, o Maven não baixou todas as dependências necessárias, gerando erros de compilação.
- **Solução**
  - Garantimos que as versões das dependências no `pom.xml` estavam atualizadas.
  - Corrigimos versões específicas para evitar conflitos.
  - Executamos o comando `./mvnw clean install` repetidamente para baixar e sincronizar todas as bibliotecas.

---

## **2. Propriedades Não Reconhecidas no `application.properties`**

- **Problema**: Mensagens de erro no VSCode indicavam que as propriedades `amazon.dynamodb.endpoint`, `amazon.dynamodb.region`, `aws.access_key_id` e `aws.secret_access_key` eram "desconhecidas".
- **Solução**
  - Criamos o arquivo `META-INF/additional-spring-configuration-metadata.json` para registrar essas propriedades como válidas.
  - Ajustamos o VSCode para ignorar propriedades personalizadas no Spring Boot (`spring-boot.ls.suggest.unknownProperties: false`).

---

## **3. Dependência de Anotações `javax.annotation`**

- **Problema**: O projeto usava a biblioteca `javax.annotation`, que não é mais suportada no Java 17, gerando erros nas anotações como `@PostConstruct`.
- **Solução**:
  - Substituímos as dependências `javax.annotation` pela `jakarta.annotation-api` no `pom.xml`.
  - Atualizamos os imports no código para `import jakarta.annotation.PostConstruct`.

---

## **4. Erro ao Configurar o DynamoDB Local**

- **Problema**: O banco de dados DynamoDB Local não estava configurado corretamente, resultando em problemas de conectividade durante os testes iniciais.
- **Solução**:
  - Baixamos e executamos o DynamoDB Local com o comando:
    java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
  - Garantimos que as propriedades no `application.properties` estavam apontando corretamente para `http://localhost:8080`.

---

## **5. Problemas com a Classe `HeroesController`**

- **Problema**: A variável `log` não era reconhecida, e o método `getName()` de `Heroes` estava ausente, gerando erros de compilação.
- **Solução**:
  - Adicionamos a anotação `@Slf4j` para configurar o log com Lombok.
  - Corrigimos a classe `Heroes` para incluir os métodos getters e setters adequados.

---

## **6. Mensagem: "O Arquivo `DynamoDBProperties` Não Contém a Classe Especificada"**

- **Problema**: O Spring não reconhecia a classe `DynamoDBProperties`, gerando erros na configuração do DynamoDB.
- **Solução**:
  - Corrigimos o nome do arquivo e a localização na estrutura de pastas.
  - Adicionamos a anotação `@ConfigurationProperties` para mapear as propriedades do DynamoDB.

---

## **7. Configuração do Lombok**

- **Problema**: A dependência do Lombok estava instalada, mas as anotações não eram reconhecidas no VSCode.
- **Solução**:
  - Garantimos a dependência no `pom.xml`:

        <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
        </dependency>

  - Instalamos o plugin Lombok no IDE e habilitamos o suporte.

---

## **8. Configuração do Java 17**

- **Problema**: A configuração inicial usava a versão 1.8 do Java, incompatível com algumas dependências modernas.
- **Solução**:
  - Atualizamos o `pom.xml` para usar o Java 17:

        <properties>
        <java.version>17</java.version>
        </properties>
  - Configuramos o ambiente local (JDK e IDE) para a versão correta.

---

## **9. Alerta: "Variável `heroesRepository` Não Usada"**

- **Problema**: O IntelliSense sinalizou que a variável `heroesRepository` não estava sendo utilizada na classe `HeroesController`.
- **Solução**:
  - Removemos a variável do controlador, uma vez que ela era redundante e o serviço `HeroesService` já tratava a lógica de banco.

---

## **10. Erros de Bean no Repositório HeroesRepository

    Problema: O repositório HeroesRepository não era reconhecido corretamente devido à incompatibilidade do @EnableScan.
    Solução:
        Revisamos a anotação @EnableScan e o uso de CrudRepository para garantir compatibilidade.
        Reestruturamos o código para aderir às práticas do Spring Data DynamoDB.

---

## **11. Ajuste do Arquivo HeroesConstant

    Problema: O projeto não encontrava a constante HEROES_TABLE_NAME, gerando erros ao criar ou acessar a tabela no DynamoDB.
    Solução:
        Atualizamos o nome da tabela no arquivo HeroesConstant e verificamos sua consistência em todo o projeto.

---

## **12. Logs e Debugging do Spring Boot

    Problema: Os erros no console não eram claros, dificultando a identificação do problema exato.
    Solução:

        Ajustamos os níveis de log no application.properties para obter informações mais detalhadas:

      logging.level.root=INFO
      logging.level.org.springframework=DEBUG
      logging.level.iovascon.projetando=DEBUG

---

## **13. Persistência de Dados no DynamoDB

    Problema: A tabela Heroes não estava sendo criada corretamente no DynamoDB Local, e os dados fictícios não eram carregados.
    Solução:
        Garantimos a criação da tabela com o código apropriado no HeroesTable.
        Ajustamos o carregamento dos dados fictícios na classe HeroesData.

---

## **14. Adaptações no DynamoDB Local

    Problema: Configurações inadequadas ou ausentes dificultaram a execução do banco de dados local.
    Solução:
        Garantimos que o DynamoDB Local estava sendo executado com o comando:

java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

Ajustamos as configurações do application.properties para apontar para <http://localhost:8080>.

---

## **15. Erros na Execução da Aplicação

    Problema: O aplicativo falhou ao iniciar devido a conflitos no mapeamento de beans, principalmente no DynamoDBMapper.
    Solução:
        Refatoramos as classes de configuração (DynamoDBConfig) para garantir que todos os beans fossem registrados corretamente.
        Ajustamos o arquivo application.properties para refletir as configurações necessárias.

---

## **16. Arquivo .gitignore

    Problema: Arquivos desnecessários e sensíveis estavam sendo versionados no repositório.
    Solução:
        Adicionamos um .gitignore completo para evitar que arquivos temporários, de build e de configuração sensível fossem incluídos no controle de versão.

---

## **17. Erros de Compilação no `pom.xml`**

- **Problema**: Dependências essenciais como `spring-boot-starter-webflux` e `spring-boot-starter-test` não tinham versões especificadas, gerando falhas.
- **Solução**:
  - Adicionamos versões explícitas no `pom.xml` para garantir compatibilidade:

        <properties>
        <spring.boot.version>3.3.5</spring.boot.version>
        <junit.jupiter.version>5.8.1</junit.jupiter.version>
        <dynamodb.version>5.1.0</dynamodb.version>
        </properties>

---

## **18. Testes e Logs**

- **Problema**: Não havia mensagens de log configuradas adequadamente para rastrear o funcionamento da API.
- **Solução**:
  - Adicionamos mensagens de log em pontos críticos no `HeroesController` e ajustamos os níveis de log no `application.properties`:

    logging.level.root=INFO
    logging.level.iovascon.projetando=DEBUG

---

## **19. Erro de Inicialização da API devido ao Netty

    Problema: A classe HttpDecoderConfig do Netty não foi encontrada, causando erros de inicialização da API.
    Solução:
        Atualizamos as dependências do Netty no pom.xml para a versão mais recente (4.1.94.Final).
        Incluímos dependências específicas (netty-handler e netty-codec-http) no dependencyManagement para evitar problemas de compatibilidade.

## **20. Conflitos em Dependências do spring-boot-starter-webflux

    Problema: A dependência spring-boot-starter-webflux trazia uma versão desatualizada do Netty, gerando inconsistências.
    Solução:
        Excluímos todas as dependências relacionadas ao Netty da configuração do spring-boot-starter-webflux:

        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-webflux</artifactId>
          <version>${spring.boot.version}</version>
          <exclusions>
            <exclusion>
              <groupId>io.netty</groupId>
              <artifactId>*</artifactId>
            </exclusion>
          </exclusions>
        </dependency>

        Adicionamos manualmente as versões mais recentes do Netty no dependencyManagement.

## **21. Reinstalação de Dependências Corrompidas

    Problema: Dependências corrompidas no repositório local estavam impedindo a execução correta da API.
    Solução:
        Executamos o comando:

        mvn dependency:purge-local-repository

        Este comando limpou e forçou o download de todas as dependências novamente.

## **22. Reconstrução de Classes no Padrão Atual

    Problema: Algumas classes (controladores, serviços, e repositórios) não estavam alinhadas ao padrão atual do framework, causando inconsistências na API.
    Solução:
        Refatoramos todas as classes para seguir as práticas recomendadas:
            Usamos anotações apropriadas como @RestController, @Service e @Repository.
            Implementamos padrões de injeção de dependência usando o @Autowired e construtores.
            Ajustamos as rotas REST no HeroesController para refletir boas práticas de design.

## **23. Configuração de Logs

    Problema: Logs genéricos dificultavam o rastreamento de problemas durante a execução da API.
    Solução:
        Adicionamos níveis de log mais específicos no arquivo application.properties:

        logging.level.root=INFO
        logging.level.iovascon.projetando=DEBUG

## **24. Suporte ao DynamoDB Local

    Problema: O DynamoDB Local estava em conflito na porta 8000, impedindo a execução simultânea da aplicação.
    Solução:
        Alteramos a porta do DynamoDB para 8080 e configuramos o arquivo application.properties para refletir a nova porta:

        dynamodb.endpoint=http://localhost:8080

## **25. Ajustes no Empacotamento da Aplicação

    Problema: Havia confusão no uso dos comandos Maven (clean install vs. clean package) para empacotar e executar a aplicação.
    Solução:
        Documentamos o processo:
            Use ./mvnw clean install para testar, compilar e empacotar o projeto.
            Use java -jar target/super-herois-marvel-api-1.0-SNAPSHOT.jar para executar o JAR gerado.

## **26. Verificação de Conectividade

    Problema: A API mostrava mensagens de "Conexão Reiniciada" no navegador.
    Solução:
        Identificamos que o problema estava na falta de configurações de rede apropriadas e corrigimos as dependências que causavam erros de inicialização no Netty.
