import java.util.*;

class Solution{
    public static List<String> combList = null;
    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        // 2. orders 에 나오는 알파벳을 전부 ArrayList 에서 넣어준다.
        for (int count : course){
            combList = new ArrayList<>();

            // 2-1 course 보다 적은 메뉴들을 시킨 주문 제거
            List<String> orderList = Arrays.stream(orders)
                    .filter(order -> order.length() >= count)
                    .toList();

            // 알파벳에 따른 조합을 구한다. 단, 오름차순으로 구한다.
            orderList.forEach(order -> combinations(
                    0,
                    order.chars().sorted().mapToObj(ch -> (char) ch).toList(),
                    "",
                    count
            ));

            // 조합만큼 갯수를 센다.
            Map<String, Integer> combCount = new HashMap<>();
            combList.forEach(comb -> {
                combCount.put(comb, combCount.getOrDefault(comb, 0) + 1);
            });

            // 가장 높은 수를 구하고 해당 높은 수에 해당하는 단어를 넣어준다.
            int max = combCount.values().stream().max(Comparator.comparing(num -> num)).orElse(0);
            if(max <= 1) continue;

            combCount.entrySet().stream().filter(entry-> entry.getValue() == max)
                    .forEach(entry -> result.add(entry.getKey()));
        }

        // 오름차순으로 반환한다.
        return result.stream().sorted().toArray(String[]::new);
    }

    public List<String> combinations(int idx, List<Character> order, String result, int count){
        if(!result.isEmpty() && result.length() == count) combList.add(result);

        int len = order.size();
        for(int i = idx; i < len; i++){
            combinations(i + 1, order, result + order.get(i), count);
        }

        return combList;
    }
}