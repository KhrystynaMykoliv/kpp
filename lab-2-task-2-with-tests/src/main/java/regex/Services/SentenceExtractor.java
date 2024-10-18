package regex.Services;

public class SentenceExtractor {
    public String[] extractSentences(String text) {
        return text.split("(?<=\\.)");
    }
}
