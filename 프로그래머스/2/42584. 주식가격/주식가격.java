import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        // price 배열을 받으면, return 할 배열도 만든다
        int length = prices.length;
        int[] answer = new int[length];

        // price 만큼 반복한다.
        for(int i = 0; i < length - 1; i++) {
            Stack<Integer> stack = new Stack<>();
            int current = prices[i];
            for(int j = i + 1; j < length; j++){
                int compare = prices[j];
                stack.push(compare);
                if(current > compare){
                    answer[i] = stack.size();
                    break;
                }
                answer[i] = stack.size();
            }
        }

        return answer;
    }
}