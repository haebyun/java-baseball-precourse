package game.baseball.adapter;

import game.baseball.application.port.out.NumberGeneratorPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator implements NumberGeneratorPort {
    private final Random random = new Random();

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < 3) {
            int candidate = random.nextInt(9) + 1;
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

