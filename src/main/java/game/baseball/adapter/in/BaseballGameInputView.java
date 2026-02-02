package game.baseball.adapter.in;

import game.baseball.application.port.out.GameInputPort;
import game.baseball.application.port.in.command.GuessCommand;
import game.baseball.application.port.in.command.RestartCommand;

import java.util.Scanner;

public class BaseballGameInputView implements GameInputPort {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public GuessCommand readGuessCommand() {
        return new GuessCommand(readLine());
    }

    @Override
    public RestartCommand readRestartCommand() {
        return new RestartCommand(readLine());
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
