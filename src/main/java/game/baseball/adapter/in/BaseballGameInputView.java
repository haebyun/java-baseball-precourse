package game.baseball.adapter.in;

import game.baseball.BaseballGameRules;
import game.baseball.application.port.out.GameInputPort;
import game.baseball.application.port.in.command.GuessCommand;
import game.baseball.application.port.in.command.RestartCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseballGameInputView implements GameInputPort {
    private static final char DIGIT_ZERO = '0';

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public GuessCommand readGuessCommand() {
        return new GuessCommand(parseGuess(readLine()));
    }

    @Override
    public RestartCommand readRestartCommand() {
        return new RestartCommand(parseRestart(readLine()));
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private List<Integer> parseGuess(String userInput) {
        String input = normalize(userInput);
        validateLength(input);
        return toDigits(input);
    }

    private String normalize(String userInput) {
        if (userInput == null) {
            throw new IllegalArgumentException("입력값이 null 입니다.");
        }
        return userInput.trim();
    }

    private void validateLength(String input) {
        if (input.length() != BaseballGameRules.NUMBER_COUNT) {
            throw new IllegalArgumentException(
                    String.format("%d자리 숫자를 입력해야 합니다.", BaseballGameRules.NUMBER_COUNT)
            );
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
        return ch - DIGIT_ZERO;
    }

    private boolean parseRestart(String command) {
        String normalized = normalize(command);
        if (BaseballGameRules.RESTART_COMMAND.equals(normalized)) {
            return true;
        }
        if (BaseballGameRules.QUIT_COMMAND.equals(normalized)) {
            return false;
        }
        throw new IllegalArgumentException(
                String.format(
                        "%s 또는 %s를 입력해야 합니다.",
                        BaseballGameRules.RESTART_COMMAND,
                        BaseballGameRules.QUIT_COMMAND
                )
        );
    }
}
