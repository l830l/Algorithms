import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        // 문자열을 하나씩 넣는데 넣는것과 맨 위에 것이 같으면 제거한다.
        Stack<Character> stack = new Stack<>();
        int sLength = s.length();
        for(int i = 0; i < sLength; i++){
            char ch = s.charAt(i);
            char last = stack.isEmpty() ? '#' : stack.peek();
            if(ch == last) stack.pop();
            else stack.push(ch);
        }

        // 스택이 모두 비면 1을 반환한다. 스택이 비어있지 않으면 0을 반환한다.
        return stack.isEmpty() ? 1 : 0;
    }
}