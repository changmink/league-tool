import dev.changmin.league.core.Game;
import dev.changmin.league.core.GameResult;
import dev.changmin.league.core.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void success_tie_game_end() {
        Player player = new Player("test1");
        Game game = new Game(player, player);
        assertTrue(game.isEnded());

        assertEquals(player.getScore(), 1);
        assertEquals(player.getTie(), 1);
        assertEquals(player.getLose(), 0);
        assertEquals(player.getWin(), 0);
    }

    private static void make_not_ended_new_game(Player player1, Player player2, Game game) {
        assertNotEquals(player1, player2);
        assertFalse(game.isEnded());

        assertEquals(player1.getScore(), 0);
        assertEquals(player1.getTie(), 0);
        assertEquals(player1.getLose(), 0);
        assertEquals(player1.getWin(), 0);

        assertEquals(player2.getScore(), 0);
        assertEquals(player2.getTie(), 0);
        assertEquals(player2.getLose(), 0);
        assertEquals(player2.getWin(), 0);
    }

    @Test
    public void success_player1_win() {
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");

        Game game = new Game(player1, player2);

        make_not_ended_new_game(player1, player2, game);

        game.end(GameResult.PLAYER1_WIN);

        assertTrue(game.isEnded());
        assertEquals(player1.getScore(), 3);
        assertEquals(player1.getTie(), 0);
        assertEquals(player1.getLose(), 0);
        assertEquals(player1.getWin(), 1);

        assertEquals(player2.getScore(), 0);
        assertEquals(player2.getTie(), 0);
        assertEquals(player2.getLose(), 1);
        assertEquals(player2.getWin(), 0);
    }

    @Test
    public void success_player2_win() {
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");

        Game game = new Game(player1, player2);

        make_not_ended_new_game(player1, player2, game);

        game.end(GameResult.PLAYER2_WIN);

        assertTrue(game.isEnded());
        assertEquals(player1.getScore(), 0);
        assertEquals(player1.getTie(), 0);
        assertEquals(player1.getLose(), 1);
        assertEquals(player1.getWin(), 0);

        assertEquals(player2.getScore(), 3);
        assertEquals(player2.getTie(), 0);
        assertEquals(player2.getLose(), 0);
        assertEquals(player2.getWin(), 1);
    }

    @Test
    public void success_tie_game() {
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");

        Game game = new Game(player1, player2);

        make_not_ended_new_game(player1, player2, game);

        game.end(GameResult.TIE);

        assertTrue(game.isEnded());
        assertEquals(player1.getScore(), 1);
        assertEquals(player1.getTie(), 1);
        assertEquals(player1.getLose(), 0);
        assertEquals(player1.getWin(), 0);

        assertEquals(player2.getScore(), 1);
        assertEquals(player2.getTie(), 1);
        assertEquals(player2.getLose(), 0);
        assertEquals(player2.getWin(), 0);
    }
}
