package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class calculator {

    public int add(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        if (text.contains("\\n")) {
            text = text.replace("\\n", "\n");
        }

        String delimiter = "[,:]";
        String numbersText = text;

        if (text.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.)\\n(.*)").matcher(text);
            if (matcher.find()) {
                delimiter = Pattern.quote(matcher.group(1));
                numbersText = matcher.group(2);
            }
        }

        return Stream.of(numbersText.split(delimiter))
            .map(String::trim)
            .mapToInt(num -> {
                int n;
                try {
                    n = Integer.parseInt(num);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("잘못된 숫자 형식: " + num);
                }
                if (n < 0) {
                    throw new IllegalArgumentException("음수는 허용되지 않습니다: " + n);
                }
                return n;
            })
            .sum();
    }
}
