package game.baseball.adapter.in;

import game.GamingConsole;
import game.baseball.application.RestartCommandParser;
import game.baseball.application.port.in.BaseballGameUseCase;
import game.baseball.application.port.in.command.GuessCommand;
import game.baseball.application.port.in.command.RestartCommand;
import game.baseball.application.port.out.GameInputPort;
import game.baseball.application.port.out.GameOutputPort;
import game.baseball.domain.Hint;

public class BaseballGameController implements GamingConsole {
    private final BaseballGameUseCase useCase;
    private final GameInputPort inputPort;
    private final GameOutputPort outputPort;
    private final RestartCommandParser restartCommandParser;

    public BaseballGameController(
            BaseballGameUseCase useCase,
            GameInputPort inputPort,
            GameOutputPort outputPort,
            RestartCommandParser restartCommandParser
    ) {
        this.useCase = useCase;
        this.inputPort = inputPort;
        this.outputPort = outputPort;
        this.restartCommandParser = restartCommandParser;
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
        while (true) {
            Hint hint = tryGuessOnce();
            if (hint != null) {
                return hint;
            }
        }
    }

    private Hint tryGuessOnce() {
        try {
            outputPort.showGuessPrompt();
            GuessCommand command = inputPort.readGuessCommand();
            return useCase.guess(command);
        } catch (IllegalArgumentException e) {
            outputPort.showError(e.getMessage());
            return null;
        }
    }

    private boolean restartSelected() {
        while (true) {
            Boolean restart = tryReadRestartCommand();
            if (restart != null) {
                return restart;
            }
        }
    }

    private Boolean tryReadRestartCommand() {
        try {
            outputPort.showRestartPrompt();
            RestartCommand command = inputPort.readRestartCommand();
            return restartCommandParser.parse(command);
        } catch (IllegalArgumentException e) {
            outputPort.showError(e.getMessage());
            return null;
        }
    }
}
