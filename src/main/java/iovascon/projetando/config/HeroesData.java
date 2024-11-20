package iovascon.projetando.config;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;

@Component
public class HeroesData {

        private final DynamoDbClient dynamoDbClient;

        public HeroesData(DynamoDbClient dynamoDbClient) {
                this.dynamoDbClient = dynamoDbClient;
        }

        public void populateData() {
                addHero("1", "Mulher Maravilha", "dc comics", 2);
                addHero("2", "Viúva Negra", "marvel", 2);
                addHero("3", "Capitã Marvel", "marvel", 2);
        }

        private void addHero(String id, String name, String universe, int films) {
                try {
                        Map<String, AttributeValue> item = Map.of(
                                        "id", AttributeValue.builder().s(id).build(),
                                        "name", AttributeValue.builder().s(name).build(),
                                        "universe", AttributeValue.builder().s(universe).build(),
                                        "films", AttributeValue.builder().n(String.valueOf(films)).build());

                        PutItemRequest request = PutItemRequest.builder()
                                        .tableName("Heroes")
                                        .item(item)
                                        .build();

                        dynamoDbClient.putItem(request);
                        System.out.println("Herói adicionado: " + name);
                } catch (Exception e) {
                        System.err.println("Erro ao adicionar herói " + name + ": " + e.getMessage());
                }
        }
}
