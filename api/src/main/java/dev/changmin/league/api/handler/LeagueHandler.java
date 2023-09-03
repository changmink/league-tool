package dev.changmin.league.api.handler;


import dev.changmin.league.api.Repository;
import dev.changmin.league.api.dto.LeagueDTO;
import dev.changmin.league.core.GameMatcher;
import dev.changmin.league.core.League;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class LeagueHandler {
    private GameMatcher gameMatcher = new GameMatcher();
    private final Repository repository;

    public LeagueHandler(Repository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue(repository.getLeagues());
    }

    public Mono<ServerResponse> post(ServerRequest serverRequest) {
        Mono<LeagueDTO> leagueDTOMono = serverRequest.bodyToMono(LeagueDTO.class);
        return leagueDTOMono.flatMap(leagueDTO -> {
            League league = new League(gameMatcher, new ArrayList<>(), leagueDTO.getName());
            repository.addLeague(league);
            return ServerResponse.ok().bodyValue(league);
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
