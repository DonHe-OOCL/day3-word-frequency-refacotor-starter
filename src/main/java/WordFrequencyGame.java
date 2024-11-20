import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyGame {

    public static final String SPACE = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getWordFrequency(String sentence) {
        // TODO if else 块
        if (sentence.split(SPACE).length == 1) {
            return sentence + " 1";
        } else {
            // todo 抽出多个方法
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(SPACE);
                List<WordFrequency> wordFrequencies = Arrays.stream(words)
                        .map(word -> new WordFrequency(word, 1))
                        .toList();

                //get the wordToWordFrequencies for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencies = getWordFrequencyMap(wordFrequencies);

                List<WordFrequency> tempList = wordToWordFrequencies.entrySet().stream()
                        .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().size()))
                        .sorted((word, nextWord) -> nextWord.getWordCount() - word.getWordCount())
                        .collect(Collectors.toList());
                wordFrequencies = tempList;

                return wordFrequencies.stream()
                        .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getWordCount())
                        .collect(Collectors.joining(LINE_BREAK));
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    // refactor this method
    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream().collect(Collectors.groupingBy(WordFrequency::getWord));
    }
}
