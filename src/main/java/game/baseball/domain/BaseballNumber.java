package game.baseball.domain;

public class BaseballNumber {
    private final Integer number;

    private BaseballNumber(Integer number) {
        this.number = number;
    }

    public static BaseballNumber of(Integer number) {
        validate(number);
        return new BaseballNumber(number);
    }

    private static void validate(final Integer number) {
        if(number < 1 || number > 9) {
            throw new IllegalArgumentException("야구 숫자의 범위는 1에서 9까지의 자연수 입니다.");
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
