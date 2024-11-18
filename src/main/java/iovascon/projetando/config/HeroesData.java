package iovascon.projetando.config;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import iovascon.projetando.constants.HeroesConstant;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class HeroesData {

        private final DynamoDB dynamoDB;

        public HeroesData(DynamoDB dynamoDB) {
                this.dynamoDB = dynamoDB;
        }

        @PostConstruct
        public void initData() {
                Table table = dynamoDB.getTable(HeroesConstant.HEROES_TABLE_NAME);

                // Dados fictícios de heróis
                table.putItem(new Item().withPrimaryKey("id", "2")
                                .withString("name", "Mulher Maravilha")
                                .withString("universe", "dc comics")
                                .withNumber("films", 2));

                table.putItem(new Item().withPrimaryKey("id", "3")
                                .withString("name", "Viuva Negra")
                                .withString("universe", "marvel")
                                .withNumber("films", 2));

                table.putItem(new Item().withPrimaryKey("id", "4")
                                .withString("name", "Capitã Marvel")
                                .withString("universe", "marvel")
                                .withNumber("films", 2));
        }
}
