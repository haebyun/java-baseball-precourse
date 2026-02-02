package game.baseball.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseballNumbersTest {

    @Test
    void createWithValidNumbers() {
        BaseballNumbers numbers = BaseballNumbers.from(List.of(1, 2, 3));

        assertThat(numbers.countStrike(BaseballNumbers.from(List.of(1, 2, 3)))).isEqualTo(3);
    }

    @Test
    void rejectNullList() {
        assertThatThrownBy(() -> BaseballNumbers.from(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectWrongSize() {
        assertThatThrownBy(() -> BaseballNumbers.from(List.of(1, 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectDuplicateNumbers() {
        assertThatThrownBy(() -> BaseballNumbers.from(List.of(1, 1, 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void countStrikeAndBall() {
        BaseballNumbers answer = BaseballNumbers.from(List.of(1, 2, 3));
        BaseballNumbers guess = BaseballNumbers.from(List.of(1, 3, 2));

        assertThat(answer.countStrike(guess)).isEqualTo(1);
        assertThat(answer.countBall(guess)).isEqualTo(2);
    }
}
