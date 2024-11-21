import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SPACE = " ";

    public String getWordFrequency(String sentence) {
        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        }
        try {
            return buildResult(sentence);
        } catch (Exception e) {
            return CALCULATE_ERROR + e.getMessage();
        }
    }

    private String buildResult(String sentence) {
        //split the input string with 1 to n pieces of spaces
        return Arrays.stream(sentence.split(SPACE_REGEX))
                .collect(Collectors.groupingBy(word -> word))
                .entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((word, nextWord) -> nextWord.getWordCount() - word.getWordCount())
                .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }
}
