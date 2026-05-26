import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        // 5. 이 때 중간에 비교했을때 모든 Map 의 수량이 discount 보다 작거나 같을 때 result 를 +1 해준다.
        int result = 0;
        int flag = 0;
        // 1. 원하는 물품 배열과 수량을 Map 으로 만든다.
        Map<String, Integer> wantList = new HashMap<>();
        int len = want.length;
        for(int i = 0; i < len; i++){
            wantList.put(want[i], number[i]);
        }

        // 2. 할인 배열에서 10개를 앞에서 뽑아서 물품으로 그룹화하여, count 를 센다.
        Map<String, Integer> discountList = new HashMap<>();
        int head = 0;
        int tail = 10;
        int limit = discount.length;
        for(int i = 0; i < 10; i++){
            discountList.put(discount[i], discountList.getOrDefault(discount[i], 0) + 1);
        }

        // 4. 뒤에 것이 discount의 마지막이 되면 이 반복을 멈춘다.
        while(true){
            for(String key : wantList.keySet()){
                if(wantList.get(key) <= discountList.getOrDefault(key, 0)){
                    flag = 1;
                }else{
                    flag = 0;
                    break;
                }
            }
            if(flag == 1) result++;

            if(tail >= limit) break;

            // 3. 이것을 Map 과 비교해서 적은 것이 있다면 앞에 것을 discount 에서 -1 하고 뒤에 하나를 +1 해준다.
            discountList.put(discount[head],discountList.get(discount[head]) - 1);
            head++;
            tail++;
            discountList.put(discount[tail - 1],discountList.getOrDefault(discount[tail - 1], 0) + 1);
        }

        return result;
    }
}