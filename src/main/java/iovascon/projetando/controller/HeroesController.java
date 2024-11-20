package iovascon.projetando.controller;

import iovascon.projetando.document.Heroes;
import iovascon.projetando.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static iovascon.projetando.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@Slf4j
@RestController
public class HeroesController {

    private final HeroesService heroesService;

    public HeroesController(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllItems() {
        log.info("Requesting the list of all heroes");
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id) {
        log.info("Requesting the hero with id {}", id);
        return heroesService.findByIdHero(id)
                .map(hero -> new ResponseEntity<>(hero, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Heroes>> createHero(@RequestBody Heroes heroes) {
        if (heroes.getName() == null || heroes.getName().isEmpty()) {
            log.error("Invalid Hero: missing name");
            return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        log.info("A new Hero was created: {}", heroes.getName());
        return heroesService.save(heroes)
                .map(savedHero -> new ResponseEntity<>(savedHero, HttpStatus.CREATED));
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Void>> deleteByIdHero(@PathVariable String id) {
        log.info("Deleting the hero with id {}", id);
        return heroesService.deleteByIdHero(id)
                .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .onErrorResume(e -> {
                    log.error("Error deleting hero with id {}: {}", id, e.getMessage());
                    return Mono.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }
}
