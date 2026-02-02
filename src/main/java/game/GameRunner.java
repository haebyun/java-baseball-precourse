package game;

public class GameRunner {
    private final GamingConsole nowRunningGame;
    private final GamePrinter nowRunningGamePrinter;

    public GameRunner(GamingConsole nowRunningGame, GamePrinter nowRunningGamePrinter) {
        this.nowRunningGame = nowRunningGame;
        this.nowRunningGamePrinter = nowRunningGamePrinter;
    }

    public void run() {
        nowRunningGamePrinter.printStartingMessage();
        nowRunningGame.play();
        nowRunningGamePrinter.printEndingMessage();
    }
}
