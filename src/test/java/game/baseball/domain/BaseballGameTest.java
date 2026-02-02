package game.baseball.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BaseballGameTest {

    @Test
    void guessAllStrike() {
        BaseballGame game = new BaseballGame(BaseballNumbers.from(List.of(1, 2, 3)));

        Hint hint = game.guess(BaseballNumbers.from(List.of(1, 2, 3)));

        assertThat(hint.isSolved()).isTrue();
        assertThat(hint.message()).isEqualTo("3스트라이크");
    }

    @Test
    void guessStrikeAndBall() {
        BaseballGame game = new BaseballGame(BaseballNumbers.from(List.of(1, 2, 3)));

        Hint hint = game.guess(BaseballNumbers.from(List.of(1, 3, 2)));

        assertThat(hint.message()).isEqualTo("1스트라이크 2볼");
    }

    @Test
    void guessNothing() {
        BaseballGame game = new BaseballGame(BaseballNumbers.from(List.of(1, 2, 3)));

        Hint hint = game.guess(BaseballNumbers.from(List.of(4, 5, 6)));

        assertThat(hint.message()).isEqualTo("낫싱");
    }
}
