package main;

import java.util.*;
public class RepetitionSpamFilter implements SpamFilter {
    private final int maxRepetitions;

    public RepetitionSpamFilter(int maxRepetitions) {
        if (maxRepetitions <= 0) {
            throw new IllegalArgumentException("Amount of repetition can not be below or equal zero");
        }
        this.maxRepetitions = maxRepetitions;
    }

    @Override
    public boolean isSpam(Message message) {
        String[] words = message.getText().toLowerCase().split("[^\\p{L}\\p{N}]+");
        Map<String, Integer> wordCounts = new HashMap<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }
        return wordCounts.values().stream().anyMatch(count -> count > maxRepetitions);
    }
}
