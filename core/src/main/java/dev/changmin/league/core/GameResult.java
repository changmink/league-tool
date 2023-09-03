package dev.changmin.league.core;

public enum GameResult {
    TIE(0), PLAYER1_WIN(1), PLAYER2_WIN(2);

    public final int value;

    GameResult(int value) {
        this.value = value;
    }
}
