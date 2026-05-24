import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd){
        TreeSet<Integer> set = new TreeSet<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            set.add(i);
            sb.append('O');
        }
        Stack<Integer> deletedStack = new Stack<>();
        int selected = k;

        for(String command: cmd){
            switch(command.charAt(0)){
                case 'U' -> {
                    int upN = Integer.parseInt(command.split(" ")[1]);
                    int current = selected;
                    for(int i = 0; i < upN; i++){
                        current = set.lower(current);
                    }
                    selected = current;
                }
                case 'D' -> {
                    int downN = Integer.parseInt(command.split(" ")[1]);
                    int current = selected;
                    for(int i = 0; i < downN; i++){
                        current = set.higher(current);
                    }
                    selected = current;
                }
                case 'C' -> {
                    int deleteN = selected;

                    if(selected == set.last()){
                        selected = set.lower(set.last());
                    }else{
                        selected = set.higher(selected);
                    }
                    deletedStack.push(deleteN);
                    set.remove(deleteN);
                }
                case 'Z' -> {
                    set.add(deletedStack.pop());
                }
            }
        }

        while(!deletedStack.isEmpty()){
            sb.setCharAt(deletedStack.pop(), 'X');
        }

        return sb.toString();
    }
}