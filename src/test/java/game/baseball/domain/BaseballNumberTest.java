package game.baseball.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseballNumberTest {

    @Test
    void createWithValidRange() {
        BaseballNumber number = BaseballNumber.of(1);

        assertThat(number.value()).isEqualTo(1);
    }

    @Test
    void rejectNumberLessThanOne() {
        assertThatThrownBy(() -> BaseballNumber.of(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectNumberGreaterThanNine() {
        assertThatThrownBy(() -> BaseballNumber.of(10))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
