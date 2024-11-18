package iovascon.projetando.service;

import iovascon.projetando.document.Heroes;
import iovascon.projetando.repository.HeroesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HeroesServiceTest {

    private HeroesRepository heroesRepository;
    private HeroesService heroesService;

    @BeforeEach
    void setUp() {
        heroesRepository = mock(HeroesRepository.class);
        heroesService = new HeroesService(heroesRepository);
    }

    @Test
    void testFindAll() {
        Heroes hero1 = new Heroes("1", "Superman", "DC Comics", 5);
        Heroes hero2 = new Heroes("2", "Iron Man", "Marvel", 3);

        when(heroesRepository.findAll()).thenReturn(Arrays.asList(hero1, hero2));

        Flux<Heroes> heroes = heroesService.findAll();

        assertEquals(2, heroes.collectList().block().size());
        verify(heroesRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdHero() {
        Heroes hero = new Heroes("1", "Wonder Woman", "DC Comics", 4);

        when(heroesRepository.findById("1")).thenReturn(Optional.of(hero));

        Mono<Heroes> foundHero = heroesService.findByIdHero("1");

        assertEquals("Wonder Woman", foundHero.block().getName());
        verify(heroesRepository, times(1)).findById("1");
    }

    @Test
    void testSave() {
        Heroes hero = new Heroes("3", "Thor", "Marvel", 3);

        when(heroesRepository.save(hero)).thenReturn(hero);

        Mono<Heroes> savedHero = heroesService.save(hero);

        assertEquals("Thor", savedHero.block().getName());
        verify(heroesRepository, times(1)).save(hero);
    }

    @Test
    void testDeleteByIdHero() {
        doNothing().when(heroesRepository).deleteById("1");

        Mono<Void> result = heroesService.deleteByIdHero("1");

        // Verificar se o método foi chamado corretamente
        result.block(); // Executa a operação
        verify(heroesRepository, times(1)).deleteById("1");
    }
}
