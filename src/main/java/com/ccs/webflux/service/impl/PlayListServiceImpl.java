package com.ccs.webflux.service.impl;

import com.ccs.webflux.document.PlayList;
import com.ccs.webflux.repository.PlayListRepository;
import com.ccs.webflux.service.PlayListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PlayListServiceImpl implements PlayListService {

    private PlayListRepository repository;

    @Override
    public Flux<PlayList> findAll() {

        return repository.findAll();
    }

    @Override
    public Mono<PlayList> findById(String id) {

        return repository.findById(id);
    }

    @Override
    public Mono<PlayList> save(PlayList playList) {

        return repository.save(playList);
    }
}
