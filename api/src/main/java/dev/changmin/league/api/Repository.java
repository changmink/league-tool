package dev.changmin.league.api;

import dev.changmin.league.core.League;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class Repository {
    private Map<String, League> leagues = new HashMap<>();

    public Collection<League> getLeagues() {
        return leagues.values();
    }

    public League addLeague(League league) {
        return leagues.put(league.getId(), league);
    }

    public League getLeague(String id) {
        return leagues.get(id);
    }
}
