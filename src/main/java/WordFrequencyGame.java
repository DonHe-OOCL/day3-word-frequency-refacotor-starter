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
            List<WordFrequency> wordFrequencies = getInitialWordFrequencies(sentence);
            //get the wordToWordFrequencies for the next step of sizing the same word
            wordFrequencies = getWordFrequencies(wordFrequencies);
            return buildResult(wordFrequencies);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }

    }

    private String buildResult(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getWordCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> getWordFrequencies(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream().collect(Collectors.groupingBy(WordFrequency::getWord))
                .entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                .sorted((word, nextWord) -> nextWord.getWordCount() - word.getWordCount())
                .toList();
    }

    private List<WordFrequency> getInitialWordFrequencies(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(SPACE_REGEX);
        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .toList();
    }
}
