package java_game.game_app.Game;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import java_game.game_app.models.Board;
import java_game.game_app.models.Dice;
import java_game.game_app.models.Player;

@Component
public class Game {
    private Board board;
    private Dice dice;
    private Player[] players;
    private int currentPlayerIndex;
    private boolean isGameOngoing;

    public Game(String... playerNames) {
        board = new Board();
        dice = new Dice();
        players = new Player[playerNames.length];
        isGameOngoing = true;

        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(i, playerNames[i]);
        }

        currentPlayerIndex = 0;
    }

    public List<Player> createPlayers(List<String> playerNames) {
        players = new Player[playerNames.size()];
        for (int i = 0; i < playerNames.size(); i++) {
            players[i] = new Player(i, playerNames.get(i));
        }
        currentPlayerIndex = 0;
        savePlayerInfoToFile("player_info.txt", Arrays.asList(players));
        return List.of(players);
    }

    public String makeMove(String playerName) {
        if (!isGameOngoing) {
            return "Game is over. A player has already won.";
        }
        // Find the player by name
        Player currentPlayer = null;

        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                currentPlayer = player;
                break;
            }
        }

        if (currentPlayer == null) {
            System.out.println("Player not found: " + playerName);
            return "Player not found.";
        }
        int diceRoll = dice.DiceRoll();
        int currentPosition = getCurrentPositionFromInfoFile(currentPlayer.getName());
        int withDiceRoll = currentPosition + diceRoll;

        if (withDiceRoll == Board.BOARD_SIZE) {
            setWinner(currentPlayer);
            isGameOngoing = false;
            return currentPlayer.getName() + " wins!";
        }

        if (withDiceRoll >= Board.BOARD_SIZE) {
            return currentPlayer.getName() + " is at " + currentPosition + " and cannot move.";
        }

        currentPosition = withDiceRoll;
        currentPlayer.setPosition(currentPosition);

        String message;

        if (board.getSnakes().containsKey(currentPosition)) {
            int snakeTail = board.getSnakes().get(currentPosition);
            currentPosition = snakeTail;
            currentPlayer.setPosition(snakeTail);
            message = currentPlayer.getName() + " got bitten by a snake at " + withDiceRoll + " and moved to "
                    + snakeTail;
        } else if (board.getLadders().containsKey(currentPosition)) {
            int ladderTop = board.getLadders().get(currentPosition);
            message = currentPlayer.getName() + " climbed a ladder from " + withDiceRoll + " to " + ladderTop;
            currentPosition = ladderTop;
            currentPlayer.setPosition(ladderTop);
        } else {
            message = currentPlayer.getName() + " is at " + currentPosition;
        }

        // Append player information to the player_info file
        updateInfoFile(currentPlayer, diceRoll, currentPosition, message);

        return message;
    }

    private void updateInfoFile(Player player, int move, int currentPosition, String message) {
        // Append player information to the player_info file
        try (FileWriter fileWriter = new FileWriter("player_info.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write("Player: " + player.getName());
            bufferedWriter.write(", dice roll: " + move);
            bufferedWriter.write(", Current Position: " + currentPosition);
            bufferedWriter.write(", Info: " + message);
            bufferedWriter.newLine(); // Add a new line for the next entry

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePlayerInfoToFile(String fileName, List<Player> players) {
        try (FileWriter fileWriter = new FileWriter(fileName, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            // started time
            bufferedWriter.write("Started at: " + LocalDateTime.now());
            bufferedWriter.newLine();
            for (Player player : players) {
                bufferedWriter.write("Player: " + player.getName());
                bufferedWriter.write(", Move: 0");
                bufferedWriter.write(", Current Position: 0");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get informayion from player_info.txt
    public List<String> getPlayerInfoFromFile(String fileName) {
        List<String> playerInfo = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                playerInfo.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return playerInfo;
    }

    private int getCurrentPositionFromInfoFile(String playerName) {
        List<String> playerInfo = getPlayerInfoFromFile("player_info.txt");
        int currentPosition = -1;

        for (int i = playerInfo.size() - 1; i >= 0; i--) {
            String info = playerInfo.get(i);
            if (info.startsWith("Player: " + playerName)) {
                String[] parts = info.split(", Current Position: ");
                if (parts.length == 2) {
                    String positionString = parts[1].split(",")[0].trim();
                    currentPosition = Integer.parseInt(positionString);
                    break;
                }
            }
        }

        return currentPosition;
    }

    private Player winningPlayer;

    public void setWinner(Player player) {
        this.winningPlayer = player;
        try (FileWriter fileWriter = new FileWriter("player_info.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("Winner: " + player.getName());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getWinner() {
        return winningPlayer;
    }

    public void setPlayers(List<Player> players) {
        this.players = players.toArray(new Player[players.size()]);
    }

}
