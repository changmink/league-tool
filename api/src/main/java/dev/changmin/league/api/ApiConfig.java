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
    public RouterFunction route(LeagueHandler leagueHandler, PlayerHandler playerHandler, MatchHandler matchHandler,
                                GameHandler gameHandler) {
        return RouterFunctions.route()
                .POST("/leagues", leagueHandler::post)
                .GET("/leagues", leagueHandler::get)
                .GET("/leagues/{id}", leagueHandler::getById)
                .POST("/leagues/{league_id}/players", playerHandler::post)
                .GET("/leagues/{league_id}/players", playerHandler::get)
                .GET("/leagues/{league_id}/players/{id}", playerHandler::getById)
                .POST("/leagues/{league_id}/matches", matchHandler::post)
                .GET("/leagues/{league_id}/matches", matchHandler::get)
                .GET("/leagues/{league_id}/matches/{id}", matchHandler::getById)
                .POST("/leagues/{league_id}/matches/{id}/games", gameHandler::post)
                .GET("/leagues/{league_id}/matches/{id}/games", gameHandler::get)
                .GET("/leagues/{league_id}/matches/{match_id}/games/{id}", gameHandler::getById)
                .build();
    }
}
