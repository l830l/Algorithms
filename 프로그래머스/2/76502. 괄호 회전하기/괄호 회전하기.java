import java.util.*;

class Solution {
    // 2. 괄호 수를 기록할 변수를 선언한다.
    int parenthesesCount = 0;

    public int solution(String s) {
        // 1. 예외처리
        int length = s.length();
        if(!isSimpleValidString(s, length)) return 0;

        // 4. 회전 수를 기록한 변수를 선언한다.
        int rotateCount = 0;
        StringBuilder sb = new StringBuilder(s);

        // 3. 올바른 문자열인지 체크한다.
        // 문자열이 바르게 될때까지 문자열을 회전시킨다.
        while(!isValidString(s, length) && rotateCount < length){
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
            s = sb.toString();
            rotateCount++;

            System.out.println("문자열 : "+s);
        }

        return parenthesesCount;
    }

    // 0. 예외처리
    // 해당 문자열이 2보다 작거나 짝수가 아니거나, 여는 괄호와 닫는 괄호의 갯수가 같지 않으면 해당 문자열은 많이 회전해도 올바른 괄호가 될 수 없으므로 바로 0을 리턴한다.
    private boolean isSimpleValidString(String s, int length){
        int check = 0;
        if(length < 2 || length % 2 != 0) return false;
        for(int i = 0; i < length; i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                check++;
            } else {
                check--;
            }
        }
        return check == 0;
    }

    // 해당 문자열이 올바른 괄호 문자열인지 제대로 체크한다.
    private boolean isValidString(String s, int length){
        StringBuilder sb = new StringBuilder();

        // 3-3 문자열의 길이만큼 반복하며 해당 문자에 따라 배열을 조작한다.
        for(int i = 0; i < length; i++) {
            char ch = s.charAt(i);

            switch (ch){
                case '(' -> sb.append(')');
                case '[' -> sb.append(']');
                case '{' -> sb.append('}');
                default -> {
                    if(sb.length() == 0) {
                        parenthesesCount = 0;
                        return false;
                    };
                    char lastChar = sb.charAt(sb.length() - 1);
                    if(ch != lastChar) {
                        parenthesesCount = 0;
                        return false;
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    if(sb.length() == 0){
                        parenthesesCount++;
                    }
                    if(sb.length() != 0){
                        lastChar = sb.charAt(sb.length() - 1);
                        System.out.println(lastChar);
                        if(lastChar == '(' || lastChar == '[' || lastChar == '{') parenthesesCount++;
                        System.out.println(parenthesesCount);
                    }
                }
            }

        }

        return sb.length() == 0;
    }
}