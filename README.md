Snake and Ladder Game
JAVA | SPRINGBOOT

Introduction:

The Snake and Ladder Game is an implementation of the popular board game. In this game, players roll a dice to move along a game board filled with snakes and ladders, with the goal of reaching the final (100th) square first. Here you can access the game through only API endpoints.
Game Rules:

•	The game is played with two or more players.

•	Players take turns to roll a dice and move their pieces along the game board.

•	The board contains snakes and ladders that can either help or hinder a player's progress.

•	The player who reaches the final square first wins the game.

How to Play:

1.	Open the project in your preferred Spring Boot-compatible IDE. Run the src/main/java/java_game/game_app/GameAppApplication

2.	Create the game with desired number of players with their names by making POST request in Thunder client or any other service.
http://localhost:8080/game/players?names={player1name}&names=={player2name}

3.	Play game by making PUT request. By changing player name desired number of players can play. Roll the dice and move your piece along the board according to the dice roll. Follow the rules of the game to determine the outcome, such as climbing ladders or getting bitten by snakes. The game ends when a player reaches the final square. That player wins the game.
http://localhost:8080/game/move?playerName={Player1Name}

4.	You can get winner info by making GET request.
http://localhost:8080/game/winnerInfo

5.	You can get game records fron player_info.txt file.

