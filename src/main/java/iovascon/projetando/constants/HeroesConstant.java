package iovascon.projetando.constants;

/**
 * Classe de constantes utilizadas na aplicação para configurações da API e do
 * DynamoDB.
 */
public final class HeroesConstant {

    // Construtor privado para evitar instância da classe de constantes
    private HeroesConstant() {
        throw new UnsupportedOperationException("Esta é uma classe utilitária e não pode ser instanciada.");
    }

    // Endpoint local para a API de heróis
    public static final String HEROES_ENDPOINT_LOCAL = "/heroes";

    // Configurações do DynamoDB Local
    public static final String DYNAMO_ENDPOINT = "http://localhost:8000";
    public static final String DYNAMO_REGION = "us-west-2";

    // Nome da tabela no DynamoDB
    public static final String HEROES_TABLE_NAME = "Heroes";
}
