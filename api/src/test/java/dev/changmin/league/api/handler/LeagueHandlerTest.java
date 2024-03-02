package dev.changmin.league.api.handler;

import dev.changmin.league.api.ApiConfig;
import dev.changmin.league.api.Repository;
import dev.changmin.league.api.dto.LeagueRequest;
import dev.changmin.league.core.GameMatcher;
import dev.changmin.league.core.League;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeagueHandlerTest {
    WebTestClient webTestClient;
    Repository repository;
    @BeforeEach
    public void setUp() {
        repository = new Repository();
        LeagueHandler leagueHandler = new LeagueHandler(repository);
        PlayerHandler playerHandler = new PlayerHandler(repository);
        MatchHandler matchHandler = new MatchHandler(repository);
        GameHandler gameHandler = new GameHandler(repository);

        RouterFunction<?> routes = new ApiConfig().route(leagueHandler, playerHandler, matchHandler, gameHandler);

        webTestClient = WebTestClient.bindToRouterFunction(routes).build();
    }

    @Test
    public void test_league_empty_and_added() {
        webTestClient.get()
                .uri("/leagues")
                .exchange()
                .expectBody().json("[]");

        repository.addLeague(new League(new GameMatcher(), List.of(), "test"));

        webTestClient.get()
                .uri("/leagues")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("[{\"name\":\"test\",\"players\":[],\"ended\":false}]");
    }

    @Test
    public void test_league_add() {
        LeagueRequest leagueRequest = new LeagueRequest("test");
        webTestClient.post()
                .uri("/leagues")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(leagueRequest), LeagueRequest.class)
                .exchange()
                .expectBody(League.class)
                .consumeWith(leagueEntityExchangeResult -> {
                    League league = leagueEntityExchangeResult.getResponseBody();
                    assertEquals(league.getName(), "test");
                });

        webTestClient.get()
                .uri("/leagues")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("[{\"name\":\"test\",\"players\":[],\"ended\":false}]");
    }

    @Test
    public void test_league_get_by_id() {
        LeagueRequest leagueRequest = new LeagueRequest("test");
        League league = webTestClient.post()
                .uri("/leagues")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(leagueRequest), LeagueRequest.class)
                .exchange()
                .expectBody(League.class)
                .returnResult()
                .getResponseBody();


        webTestClient.get()
                .uri("/leagues/1")
                .exchange()
                .expectStatus().is4xxClientError();

        webTestClient.get()
                .uri("/leagues/" + league.getId())
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(League.class)
                .consumeWith(leagueEntityExchangeResult -> {
                    League result = leagueEntityExchangeResult.getResponseBody();
                    assertEquals(result.getName(), "test");
                    assertEquals(result.getId(), league.getId());
                });

    }
}
