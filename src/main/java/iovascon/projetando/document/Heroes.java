package iovascon.projetando.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa o modelo de dados de Heróis.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Heroes {
    private String id;
    private String name;
    private String universe;
    private int films;
}
