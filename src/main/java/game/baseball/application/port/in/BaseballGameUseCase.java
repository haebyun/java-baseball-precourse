package game.baseball.application.port.in;

import game.baseball.application.port.in.command.GuessCommand;
import game.baseball.domain.Hint;

public interface BaseballGameUseCase {
    void startNewGame();

    Hint guess(GuessCommand command);
}
