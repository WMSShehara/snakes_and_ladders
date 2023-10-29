package java_game.game_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "java_game.game_app" })
public class GameAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameAppApplication.class, args);
	}

}
