package game.baseball.application;

import game.baseball.application.port.in.command.RestartCommand;

public class RestartCommandParser {
    public boolean parse(RestartCommand command) {
        String normalized = normalize(command.input());
        if ("1".equals(normalized)) {
            return true;
        }
        if ("2".equals(normalized)) {
            return false;
        }
        throw new IllegalArgumentException("1 또는 2를 입력해야 합니다.");
    }

    private String normalize(String userInput) {
        if (userInput == null) {
            throw new IllegalArgumentException("입력값이 null 입니다.");
        }
        return userInput.trim();
    }
}
