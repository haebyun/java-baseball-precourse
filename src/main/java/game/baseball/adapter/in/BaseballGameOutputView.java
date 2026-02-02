package game.baseball.adapter.in;

import game.baseball.application.port.out.GameOutputPort;
import game.baseball.domain.Hint;

public class BaseballGameOutputView implements GameOutputPort {
    private static final String NUMBER_INPUT_PROMPT = "숫자를 입력해주세요 : ";
    private static final String CORRECT_MESSAGE = "3개의 숫자를 모두 맞히셨습니다. 게임 종료";
    private static final String OPTION_INPUT_PROMPT = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

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
