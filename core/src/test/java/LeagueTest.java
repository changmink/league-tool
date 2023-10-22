import dev.changmin.league.core.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LeagueTest {
    private GameMatcher gameMatcher = new GameMatcher();

    @Test
    public void success_first_league_zero_players() {
        int playersNumber = 0;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playersNumber; i++) {
            players.add(new Player("test" + i));
        }

        League league = new League(gameMatcher, players, "test");
        Map<String, Game> games = league.match();
        assertTrue(games.isEmpty());
    }

    @Test
    public void success_league_total_round_3() {
        for (int count = 1; count <= 8; ++count) {
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                players.add(new Player("test" + i));
            }

            League league = new League(gameMatcher, players, "test" + count);
            Map<String, Game> games = league.match();
            assertFalse(games.isEmpty());

            assertEquals(league.totalRound(), 3);
        }
    }

    @Test
    public void success_league_total_round_4() {
        for (int count = 9; count <= 16; ++count) {
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                players.add(new Player("test" + i));
            }

            League league = new League(gameMatcher, players, "test" + count);
            Map<String, Game> games = league.match();
            assertFalse(games.isEmpty());

            assertEquals(league.totalRound(), 4);
        }
    }

    @Test
    public void success_league_total_round_5() {
        for (int count = 17; count <= 32; ++count) {
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                players.add(new Player("test" + i));
            }

            League league = new League(gameMatcher, players, "test" + count);
            Map<String, Game> games = league.match();
            assertFalse(games.isEmpty());

            assertEquals(league.totalRound(), 5);
        }
    }

    @Test
    public void success_league_total_round_6() {
        for (int count = 33; count <= 64; ++count) {
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                players.add(new Player("test" + i));
            }

            League league = new League(gameMatcher, players, "test" + count);
            Map<String, Game> games = league.match();
            assertFalse(games.isEmpty());

            assertEquals(league.totalRound(), 6);
        }
    }

    @Test
    public void success_league_total_round_7() {
        for (int count = 65; count <= 128; ++count) {
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                players.add(new Player("test" + i));
            }

            League league = new League(gameMatcher, players, "test" + count);
            Map<String, Game> games = league.match();
            assertFalse(games.isEmpty());

            assertEquals(league.totalRound(), 7);
        }
    }

    @Test
    public void success_league_total_round_8() {
        for (int count = 129; count <= 256; ++count) {
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                players.add(new Player("test" + i));
            }

            League league = new League(gameMatcher, players, "test" + count);
            Map<String, Game> games = league.match();
            assertFalse(games.isEmpty());

            assertEquals(league.totalRound(), 8);
        }
    }

    @Test
    public void success_league_total_round_9() {
        for (int count = 257; count <= 512; ++count) {
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                players.add(new Player("test" + i));
            }

            League league = new League(gameMatcher, players, "test" + count);
            Map<String, Game> games = league.match();
            assertFalse(games.isEmpty());

            assertEquals(league.totalRound(), 9);
        }
    }

    @Test
    public void success_league_total_round_10() {
        for (int count = 513; count <= 1024; ++count) {
            List<Player> players = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                players.add(new Player("test" + i));
            }

            League league = new League(gameMatcher, players, "test" + count);
            Map<String, Game> games = league.match();
            assertFalse(games.isEmpty());

            assertEquals(league.totalRound(), 10);
        }
    }

    // 힙이 터져서 그냥 만명까지만 한다.
    @Test
    public void success_league_total_round_10_max() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            players.add(new Player("test" + i));
        }

        League league = new League(gameMatcher, players, "test");

        assertEquals(league.totalRound(), 10);
    }

    @Test
    public void success_all_end_game() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 32; i++ ){
            players.add(new Player( "test" + i));
        }

        League league = new League(gameMatcher, players, "test");
        Map<String, Game> games = league.match();
        boolean allGameEnded = false;
        for (String id : games.keySet()) {
            assertFalse(allGameEnded);
            allGameEnded = league.endGame(id, GameResult.PLAYER1_WIN.value);
        }

        assertTrue(allGameEnded);
    }

    @Test
    public void fail_wrong_game_id() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 8; i++ ){
            players.add(new Player( "test" + i));
        }

        League league = new League(gameMatcher, players, "test");
        league.match();
        assertThrows(IllegalArgumentException.class, () -> {
            league.endGame("id", GameResult.PLAYER1_WIN.value);
        });
    }

    @Test
    public void success_end_league() {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 8; i++ ){
            players.add(new Player( "test" + i));
        }

        League league = new League(gameMatcher, players, "test");

        for (int i = 0; i < 3; ++i) {
            Map<String, Game> games = league.match();
            boolean allGameEnded = false;
            for (String id : games.keySet()) {
                assertFalse(allGameEnded);
                allGameEnded = league.endGame(id, GameResult.PLAYER1_WIN.value);
            }

            assertTrue(allGameEnded);
        }

        assertTrue(league.isEnded());

    }
}
