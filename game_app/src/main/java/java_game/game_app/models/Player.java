package java_game.game_app.models;

public class Player {
    private int id;
    private String name;
    private int position;
    private int totalMoves;
    private int gamesPlayed;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.position = 0;
        this.totalMoves = 0;
        this.gamesPlayed = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public void setTotalMoves(int totalMoves) {
        this.totalMoves = totalMoves;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public void setName(int playerIndex, String playerName) {
        this.name = playerName;
        this.id = playerIndex;
    }

    // Other methods as needed
}
