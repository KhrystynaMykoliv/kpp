package regex.Services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NestedParenthesesChecker {
    public boolean hasNestedParentheses(String sentence) {
        Pattern pattern = Pattern.compile("\\(([^()]*\\([^()]+\\)[^()]*)\\)");
        Matcher matcher = pattern.matcher(sentence);
        return matcher.find();
    }
}
