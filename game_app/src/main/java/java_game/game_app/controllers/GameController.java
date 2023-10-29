package java_game.game_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java_game.game_app.Game.Game;
import java_game.game_app.models.Player;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private final Game game;

    @Autowired
    public GameController(Game game) {
        this.game = game;
    }

    @PostMapping("/players")
    public ResponseEntity<String> createPlayers(@RequestParam List<String> names) {
        // List<Player> players = game.createPlayers(names);
        game.createPlayers(names);
        return ResponseEntity.ok("Players created.");
    }

    @PutMapping("/move")
    public ResponseEntity<String> makeMove(@RequestParam String playerName) {
        String result = game.makeMove(playerName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/winnerInfo")
    public ResponseEntity<String> getWinnerInfo() {
        Player winner = game.getWinner();
        if (winner != null) {
            return ResponseEntity.ok("Winner: " + winner.getName());
        } else {
            return ResponseEntity.ok("No winner yet");
        }
    }

}
