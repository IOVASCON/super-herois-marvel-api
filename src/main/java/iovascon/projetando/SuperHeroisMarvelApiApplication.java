package iovascon.projetando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe principal da aplicação Super Heróis Marvel API.
 * Inicializa a aplicação Spring Boot.
 */
@SpringBootApplication
public class SuperHeroisMarvelApiApplication {

    private static final Logger logger = LoggerFactory.getLogger(SuperHeroisMarvelApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SuperHeroisMarvelApiApplication.class, args);
        logger.info("API de Super-Heróis Marvel iniciada com sucesso!");
    }
}
