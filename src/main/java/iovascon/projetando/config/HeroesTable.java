package iovascon.projetando.config;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import iovascon.projetando.constants.HeroesConstant;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.Collections;

@Configuration
public class HeroesTable {

    private final DynamoDB dynamoDB;

    public HeroesTable(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    @PostConstruct
    public void createTable() {
        try {
            System.out.println("Criando tabela, aguarde...");
            Table table = dynamoDB.createTable(
                    HeroesConstant.HEROES_TABLE_NAME,
                    Collections.singletonList(new KeySchemaElement("id", KeyType.HASH)),
                    Collections.singletonList(new AttributeDefinition("id", ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L, 5L));
            table.waitForActive();
            System.out.println("Tabela criada com sucesso: " + table.getDescription().getTableStatus());
        } catch (Exception e) {
            System.err.println("Não foi possível criar a tabela: " + e.getMessage());
        }
    }
}
