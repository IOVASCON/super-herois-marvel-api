package iovascon.projetando.service;

import iovascon.projetando.document.Heroes;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HeroesService {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "Heroes";

    public HeroesService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public Flux<Heroes> findAll() {
        return Mono.fromCallable(() -> {
            ScanRequest scanRequest = ScanRequest.builder()
                    .tableName(tableName)
                    .build();

            return dynamoDbClient.scan(scanRequest).items().stream()
                    .map(this::mapToHero)
                    .collect(Collectors.toList());
        }).flatMapMany(Flux::fromIterable)
                .onErrorResume(e -> {
                    System.err.println("Erro ao buscar todos os heróis: " + e.getMessage());
                    return Flux.empty();
                });
    }

    public Mono<Heroes> findByIdHero(String id) {
        return Mono.fromCallable(() -> {
            Map<String, AttributeValue> key = Map.of("id", AttributeValue.builder().s(id).build());

            GetItemRequest getRequest = GetItemRequest.builder()
                    .tableName(tableName)
                    .key(key)
                    .build();

            Map<String, AttributeValue> item = dynamoDbClient.getItem(getRequest).item();
            if (item == null || item.isEmpty()) {
                throw new RuntimeException("Herói não encontrado com o ID: " + id);
            }

            return mapToHero(item);
        }).onErrorResume(e -> {
            System.err.println("Erro ao buscar herói por ID: " + id + " - " + e.getMessage());
            return Mono.empty();
        });
    }

    public Mono<Heroes> save(Heroes heroes) {
        if (heroes == null || heroes.getName() == null || heroes.getName().isEmpty()) {
            return Mono
                    .error(new IllegalArgumentException("Herói inválido. Certifique-se de fornecer um nome válido."));
        }

        return Mono.fromRunnable(() -> {
            Map<String, AttributeValue> item = mapToAttributes(heroes);

            PutItemRequest putRequest = PutItemRequest.builder()
                    .tableName(tableName)
                    .item(item)
                    .build();

            dynamoDbClient.putItem(putRequest);
        }).thenReturn(heroes)
                .onErrorResume(e -> {
                    System.err.println("Erro ao salvar o herói: " + e.getMessage());
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteByIdHero(String id) {
        return Mono.fromRunnable(() -> {
            try {
                DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                        .tableName(tableName)
                        .key(Map.of("id", AttributeValue.builder().s(id).build()))
                        .build();

                dynamoDbClient.deleteItem(deleteRequest);
                System.out.println("Herói deletado com sucesso: " + id);
            } catch (Exception e) {
                System.err.println("Erro ao deletar herói com ID " + id + ": " + e.getMessage());
                throw new RuntimeException("Erro ao deletar herói", e);
            }
        });
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
