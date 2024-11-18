package iovascon.projetando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;

/**
 * Classe principal da aplicação Super Heróis Marvel API.
 * Inicializa a aplicação Spring Boot com suporte ao DynamoDB.
 */
@SpringBootApplication
@EnableDynamoDBRepositories(basePackages = "iovascon.projetando.repository") // Especificar o pacote das interfaces de
                                                                             // repositórios
public class SuperHeroisMarvelApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperHeroisMarvelApiApplication.class, args);
        System.out.println("API de Super-Heróis Marvel com Spring WebFlux iniciada!");
    }
}
