package dev.changmin.league.api.handler;


import dev.changmin.league.api.Repository;
import dev.changmin.league.api.dto.LeagueRequest;
import dev.changmin.league.core.GameMatcher;
import dev.changmin.league.core.League;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class LeagueHandler {
    private final Logger logger = LoggerFactory.getLogger(LeagueRequest.class);
    private GameMatcher gameMatcher = new GameMatcher();
    private final Repository repository;

    public LeagueHandler(Repository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue(repository.getLeagues());
    }

    public Mono<ServerResponse> post(ServerRequest serverRequest) {
        Mono<LeagueRequest> leagueDTOMono = serverRequest.bodyToMono(LeagueRequest.class);
        return leagueDTOMono.flatMap(leagueRequest -> {
            League league = new League(gameMatcher, new ArrayList<>(), leagueRequest.getName());
            repository.addLeague(league);
            return ServerResponse.ok().bodyValue(league);
        }).onErrorResume(throwable -> {
            logger.error("{}", throwable);
            return Mono.error(throwable);
        });
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        League league = repository.getLeague(id);
        if (null == league) {
            return ServerResponse.notFound().build();
        }
        return ServerResponse.ok().bodyValue(league);
    }
}
