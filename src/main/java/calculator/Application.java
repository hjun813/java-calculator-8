package calculator;

import camp.nextstep.edu.missionutils.Console; // 입력
import java.util.*;


public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        calculator calculator = new calculator();
        try {
            int result = calculator.add(input);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("오류");
        }
    }
}
