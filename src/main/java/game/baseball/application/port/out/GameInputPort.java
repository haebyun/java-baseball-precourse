package game.baseball.application.port.out;

import game.baseball.application.port.in.command.GuessCommand;
import game.baseball.application.port.in.command.RestartCommand;

public interface GameInputPort {
    GuessCommand readGuessCommand();

    RestartCommand readRestartCommand();
}
