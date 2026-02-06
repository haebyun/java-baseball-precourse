package game.baseball.domain;

import game.baseball.BaseballGameRules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseballNumbers {
    private final List<BaseballNumber> numbers;

    private BaseballNumbers(List<BaseballNumber> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public static BaseballNumbers from(final List<Integer> numbers) {
        validate(numbers);
        return new BaseballNumbers(createBaseballNumbers(numbers));
    }

    private static void validate(final List<Integer> numbers) {
        validateNotNull(numbers);
        validateSize(numbers);
        validateDistinct(numbers);
    }

    private static void validateNotNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("숫자 목록이 null 입니다.");
        }
    }

    private static void validateSize(final List<Integer> numbers) {
        if (numbers.size() != BaseballGameRules.NUMBER_COUNT) {
            throw new IllegalArgumentException(
                    String.format("숫자야구의 숫자 개수는 %d개입니다.", BaseballGameRules.NUMBER_COUNT)
            );
        }
    }

    private static void validateDistinct(final List<Integer> numbers) {
        Set<Integer> unique = new HashSet<>();
        for (Integer number : numbers) {
            if (unique.contains(number)) {
                throw new IllegalArgumentException("숫자는 서로 중복될 수 없습니다.");
            }
            unique.add(number);
        }
    }

    private static List<BaseballNumber> createBaseballNumbers(List<Integer> numbers) {
        List<BaseballNumber> baseballNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            baseballNumbers.add(BaseballNumber.of(number));
        }
        return baseballNumbers;
    }

    public int countStrike(BaseballNumbers guess) {
        int strike = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).equals(guess.numbers.get(i))) {
                strike++;
            }
        }
        return strike;
    }

    public int countBall(BaseballNumbers guess) {
        int ball = 0;
        for (int i = 0; i < numbers.size(); i++) {
            BaseballNumber candidate = guess.numbers.get(i);
            if (isStrikePosition(candidate, i)) {
                continue;
            }
            if (numbers.contains(candidate)) {
                ball++;
            }
        }
        return ball;
    }

    private boolean isStrikePosition(BaseballNumber candidate, int index) {
        return numbers.get(index).equals(candidate);
    }
}
