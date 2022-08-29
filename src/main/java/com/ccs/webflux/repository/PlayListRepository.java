package com.ccs.webflux.repository;

import com.ccs.webflux.document.PlayList;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PlayListRepository extends ReactiveMongoRepository<PlayList, String> {
}
