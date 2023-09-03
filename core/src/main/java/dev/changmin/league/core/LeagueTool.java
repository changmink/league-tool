package dev.changmin.league.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LeagueTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Player> playerList = new ArrayList<>();
        String name;
        System.out.println("Input dev.changmin.league.core.Player name. end '-1'");
        while (true) {
            System.out.print("name: ");
            name = scanner.nextLine();

            if (name.equals("-1")) {
                break;
            }

            Player player = new Player(name);
            playerList.add(player);
        }

        League league = new League(new GameMatcher(), playerList, "league");

        while(!league.isEnded()) {
            Map<String, Game> games = league.match();
            for (Game game : games.values()) {
                System.out.println(game);
            }

            while (true) {
                System.out.print("dev.changmin.league.core.Game id: ");
                String id = scanner.nextLine();
                System.out.print("dev.changmin.league.core.Game result(0 - tie, 1 - p1 win, 2 - p2 win): ");
                int result = Integer.parseInt(scanner.nextLine());
                if (league.endGame(id, result)) {
                    break;
                }
            }
        }

    }
}
