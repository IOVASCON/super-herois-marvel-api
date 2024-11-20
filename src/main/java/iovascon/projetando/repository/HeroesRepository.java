package iovascon.projetando.repository;

import iovascon.projetando.document.Heroes;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repositório manual para gerenciar operações na tabela de Heróis no DynamoDB.
 */
@Repository
public class HeroesRepository {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "Heroes";

    public HeroesRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public List<Heroes> findAll() {
        ScanRequest scanRequest = ScanRequest.builder().tableName(tableName).build();
        return dynamoDbClient.scan(scanRequest).items().stream()
                .map(this::mapToHero)
                .collect(Collectors.toList());
    }

    public Heroes findById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(id).build());

        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        Map<String, AttributeValue> item = dynamoDbClient.getItem(request).item();
        return item != null ? mapToHero(item) : null;
    }

    public void save(Heroes hero) {
        Map<String, AttributeValue> item = mapToAttributes(hero);

        PutItemRequest putRequest = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(putRequest);
    }

    public void deleteById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().s(id).build());

        DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        dynamoDbClient.deleteItem(deleteRequest);
    }

    private Heroes mapToHero(Map<String, AttributeValue> attributes) {
        return Heroes.builder()
                .id(attributes.get("id").s())
                .name(attributes.get("name").s())
                .universe(attributes.get("universe").s())
                .films(Integer.parseInt(attributes.get("films").n()))
                .build();
    }

    private Map<String, AttributeValue> mapToAttributes(Heroes hero) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("id", AttributeValue.builder().s(hero.getId()).build());
        attributes.put("name", AttributeValue.builder().s(hero.getName()).build());
        attributes.put("universe", AttributeValue.builder().s(hero.getUniverse()).build());
        attributes.put("films", AttributeValue.builder().n(String.valueOf(hero.getFilms())).build());
        return attributes;
    }
}
