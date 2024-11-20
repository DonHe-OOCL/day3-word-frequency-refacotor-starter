public class WordFrequency {
    private String word;
    private int count;

    public WordFrequency(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
