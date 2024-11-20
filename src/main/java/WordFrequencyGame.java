import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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

                List<WordFrequency> wordFrequencies = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencies.add(wordFrequency);
                }

                //get the wordToWordFrequencies for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordToWordFrequencies = getWordFrequencyMap(wordFrequencies);

                List<WordFrequency> tempList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordToWordFrequencies.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    tempList.add(wordFrequency);
                }
                wordFrequencies = tempList;

                wordFrequencies.sort((word, nextWord) -> nextWord.getWordCount() - word.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                for (WordFrequency wordFrequency : wordFrequencies) {
                    String frequency = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    joiner.add(frequency);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    // todo use stream api to replace the for loop
    // refactor this method
    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordFrequencyMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       wordFrequencyMap.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordFrequencyMap.containsKey(wordFrequency.getWord())) {
                ArrayList tempList = new ArrayList<>();
                tempList.add(wordFrequency);
                wordFrequencyMap.put(wordFrequency.getWord(), tempList);
            } else {
                wordFrequencyMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordFrequencyMap;
    }
}
