package game.baseball.domain;

public class BaseballNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    private final Integer number;

    private BaseballNumber(Integer number) {
        this.number = number;
    }

    public static BaseballNumber of(Integer number) {
        validate(number);
        return new BaseballNumber(number);
    }

    private static void validate(final Integer number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("야구 숫자의 범위는 %d에서 %d까지의 자연수 입니다.", MIN_NUMBER, MAX_NUMBER)
            );
        }
    }

    public int value() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseballNumber val)) {
            return false;
        }
        return number.equals(val.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

}
