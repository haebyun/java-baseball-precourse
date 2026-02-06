package game.baseball.domain;

import game.baseball.BaseballGameRules;

public class Hint {
    private static final int MIN_COUNT = 0;
    private static final int MAX_COUNT = BaseballGameRules.NUMBER_COUNT;
    private static final int SOLVED_STRIKE_COUNT = BaseballGameRules.NUMBER_COUNT;

    private final int strike;
    private final int ball;

    private Hint(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public static Hint of(int strike, int ball) {
        validate(strike, ball);
        return new Hint(strike, ball);
    }

    public boolean isSolved() {
        return strike == SOLVED_STRIKE_COUNT;
    }

    public String message() {
        if (isNothing()) {
            return "낫싱";
        }
        return buildMessage();
    }

    private boolean isNothing() {
        return strike == MIN_COUNT && ball == MIN_COUNT;
    }

    private String buildMessage() {
        StringBuilder sb = new StringBuilder();
        appendStrike(sb);
        appendBall(sb);
        return sb.toString();
    }

    private void appendStrike(StringBuilder sb) {
        if (strike == MIN_COUNT) {
            return;
        }
        sb.append(strike).append("스트라이크");
    }

    private void appendBall(StringBuilder sb) {
        if (ball == MIN_COUNT) {
            return;
        }
        if (!sb.isEmpty()) {
            sb.append(" ");
        }
        sb.append(ball).append("볼");
    }

    private static void validate(int strike, int ball) {
        if (strike < MIN_COUNT || strike > MAX_COUNT) {
            throw new IllegalArgumentException(
                    String.format("스트라이크는 %d~%d 범위여야 합니다.", MIN_COUNT, MAX_COUNT)
            );
        }
        if (ball < MIN_COUNT || ball > MAX_COUNT) {
            throw new IllegalArgumentException(
                    String.format("볼은 %d~%d 범위여야 합니다.", MIN_COUNT, MAX_COUNT)
            );
        }
        if (strike + ball > MAX_COUNT) {
            throw new IllegalArgumentException(
                    String.format("스트라이크와 볼의 합은 %d을 초과할 수 없습니다.", MAX_COUNT)
            );
        }
    }
}
