import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SPACE = " ";

    public String getWordFrequency(String sentence) {
        String[] words = sentence.split(SPACE_REGEX);
        if (words.length == 1) {
            return sentence + " 1";
        }
        try {
            return buildResult(words);
        } catch (Exception e) {
            return CALCULATE_ERROR + e.getMessage();
        }
    }

    private String buildResult(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(word -> word))
                .entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((word, nextWord) -> nextWord.getWordCount() - word.getWordCount())
                .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }
}
