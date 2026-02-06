package game.baseball.adapter.in;

import game.GamingConsole;
import game.baseball.application.port.in.BaseballGameUseCase;
import game.baseball.application.port.out.GameInputPort;
import game.baseball.application.port.out.GameOutputPort;
import game.baseball.domain.Hint;

import java.util.function.Supplier;

public class BaseballGameController implements GamingConsole {
    private final BaseballGameUseCase useCase;
    private final GameInputPort inputPort;
    private final GameOutputPort outputPort;

    public BaseballGameController(
            BaseballGameUseCase useCase,
            GameInputPort inputPort,
            GameOutputPort outputPort
    ) {
        this.useCase = useCase;
        this.inputPort = inputPort;
        this.outputPort = outputPort;
    }

    @Override
    public void play() {
        while (true) {
            useCase.startNewGame();
            playUntilSolved();
            if (!restartSelected()) {
                return;
            }
        }
    }

    private void playUntilSolved() {
        while (true) {
            Hint hint = readValidHint();
            outputPort.showResult(hint);
            if (hint.isSolved()) {
                outputPort.showGameEnd();
                return;
            }
        }
    }

    private Hint readValidHint() {
        return readUntilValid(() -> {
            outputPort.showGuessPrompt();
            return useCase.guess(inputPort.readGuessCommand());
        });
    }

    private boolean restartSelected() {
        return readUntilValid(() -> {
            outputPort.showRestartPrompt();
            return inputPort.readRestartCommand().restart();
        });
    }

    private <T> T readUntilValid(Supplier<T> reader) {
        while (true) {
            try {
                return reader.get();
            } catch (IllegalArgumentException e) {
                outputPort.showError(e.getMessage());
            }
        }
    }
}
