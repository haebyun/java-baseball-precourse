import game.GameRunner;
import game.baseball.config.BaseballGameConfig;

public class Application {
    public static void main(String[] args) {
        GameRunner gameRunner = new BaseballGameConfig().gameRunner();
        gameRunner.run();
    }
}
