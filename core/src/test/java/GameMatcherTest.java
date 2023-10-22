import dev.changmin.league.core.Game;
import dev.changmin.league.core.GameMatcher;
import dev.changmin.league.core.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class GameMatcherTest {
    @Test
    public void fail_round_number() {
        GameMatcher gameMatcher = new GameMatcher();
        assertThrows(IllegalArgumentException.class, () -> {
            gameMatcher.match(List.of(), 0);
        });
    }

    @Test
    public void success_empty_player() {
        GameMatcher gameMatcher = new GameMatcher();
        Map<String, Game> games = gameMatcher.match(List.of(), 1);
        assertTrue(games.isEmpty());
    }

    @Test
    public void success_one_player() {
        Player player = new Player("test1");
        GameMatcher gameMatcher = new GameMatcher();
        Map<String, Game> games = gameMatcher.match(List.of(player), 1);

        assertFalse(games.isEmpty());
        assertEquals(games.size(), 1);
    }

    @Test
    public void success_two_player_first_round() {
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");
        List<Player> list = new ArrayList<>();
        list.add(player1);
        list.add(player2);
        GameMatcher gameMatcher = new GameMatcher();
        // list.of로 생성한 리스트는 imutableList 이다!!!
        Map<String, Game> games = gameMatcher.match(list, 1);

        assertFalse(games.isEmpty());
        assertEquals(games.size(), 1);
    }

    @Test
    public void success_three_player_first_round() {
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");
        Player player3 = new Player("test3");

        List<Player> list = new ArrayList<>();
        list.add(player1);
        list.add(player2);
        list.add(player3);

        GameMatcher gameMatcher = new GameMatcher();

        // list.of로 생성한 리스트는 imutableList 이다!!!
        Map<String, Game> games = gameMatcher.match(list, 1);

        assertFalse(games.isEmpty());
        assertEquals(games.size(), 2);
    }

    @Test
    public void success_four_player_first_round() {
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");
        Player player3 = new Player("test3");
        Player player4 = new Player("test4");
        List<Player> list = new ArrayList<>();
        list.add(player1);
        list.add(player2);
        list.add(player3);
        list.add(player4);
        GameMatcher gameMatcher = new GameMatcher();

        // list.of로 생성한 리스트는 imutableList 이다!!!
        Map<String, Game> games = gameMatcher.match(list, 1);

        assertFalse(games.isEmpty());
        assertEquals(games.size(), 2);
    }

    @Test
    public void success_player_first_round() {
        int playersNumber = 100;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playersNumber; i++) {
            players.add(new Player("test" + i));
        }

        GameMatcher gameMatcher = new GameMatcher();
        Map<String, Game> games = gameMatcher.match(players, 1);

        assertEquals(games.size(), playersNumber/2);
    }





    @Test
    public void success_two_player_second_round() {
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");
        List<Player> list = new ArrayList<>();
        list.add(player1);
        list.add(player2);
        GameMatcher gameMatcher = new GameMatcher();
        // list.of로 생성한 리스트는 imutableList 이다!!!
        Map<String, Game> games = gameMatcher.match(list, 1);

        assertFalse(games.isEmpty());
        assertEquals(games.size(), 1);
    }

    @Test
    public void success_player_second_round() {
        int playersNumber = 100;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playersNumber; i++) {
            players.add(new Player("test" + i));
        }

        GameMatcher gameMatcher = new GameMatcher();
        Map<String, Game> games = gameMatcher.match(players, 2);

        assertEquals(games.size(), playersNumber/2);
    }

}
