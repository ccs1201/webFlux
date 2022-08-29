package com.ccs.webflux.api.core.handler;

import com.ccs.webflux.document.PlayList;
import com.ccs.webflux.service.PlayListService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

//@Component
@AllArgsConstructor
public class PlayListHandler {

    private PlayListService service;

    public Mono<ServerResponse> findAll(ServerRequest request) {

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), PlayList.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {

        String id = request.pathVariable("id");

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findById(id), PlayList.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<PlayList> playListMono = request.bodyToMono(PlayList.class);

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        fromPublisher(
                                playListMono.flatMap(service::save), PlayList.class));
    }

}
