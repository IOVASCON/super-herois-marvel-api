package iovascon.projetando.service;

import iovascon.projetando.document.Heroes;
import iovascon.projetando.repository.HeroesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {

    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    public Flux<Heroes> findAll() {
        return Flux.fromIterable(this.heroesRepository.findAll())
                .onErrorResume(e -> {
                    System.err.println("Erro ao buscar todos os heróis: " + e.getMessage());
                    return Flux.empty();
                });
    }

    public Mono<Heroes> findByIdHero(String id) {
        return Mono.justOrEmpty(this.heroesRepository.findById(id))
                .switchIfEmpty(Mono.error(new RuntimeException("Herói não encontrado com o ID: " + id)))
                .onErrorResume(e -> {
                    System.err.println("Erro ao buscar herói por ID: " + id + " - " + e.getMessage());
                    return Mono.empty();
                });
    }

    public Mono<Heroes> save(Heroes heroes) {
        return Mono.justOrEmpty(this.heroesRepository.save(heroes))
                .onErrorResume(e -> {
                    System.err.println("Erro ao salvar o herói: " + e.getMessage());
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteByIdHero(String id) {
        return Mono.fromRunnable(() -> heroesRepository.deleteById(id));
    }

}
