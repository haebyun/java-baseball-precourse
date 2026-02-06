package game.baseball.adapter.in;

import game.baseball.BaseballGameRules;
import game.baseball.application.port.out.GameOutputPort;
import game.baseball.domain.Hint;

public class BaseballGameOutputView implements GameOutputPort {
    private static final String NUMBER_INPUT_PROMPT = "숫자를 입력해주세요 : ";
    private static final String CORRECT_MESSAGE =
            String.format("%d개의 숫자를 모두 맞히셨습니다. 게임 종료", BaseballGameRules.NUMBER_COUNT);
    private static final String OPTION_INPUT_PROMPT =
            String.format(
                    "게임을 새로 시작하려면 %s, 종료하려면 %s를 입력하세요.",
                    BaseballGameRules.RESTART_COMMAND,
                    BaseballGameRules.QUIT_COMMAND
            );

    @Override
    public void showGuessPrompt() {
        System.out.print(NUMBER_INPUT_PROMPT);
    }

    @Override
    public void showResult(Hint hint) {
        System.out.println(hint.message());
    }

    @Override
    public void showGameEnd() {
        System.out.println(CORRECT_MESSAGE);
    }

    @Override
    public void showRestartPrompt() {
        System.out.println(OPTION_INPUT_PROMPT);
    }

    @Override
    public void showError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
