package dev.changmin.league.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ApiConfig {
    @Bean
    public RouterFunction route(leagueHandler leagueHandler) {
        return RouterFunctions.route()
                .GET("/leagues", leagueHandler::get)
                .GET("/leagues/{id}", request -> ServerResponse.ok().bodyValue("league_id"))
                .GET("/leagues/{league_id}/players", request -> ServerResponse.ok().bodyValue("players"))
                .GET("/leagues/{league_id}/players/{id}", request -> ServerResponse.ok().bodyValue("players_id"))
                .GET("/leagues/{league_id}/matches", request -> ServerResponse.ok().bodyValue("matches"))
                .GET("/leagues/{league_id}/matches/{id}", request -> ServerResponse.ok().bodyValue("matches_id"))
                .GET("/leagues/{league_id}/matches/{id}/games", request -> ServerResponse.ok().bodyValue("games"))
                .GET("/leagues/{league_id}/matches/{match_id}/games/{id}", request -> ServerResponse.ok().bodyValue("games_id"))
                .build();
    }
}
