package game.baseball.application.port.in.command;

public class RestartCommand {
    private final String input;

    public RestartCommand(String input) {
        this.input = input;
    }

    public String input() {
        return input;
    }
}
