package game.baseball.domain;

public class Hint {
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
        return strike == 3;
    }

    public String message() {
        if (isNothing()) {
            return "낫싱";
        }
        return buildMessage();
    }

    private boolean isNothing() {
        return strike == 0 && ball == 0;
    }

    private String buildMessage() {
        StringBuilder sb = new StringBuilder();
        appendStrike(sb);
        appendBall(sb);
        return sb.toString();
    }

    private void appendStrike(StringBuilder sb) {
        if (strike == 0) {
            return;
        }
        sb.append(strike).append("스트라이크");
    }

    private void appendBall(StringBuilder sb) {
        if (ball == 0) {
            return;
        }
        if (!sb.isEmpty()) {
            sb.append(" ");
        }
        sb.append(ball).append("볼");
    }

    private static void validate(int strike, int ball) {
        if (strike < 0 || strike > 3) {
            throw new IllegalArgumentException("스트라이크는 0~3 범위여야 합니다.");
        }
        if (ball < 0 || ball > 3) {
            throw new IllegalArgumentException("볼은 0~3 범위여야 합니다.");
        }
        if (strike + ball > 3) {
            throw new IllegalArgumentException("스트라이크와 볼의 합은 3을 초과할 수 없습니다.");
        }
    }
}
