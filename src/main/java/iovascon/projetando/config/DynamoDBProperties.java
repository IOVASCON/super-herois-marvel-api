package iovascon.projetando.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Classe para mapeamento das propriedades de configuração do DynamoDB.
 * As propriedades são carregadas a partir do arquivo application.properties
 * com o prefixo "amazon.dynamodb".
 */
@Configuration
@ConfigurationProperties(prefix = "amazon.dynamodb")
public class DynamoDBProperties {

    private String endpoint;
    private String region;

    // Getters e Setters
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
