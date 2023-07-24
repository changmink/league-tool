import java.util.UUID;

public class Player {
    private String id;
    private String name;
    private int win;
    private int lose;
    private int tie;

    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getTie() {
        return tie;
    }

    public void win() {
        this.win++;
    }

    public void lose() {
        this.lose++;
    }

    public void tie() {
        this.tie++;
    }

    public int getScore() {
        return win * 3 + tie * 1;
    }

    @Override
    public String toString() {
        return name + "(" + this.getScore() + ")";
    }
}
