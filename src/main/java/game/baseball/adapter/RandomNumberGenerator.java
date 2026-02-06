package game.baseball.adapter;

import game.baseball.BaseballGameRules;
import game.baseball.application.port.out.NumberGeneratorPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator implements NumberGeneratorPort {
    private static final int RANDOM_RANGE = BaseballGameRules.MAX_NUMBER - BaseballGameRules.MIN_NUMBER + 1;

    private final Random random = new Random();

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < BaseballGameRules.NUMBER_COUNT) {
            int candidate = random.nextInt(RANDOM_RANGE) + BaseballGameRules.MIN_NUMBER;
            addIfAbsent(numbers, candidate);
        }
        return numbers;
    }

    private void addIfAbsent(List<Integer> numbers, int candidate) {
        if (numbers.contains(candidate)) {
            return;
        }
        numbers.add(candidate);
    }
}
