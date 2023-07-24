import java.util.UUID;

public class Game {
    private String id;
    private Player[] players;
    private boolean ended;

    public Game(Player player1, Player player2) {
        this.id = UUID.randomUUID().toString();
        this.players = new Player[2];
        this.players[0] = player1;
        this.players[1] = player2;
        this.ended = false;
        if (player1.equals(player2)) {
            this.ended = true;
            player1.tie();
        }
    }

    public void end(GameResult result) {
        if (ended) {
            return;
        }

        if (players[0].equals(players[1])) {
            players[0].tie();
        } else if (result == GameResult.PLAYER1_WIN) {
            players[0].win();
            players[1].lose();
        } else if (result == GameResult.PLAYER2_WIN) {
            players[0].lose();
            players[1].win();
        } else {
            players[0].tie();
            players[1].tie();
        }
        ended = true;
    }

    @Override
    public String toString() {
        if (players[0].equals(players[1])) {
            return id + "\n" + players[0] + " 부전승";
        }
        return id + "\n" + players[0] + " VS " + players[1];
    }

    public String getId() {
        return id;
    }

    public boolean isEnded() {
        return ended;
    }
}
