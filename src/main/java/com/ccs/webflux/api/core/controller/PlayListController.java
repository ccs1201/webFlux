package com.ccs.webflux.api.core.controller;

import com.ccs.webflux.document.PlayList;
import com.ccs.webflux.service.PlayListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("/api/playlists")
@AllArgsConstructor
public class PlayListController {

    private PlayListService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<PlayList> findAll() {

        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PlayList> findById(@PathVariable String id) {

        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PlayList> save(@RequestBody PlayList playList) {

        return service.save(playList);
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tuple2<Long, PlayList>> getPlaylistByEvents() {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
        Flux<PlayList> playListFlux = service.findAll();
        System.out.println("Passou no events");

        return Flux.zip(interval, playListFlux);

    }
}
