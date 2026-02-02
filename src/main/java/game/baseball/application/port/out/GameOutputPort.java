package game.baseball.application.port.out;

import game.baseball.domain.Hint;

public interface GameOutputPort {
    void showGuessPrompt();

    void showResult(Hint hint);

    void showGameEnd();

    void showRestartPrompt();

    void showError(String message);
}
