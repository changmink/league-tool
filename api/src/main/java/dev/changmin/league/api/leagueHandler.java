package dev.changmin.league.api;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class leagueHandler {

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        return null;
    }
}
