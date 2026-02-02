package game.baseball.adapter.out;

import game.GamePrinter;

public class BaseballPrinter implements GamePrinter {
    private static final String START_MESSAGE = "숫자 야구 게임을 시작합니다.";
    private static final String END_MESSAGE = "게임을 종료합니다.";

    @Override
    public void printStartingMessage() {
        System.out.println(START_MESSAGE);
    }

    @Override
    public void printEndingMessage() {
        System.out.println(END_MESSAGE);
    }
}
