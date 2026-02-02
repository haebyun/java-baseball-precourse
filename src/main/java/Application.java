import game.GamePrinter;
import game.GameRunner;
import game.GamingConsole;
import game.baseball.adapter.RandomNumberGenerator;
import game.baseball.adapter.in.BaseballGameController;
import game.baseball.adapter.in.BaseballGameInputView;
import game.baseball.adapter.in.BaseballGameOutputView;
import game.baseball.adapter.out.BaseballPrinter;
import game.baseball.application.BaseballGameService;
import game.baseball.application.port.in.BaseballGameUseCase;
import game.baseball.application.port.out.GameInputPort;
import game.baseball.application.port.out.GameOutputPort;
import game.baseball.application.port.out.NumberGeneratorPort;

public class Application {
    public static void main(String[] args) {
        NumberGeneratorPort numberGeneratorPort = new RandomNumberGenerator();
        GameInputPort inputPort = new BaseballGameInputView();
        GamingConsole console = getGamingConsole(numberGeneratorPort, inputPort);
        GamePrinter printer = new BaseballPrinter();
        GameRunner gameRunner = new GameRunner(
                console,
                printer
        );
        gameRunner.run();
    }

    private static GamingConsole getGamingConsole(NumberGeneratorPort numberGeneratorPort, GameInputPort inputPort) {
        GameOutputPort outputPort = new BaseballGameOutputView();
        BaseballGameUseCase useCase = new BaseballGameService(numberGeneratorPort);
        return new BaseballGameController(
                useCase,
                inputPort,
                outputPort
        );
    }
}
