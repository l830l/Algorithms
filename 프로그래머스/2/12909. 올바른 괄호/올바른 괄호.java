class Solution {
    boolean solution(String s) {
        int count = 1;
        int stringLength = s.length();
        if(stringLength < 2 || stringLength % 2 != 0) return false;

        for(int i = 0; i < stringLength; i++) {
            char ch = s.charAt(i);
            count = ch == '('? count + 1 : count - 1;
            if(count < 1) return false;
        }

        return count == 1;
    }
}