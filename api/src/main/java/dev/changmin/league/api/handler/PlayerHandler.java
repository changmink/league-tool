package dev.changmin.league.api.handler;

import dev.changmin.league.api.Repository;
import dev.changmin.league.api.dto.PlayerRequest;
import dev.changmin.league.core.Player;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class PlayerHandler {
    private final Repository repository;

    public PlayerHandler(Repository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        String leagueId = serverRequest.pathVariable("league_id");
        return ServerResponse.ok().bodyValue(this.repository.getLeague(leagueId).getPlayers());
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        String leagueId = serverRequest.pathVariable("league_id");
        String id = serverRequest.pathVariable("id");
        Optional<Player> player = this.repository.getLeague(leagueId)
                .getPlayers()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findAny();
        if (player.isPresent()) {
            ServerResponse.ok().bodyValue(player.get());
        }

        return ServerResponse.notFound().build();
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
