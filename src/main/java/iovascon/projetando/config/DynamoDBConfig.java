package iovascon.projetando.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração unificada do DynamoDB para registrar os beans do cliente e do
 * mapper.
 * Também habilita o uso de repositórios do Spring Data DynamoDB.
 */
@Configuration
@EnableDynamoDBRepositories(basePackages = "iovascon.projetando.repository")
public class DynamoDBConfig {

    private final DynamoDBProperties dynamoDBProperties;

    public DynamoDBConfig(DynamoDBProperties dynamoDBProperties) {
        this.dynamoDBProperties = dynamoDBProperties;
    }

    /**
     * Cria o cliente AmazonDynamoDB configurado para uso local.
     *
     * @return AmazonDynamoDB
     */
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        // Credenciais fictícias para o DynamoDB Local
        BasicAWSCredentials credentials = new BasicAWSCredentials("fakeAccessKey", "fakeSecretKey");

        // Configurando o cliente para o DynamoDB Local
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                dynamoDBProperties.getEndpoint(),
                                dynamoDBProperties.getRegion()))
                .build();
    }

    /**
     * Cria o DynamoDBMapper para realizar operações no DynamoDB.
     *
     * @param amazonDynamoDB Cliente AmazonDynamoDB
     * @return DynamoDBMapper
     */
    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }
}
