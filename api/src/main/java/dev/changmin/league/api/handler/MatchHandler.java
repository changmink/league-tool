package dev.changmin.league.api.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MatchHandler {
    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> post(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        return null;
    }
}
