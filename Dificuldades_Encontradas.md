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
  - Garantimos que as propriedades no `application.properties` estavam apontando corretamente para `http://localhost:8000`.

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

## **10. Erros de Compilação no `pom.xml`**

- **Problema**: Dependências essenciais como `spring-boot-starter-webflux` e `spring-boot-starter-test` não tinham versões especificadas, gerando falhas.
- **Solução**:
  - Adicionamos versões explícitas no `pom.xml` para garantir compatibilidade:

        <properties>
        <spring.boot.version>3.3.5</spring.boot.version>
        <junit.jupiter.version>5.8.1</junit.jupiter.version>
        <dynamodb.version>5.1.0</dynamodb.version>
        </properties>

---

## **11. Testes e Logs**

- **Problema**: Não havia mensagens de log configuradas adequadamente para rastrear o funcionamento da API.
- **Solução**:
  - Adicionamos mensagens de log em pontos críticos no `HeroesController` e ajustamos os níveis de log no `application.properties`:

    logging.level.root=INFO
    logging.level.iovascon.projetando=DEBUG

---
