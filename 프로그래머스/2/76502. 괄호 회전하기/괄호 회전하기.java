class Solution {
    int parenthesesCount = 0;

    public int solution(String s) {
        int length = s.length();

        // 1. 예외처리
        if(!isSimpleValidString(s, length)) return 0;

        int rotateCount = 0;
        StringBuilder sb = new StringBuilder(s);

        // 2. 주어진 문자열이 올바른 괄호 문자인지 확인한다.
        // 4. 문자열 길이만큼 회전 시켰는데도 올바르지 않은 문자열이 계속되면, 0을 리턴한다.
        while(!isValidString(s, length) && rotateCount < length){
            // 3. 올바르지 않은 문자열일 경우 회전시킨다.
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
            s = sb.toString();
            rotateCount++;
        }

        // 5. 올바른 문자열이 되면 괄호 수를 리턴한다.
        return parenthesesCount;
    }


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
        // 1. StringBuilder 를 이용하여 순서나 갯수를 체크한다.
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            char ch = s.charAt(i);

            switch (ch){
                // 2. 여는 괄호일 경우 같은 모양의 닫는 괄호를 넣는다
                case '(' -> sb.append(')');
                case '[' -> sb.append(']');
                case '{' -> sb.append('}');

                // 3. 닫는 괄호일 경우에서
                default -> {
                    // 1. StringBuilder 가 비어있거나 마지막 문자와 현재의 문자가 다를 경우 잘못된 문자열이라 판단한다
                    if(sb.length() == 0 || sb.charAt(sb.length() - 1) != ch) {
                        parenthesesCount = 0;
                        return false;
                    };

                    // 2. 마지막 것을 삭제한다
                    sb.deleteCharAt(sb.length() - 1);

                    // 3. 마지막 것을 삭제하고 StringBuilder 가 비어있거나, 여는 괄호일 경우 괄호 갯수를 증가시킨다.
                    if(sb.length() == 0){
                        parenthesesCount++;
                    }else{
                        char lastChar = sb.charAt(sb.length() - 1);
                        if(lastChar == '(' || lastChar == '[' || lastChar == '{') parenthesesCount++;
                    }
                }
            }
        }

        // 올바른 문자라고 판단한다.
        return sb.length() == 0;
    }
}