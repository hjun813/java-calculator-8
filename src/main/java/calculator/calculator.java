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
                delimiter = Pattern.quote(matcher.group(1)); // 특수문자 구분자도 처리
                numbersText = matcher.group(2);
            }
        }

        // 문자열이 비어있으면 0을 반환하도록 스트림을 수정
        return splitToNumbers(numbersText, delimiter)
                .mapToInt(this::parseAndValidate)
                .sum();
    }

    // 문자열을 숫자로 변환하고 음수인지 검증하는 헬퍼 메서드
    private int parseAndValidate(String numStr) {
        int num = Integer.parseInt(numStr);
        if (num < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
        return num;
    }

    // 구분자로 자르고 스트림을 반환하는 헬퍼 메서드
    private Stream<String> splitToNumbers(String text, String delimiter) {
        if (text.isEmpty()) {
            return Stream.empty();
        }
        return Arrays.stream(text.split(delimiter));
    }
}