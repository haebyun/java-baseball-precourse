package game.baseball.domain;

public class BaseballGame {
    private final BaseballNumbers answer;

    public BaseballGame(BaseballNumbers answer) {
        this.answer = answer;
    }

    public Hint guess(BaseballNumbers guess) {
        int strike = answer.countStrike(guess);
        int ball = answer.countBall(guess);
        return Hint.of(strike, ball);
    }
}
