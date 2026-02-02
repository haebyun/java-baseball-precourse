package game.baseball.application;

import game.baseball.application.port.in.command.GuessCommand;
import game.baseball.domain.BaseballNumbers;

import java.util.ArrayList;
import java.util.List;

public class GuessCommandParser {
    public BaseballNumbers parse(GuessCommand command) {
        String input = normalize(command.input());
        validateLength(input);
        return BaseballNumbers.from(toDigits(input));
    }

    private String normalize(String userInput) {
        if (userInput == null) {
            throw new IllegalArgumentException("입력값이 null 입니다.");
        }
        return userInput.trim();
    }

    private void validateLength(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException("세 자리 숫자를 입력해야 합니다.");
        }
    }

    private List<Integer> toDigits(String input) {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            digits.add(parseDigit(input.charAt(i)));
        }
        return digits;
    }

    private int parseDigit(char ch) {
        if (!Character.isDigit(ch)) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
        return ch - '0';
    }
}
