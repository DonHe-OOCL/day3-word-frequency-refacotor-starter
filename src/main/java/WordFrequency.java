public class WordFrequency {
    private String word;
    private int count;

    public WordFrequency(String word, int count) {
        this.word = word;
        this.count = count;
    }

    // todo add setter
    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }
}
