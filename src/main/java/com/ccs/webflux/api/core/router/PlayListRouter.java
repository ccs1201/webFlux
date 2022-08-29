package com.ccs.webflux.api.core.router;

import com.ccs.webflux.api.core.handler.PlayListHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

//@Configuration
public class PlayListRouter {

    private static final String URI = "/api/playlists";

    @Bean
    public RouterFunction<ServerResponse> route(PlayListHandler handler) {
        return RouterFunctions
                .route(GET(URI)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET(URI+"/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)), handler::findById)
                .andRoute(POST(URI)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::save);
    }

}
