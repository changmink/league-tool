import java.util.*;

public class GameMatcher {
    public Map<String, Game> match(List<Player> players, int roundNumber){
        if (roundNumber < 1) {
            throw new IllegalArgumentException("roundNumber should be over 1");
        }

        Map<String, Game> games = new HashMap<>();
        if (roundNumber == 1) {
            Collections.shuffle(players);
        } else {
            Collections.sort(players, (p1, p2) -> {
                return p2.getScore() - p1.getScore();
            });
        }

        int matches = players.size() / 2;

        for (int i = 0; i < players.size() - 1; i += 2) {
            Player player1 = players.get(i);
            Player player2 = players.get(i + 1);
            Game game = new Game(player1, player2);
            games.put(game.getId(), game);
        }

        if (matches * 2 < players.size()) {
            Player player = players.get(players.size()-1);
            Game game = new Game(player, player);
            games.put(game.getId(), game);
        }

        return games;
    }
}
