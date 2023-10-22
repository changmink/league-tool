package dev.changmin.league.api.handler;

import dev.changmin.league.api.Repository;
import dev.changmin.league.api.dto.GameRequest;
import dev.changmin.league.core.Game;
import dev.changmin.league.core.GameResult;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GameHandler {
    private final Repository repository;

    public GameHandler(Repository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> post(ServerRequest serverRequest) {
        String leagueId = serverRequest.pathVariable("league_id");
        String id = serverRequest.pathVariable("id");
        Map<String, Game> matches = repository.getMatches(leagueId);
        if (null == matches) {
            return ServerResponse.notFound().build();
        }

        Game game = matches.get(id);
        if (null == game) {
            return ServerResponse.notFound().build();
        }
        return serverRequest.bodyToMono(GameRequest.class)
                .flatMap(gameRequest -> {
                    if (gameRequest.getResult() > 2) {
                        return Mono.error(new IllegalArgumentException());
                    }
                    int result = gameRequest.getResult();
                    game.end(GameResult.values()[result]);
                    return ServerResponse.ok().build();
                });
    }


    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        String leagueId = serverRequest.pathVariable("league_id");
        Map<String, Game> matches = repository.getMatches(leagueId);
        if (null == matches) {
            return ServerResponse.notFound().build();
        }
        return ServerResponse.ok().bodyValue(matches);
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        String leagueId = serverRequest.pathVariable("league_id");
        String id = serverRequest.pathVariable("id");
        Map<String, Game> matches = repository.getMatches(leagueId);
        if (null == matches) {
            return ServerResponse.notFound().build();
        }

        Game game = matches.get(id);
        if (null == game) {
            return ServerResponse.notFound().build();
        }

        return ServerResponse.ok().bodyValue(game);
    }
}
