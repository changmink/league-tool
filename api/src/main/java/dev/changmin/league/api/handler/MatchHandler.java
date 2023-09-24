package dev.changmin.league.api.handler;

import dev.changmin.league.api.Repository;
import dev.changmin.league.core.Game;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class MatchHandler {
    private final Repository repository;

    public MatchHandler(Repository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        String leagueId = serverRequest.pathVariable("league_id");
        Map<String, Game> matches = repository.getMatches(leagueId);
        if (null == matches) {
            return ServerResponse.notFound().build();
        }
        return ServerResponse.ok().bodyValue(matches);
    }

    public Mono<ServerResponse> post(ServerRequest serverRequest) {
        String leagueId = serverRequest.pathVariable("league_id");
        Map<String, Game> matches = repository.getLeague(leagueId).match();
        repository.addMatches(leagueId, matches);
        return ServerResponse.ok().bodyValue(matches);
    }
}
