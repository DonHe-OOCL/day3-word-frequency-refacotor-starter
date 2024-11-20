public class Input {
    // todo rename filed
    private String value;
    private int count;

    // todo rename args name
    public Input(String w, int i) {
        this.value = w;
        this.count = i;
    }

    // todo add setter
    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.count;
    }
}
