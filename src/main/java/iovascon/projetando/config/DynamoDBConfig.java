package iovascon.projetando.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

/**
 * Configuração unificada do DynamoDB para registrar o cliente.
 */
@Configuration
public class DynamoDBConfig {

    private final String endpoint = "http://localhost:8000"; // Configurar conforme necessário
    private final String region = "us-west-2";
    private final String accessKey = "fakeAccessKey";
    private final String secretKey = "fakeSecretKey";

    @Bean
    public DynamoDbClient dynamoDbClient() {
        DynamoDbClientBuilder builder = DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)));

        if (endpoint != null && !endpoint.isEmpty()) {
            builder.endpointOverride(java.net.URI.create(endpoint));
        }

        return builder.build();
    }
}
