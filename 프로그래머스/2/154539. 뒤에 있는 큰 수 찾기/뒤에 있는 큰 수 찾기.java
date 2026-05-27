import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int len = numbers.length;
        int[] result = new int[len];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < len; i++){
            if(stack.isEmpty()){
                stack.push(i);
                continue;
            }
            while(!stack.isEmpty() && numbers[i] > numbers[stack.peek()]){
                int top = stack.pop();
                result[top] = numbers[i];
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            result[stack.pop()] = -1;
        }
        return result;
    }
}