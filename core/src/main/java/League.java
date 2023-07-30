import java.util.List;
import java.util.Map;
import java.util.UUID;

public class League {
    private String id;
    private GameMatcher matcher;
    private List<Player> players;
    private Map<String, Game> games;
    private int round;
    public League(GameMatcher matcher, List<Player> players) {
        this.id = UUID.randomUUID().toString();
        this.matcher = matcher;
        this.players = players;
        this.round = 1;
    }

    public Map<String, Game> match() {
        this.games = matcher.match(this.players, this.round++);
        return this.games;
    }

    public boolean endGame(String id, int result) {
        if (games.containsKey(id)) {
            Game game = games.get(id);
            game.end(GameResult.values()[result]);
            return isAllEndGame();
        }
        throw new IllegalArgumentException("Not Found Game");
    }

    private boolean isAllEndGame() {
        for (Game game : this.games.values()) {
            if(!game.isEnded()) {
                return false;
            }
        }
        return true;
    }

    public int totalRound() {
        int n = players.size();
        if (n <= 8) {
            return 3;
        }
        if (n <= 16) {
            return 4;
        }
        if (n <= 32) {
            return 5;
        }
        if (n <= 64) {
            return 6;
        }
        if (n <= 128) {
            return 7;
        }
        if (n <= 256) {
            return 8;
        }
        if (n <= 512) {
            return 9;
        }
        return 10;
    }

    public boolean isEnded() {
        return round > totalRound();
    }
}
