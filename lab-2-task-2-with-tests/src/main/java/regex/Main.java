package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String sentence = "I love kebab very much";  // Ваше речення
        System.out.println(removeSpacesUsingRegex(sentence));
    }

    public static String removeSpacesUsingRegex(String sentence) {

        String cleanedSentence = sentence.replaceAll("\\s+", " ");


        Pattern pattern = Pattern.compile("^\\S+\\s(\\S+)\\s(.*)\\s(\\S+)$");
        Matcher matcher = pattern.matcher(cleanedSentence);

        if (matcher.matches()) {
            String firstWord = cleanedSentence.split("\\s+")[0];
            String secondWord = matcher.group(1);
            String middleWords = matcher.group(2).replaceAll("\\s", ""); // Середні слова без пробілів
            String lastWord = matcher.group(3);


            return firstWord + " " + secondWord + middleWords + " " + lastWord;
        }

        return sentence;
    }
}