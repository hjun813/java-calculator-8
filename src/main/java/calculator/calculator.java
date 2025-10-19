package calculator;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class calculator {

    public int add(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String delimiter = "[,:]";
        String numbersText = text;

        if (text.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.)\\\\n(.*)").matcher(text);
            if (matcher.find()) {
                delimiter = Pattern.quote(matcher.group(1));
                numbersText = matcher.group(2);
            }
        }

        
        return splitToNumbers(numbersText, delimiter)
                .mapToInt(this::parseAndValidate)
                .sum();
    }

    
    private int parseAndValidate(String numStr) {
        int num = Integer.parseInt(numStr);
        if (num < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
        return num;
    }

    
    private Stream<String> splitToNumbers(String text, String delimiter) {
        if (text.isEmpty()) {
            return Stream.empty();
        }
        return Arrays.stream(text.split(delimiter));
    }
}