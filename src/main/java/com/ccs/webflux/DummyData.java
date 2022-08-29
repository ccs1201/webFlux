package com.ccs.webflux;

import com.ccs.webflux.document.PlayList;
import com.ccs.webflux.repository.PlayListRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

//@Component
@AllArgsConstructor
public class DummyData implements CommandLineRunner {

    private final PlayListRepository playlistRepository;

    @Override
    public void run(String... args) {

        playlistRepository.deleteAll()
                .thenMany(
                        Flux.just("API REST Spring Boot", "Deploy de uma aplicação java com Spring WebFlux", "Java 17",
                                        "Github", "Spring Security", "Web Service RESTFULL", "Bean no Spring Framework")
                                .map(nome -> new PlayList(UUID.randomUUID().toString(), nome))
                                .flatMap(playlistRepository::save))
                .subscribe(System.out::println);
    }
}
