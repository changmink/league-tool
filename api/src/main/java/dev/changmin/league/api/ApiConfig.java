package dev.changmin.league.api;

import dev.changmin.league.api.handler.GameHandler;
import dev.changmin.league.api.handler.LeagueHandler;
import dev.changmin.league.api.handler.MatchHandler;
import dev.changmin.league.api.handler.PlayerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class ApiConfig {
    @Bean
    public RouterFunction route(LeagueHandler leagueHandler) {
        return RouterFunctions.route()
                .POST("/leagues", leagueHandler::post)
                .GET("/leagues", leagueHandler::get)
                .GET("/leagues/{id}", leagueHandler::getById)
                .POST("/leagues/{league_id}/players", PlayerHandler::post)
                .GET("/leagues/{league_id}/players", PlayerHandler::get)
                .GET("/leagues/{league_id}/players/{id}", PlayerHandler::getById)
                .POST("/leagues/{league_id}/matches", MatchHandler::post)
                .GET("/leagues/{league_id}/matches", MatchHandler::get)
                .GET("/leagues/{league_id}/matches/{id}", MatchHandler::getById)
                .POST("/leagues/{league_id}/matches/{id}/games", GameHandler::post)
                .GET("/leagues/{league_id}/matches/{id}/games", GameHandler::get)
                .GET("/leagues/{league_id}/matches/{match_id}/games/{id}", GameHandler::getById)
                .build();
    }
}
