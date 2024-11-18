package iovascon.projetando.repository;

import iovascon.projetando.document.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositório responsável por gerenciar operações na tabela de Heróis no
 * DynamoDB.
 * Extende a interface CrudRepository do Spring Data para fornecer operações
 * CRUD básicas.
 */
@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String> {

    /**
     * Busca heróis por nome.
     * 
     * @param name o nome do herói a ser buscado
     * @return uma lista de heróis com o nome correspondente
     */
    Iterable<Heroes> findByName(String name);

    /**
     * Busca heróis pelo universo (Marvel, DC, etc.).
     * 
     * @param universe o universo do herói
     * @return uma lista de heróis pertencentes ao universo informado
     */
    Iterable<Heroes> findByUniverse(String universe);
}
