package game.baseball.application.port.in.command;

import java.util.List;

public record GuessCommand(List<Integer> digits) {
    public GuessCommand {
        digits = List.copyOf(digits);
    }
}
