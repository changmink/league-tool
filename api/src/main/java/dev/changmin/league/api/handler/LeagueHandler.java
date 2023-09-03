package dev.changmin.league.api.handler;


import dev.changmin.league.api.dto.LeagueDTO;
import dev.changmin.league.core.GameMatcher;
import dev.changmin.league.core.League;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class LeagueHandler {
    private GameMatcher gameMatcher = new GameMatcher();
    private Map<String, League> leagues = new HashMap<>();

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> post(ServerRequest serverRequest) {
        Mono<LeagueDTO> leagueDTOMono = serverRequest.bodyToMono(LeagueDTO.class);
        return leagueDTOMono.flatMap(leagueDTO -> {
            League league = new League(gameMatcher, new ArrayList<>(), leagueDTO.getName());
            leagues.put(league.getId(), league);
            leagueDTO.setId(league.getId());
            return ServerResponse.ok().bodyValue(leagueDTO);
        });
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        return null;
    }
}
