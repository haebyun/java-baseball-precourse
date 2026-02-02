package game.baseball.application.port.in.command;

public class GuessCommand {
    private final String input;

    public GuessCommand(String input) {
        this.input = input;
    }

    public String input() {
        return input;
    }
}
