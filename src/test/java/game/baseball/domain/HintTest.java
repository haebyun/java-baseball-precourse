package game.baseball.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HintTest {

    @Test
    void messageWhenNothing() {
        Hint hint = Hint.of(0, 0);

        assertThat(hint.message()).isEqualTo("낫싱");
    }

    @Test
    void messageWhenStrikeAndBall() {
        Hint hint = Hint.of(1, 2);

        assertThat(hint.message()).isEqualTo("1스트라이크 2볼");
    }

    @Test
    void messageWhenOnlyBall() {
        Hint hint = Hint.of(0, 2);

        assertThat(hint.message()).isEqualTo("2볼");
    }

    @Test
    void solvedWhenThreeStrike() {
        Hint hint = Hint.of(3, 0);

        assertThat(hint.isSolved()).isTrue();
    }

    @Test
    void rejectInvalidStrikeRange() {
        assertThatThrownBy(() -> Hint.of(4, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectInvalidBallRange() {
        assertThatThrownBy(() -> Hint.of(0, 4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectSumGreaterThanThree() {
        assertThatThrownBy(() -> Hint.of(2, 2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
