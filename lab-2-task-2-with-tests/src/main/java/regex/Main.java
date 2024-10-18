package regex;

import regex.Services.FileReaderService;
import regex.Services.NestedParenthesesChecker;
import regex.Services.SentenceExtractor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String filePath = "text.txt";

        FileReaderService fileReaderService = new FileReaderService();
        SentenceExtractor sentenceExtractor = new SentenceExtractor();
        NestedParenthesesChecker checker = new NestedParenthesesChecker();

        try {
            String text = fileReaderService.readFile(filePath);
            String[] sentences = sentenceExtractor.extractSentences(text);

            for (String sentence : sentences) {
                if (checker.hasNestedParentheses(sentence)) {
                    System.out.println(sentence);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}
