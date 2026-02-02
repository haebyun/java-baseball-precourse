package game.baseball.application;

import game.baseball.application.port.in.BaseballGameUseCase;
import game.baseball.application.port.in.command.GuessCommand;
import game.baseball.application.port.out.NumberGeneratorPort;
import game.baseball.domain.BaseballGame;
import game.baseball.domain.BaseballNumbers;
import game.baseball.domain.Hint;

public class BaseballGameService implements BaseballGameUseCase {
    private final NumberGeneratorPort numberGeneratorPort;
    private BaseballGame game;

    public BaseballGameService(NumberGeneratorPort numberGeneratorPort) {
        this.numberGeneratorPort = numberGeneratorPort;
    }

    @Override
    public void startNewGame() {
        BaseballNumbers answer = BaseballNumbers.from(numberGeneratorPort.generate());
        this.game = new BaseballGame(answer);
    }

    @Override
    public Hint guess(GuessCommand command) {
        ensureGameStarted();
        BaseballNumbers guess = BaseballNumbers.from(command.digits());
        return game.guess(guess);
    }

    private void ensureGameStarted() {
        if (game == null) {
            throw new IllegalStateException("게임이 시작되지 않았습니다.");
        }
    }
}
