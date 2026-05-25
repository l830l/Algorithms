import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        // 참여자를 모두 HashMap 에 넣는다. 키는 이름, 값은 해당 이름을 가진 사람 수로 한다.
        HashMap<String, Integer> map = new HashMap<>();
        Arrays.stream(participant).forEach(name -> {
            if(map.containsKey(name)) {
                map.put(name, map.get(name) + 1);
            }else{
                map.put(name, 1);
            }
        });

        Arrays.stream(completion).forEach(name -> {
            if(map.containsKey(name)) {
                map.put(name, map.get(name) - 1);
            }
            if(map.get(name) == 0) {
                map.remove(name);
            }
        });

        return map.keySet().stream().map(i -> i).toArray()[0].toString();
    }
}