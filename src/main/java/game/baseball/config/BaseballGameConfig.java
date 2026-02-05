package game.baseball.config;

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

public class BaseballGameConfig {
    public GameRunner gameRunner() {
        NumberGeneratorPort numberGeneratorPort = new RandomNumberGenerator();
        GameInputPort inputPort = new BaseballGameInputView();
        GameOutputPort outputPort = new BaseballGameOutputView();
        BaseballGameUseCase useCase = new BaseballGameService(numberGeneratorPort);
        GamingConsole console = new BaseballGameController(
                useCase,
                inputPort,
                outputPort
        );
        GamePrinter printer = new BaseballPrinter();
        return new GameRunner(console, printer);
    }
}
