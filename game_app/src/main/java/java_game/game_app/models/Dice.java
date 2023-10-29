package java_game.game_app.models;

import java.util.Random;

public class Dice {
    public int DiceRoll() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
