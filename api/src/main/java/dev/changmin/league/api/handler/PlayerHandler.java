package dev.changmin.league.api.handler;

import dev.changmin.league.api.Repository;
import dev.changmin.league.api.dto.PlayerRequest;
import dev.changmin.league.core.Player;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PlayerHandler {
    private final Repository repository;

    public PlayerHandler(Repository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> post(ServerRequest serverRequest) {
        String leagueId = serverRequest.pathVariable("league_id");
        return serverRequest.bodyToMono(PlayerRequest.class)
                .flatMap(playerRequest -> {
                    String name = playerRequest.getName();
                    Player player = new Player(name);
                    this.repository.addPlayer(leagueId, player);
                    return ServerResponse.ok().build();
                });
    }
}
