package java_game.game_app.models;

import java.util.HashMap;
import java.util.Map;

public class Board {

    public final static int BOARD_SIZE = 100;
    public static Map<Integer, Integer> snakes = new HashMap<>();
    public static Map<Integer, Integer> ladders = new HashMap<>();

    {
        snakes.put(96, 54);
        snakes.put(70, 55);
        snakes.put(52, 42);
        snakes.put(25, 2);
        snakes.put(95, 72);

        ladders.put(6, 25);
        ladders.put(11, 40);
        ladders.put(60, 85);
        ladders.put(46, 96);
        ladders.put(17, 69);
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public void setSnakes(Map<Integer, Integer> snakes) {
        this.snakes = snakes;
    }

    public void setLadders(Map<Integer, Integer> ladders) {
        this.ladders = ladders;
    }
}
