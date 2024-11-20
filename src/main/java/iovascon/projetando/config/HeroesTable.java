package iovascon.projetando.config;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@Component
public class HeroesTable {

    private final DynamoDbClient dynamoDbClient;

    public HeroesTable(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void createTable() {
        try {
            CreateTableRequest request = CreateTableRequest.builder()
                    .tableName("Heroes")
                    .keySchema(KeySchemaElement.builder()
                            .attributeName("id")
                            .keyType(KeyType.HASH)
                            .build())
                    .attributeDefinitions(AttributeDefinition.builder()
                            .attributeName("id")
                            .attributeType(ScalarAttributeType.S)
                            .build())
                    .provisionedThroughput(ProvisionedThroughput.builder()
                            .readCapacityUnits(5L)
                            .writeCapacityUnits(5L)
                            .build())
                    .build();

            dynamoDbClient.createTable(request);
            System.out.println("Tabela criada com sucesso!");
        } catch (ResourceInUseException e) {
            System.out.println("Tabela já existe.");
        } catch (Exception e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}
