import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Queue<String> cardQ1 = new ArrayDeque<>();
        Queue<String> cardQ2 = new ArrayDeque<>();
        Arrays.stream(cards1).forEach(cardQ1::offer);
        Arrays.stream(cards2).forEach(cardQ2::offer);
        for(String findWord : goal){
            if(!cardQ1.isEmpty() && findWord.equals(cardQ1.peek())){
                cardQ1.poll();
                continue;
            }
            if(!cardQ2.isEmpty() && findWord.equals(cardQ2.peek())){
                cardQ2.poll();
                continue;
            }
            return "No";
        }

        return "Yes";
    }
}