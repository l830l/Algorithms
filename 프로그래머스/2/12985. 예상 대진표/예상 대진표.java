import java.util.*;

class Solution {
    private int answer = 0;

    public int solution(int n, int a, int b){
        int[] nums = new int[n];
        for(int i = 1; i <= n; i++){
            nums[i - 1] = i;
        }
        makeFightTable(nums, a, b);

        return answer;
    }

    private int[] makeFightTable(int[] nums, int a, int b){
        // 1. 먼저, 무조건 배열에서 2개씩 짝짓는다.
        List<List<Integer>> fightList = new ArrayList<>();
        ArrayList<Integer> newFightList = new ArrayList<>();
        int numsLen = nums.length;
        for(int i = 0; i < numsLen; i++){
            fightList.add(Arrays.asList(nums[i], nums[++i]));
        }

        for (List<Integer> twoFight : fightList) {
            int fightA = twoFight.get(0);
            int fightB = twoFight.get(1);

            // 2. 둘이 마주치면 라운드를 멈추고 몇번 싸웠는지 반환한다.
            if ((fightA == a && fightB == b) || (fightA == b && fightB == a)) {
                answer++;
                return null;
            }

            // 3. 만약 두번째 또는 세번째 매개변수 숫자가 들어오면 해당을 선택한다.
            // 4. 나머지는 인덱스가 홀수를 선택한다.
            if (fightA == a || fightB == a) {
                newFightList.add(a);
            } else if (fightA == b || fightB == b) {
                newFightList.add(b);
            } else {
                newFightList.add(fightA);
            }
        }
        answer++;

        // 5. 그리고 2/N으로 하여금 다시 반복하는데, 만약 N이 1이되면 멈춘다.
        return makeFightTable(newFightList.stream().mapToInt(i -> i).toArray(), a, b);
    }
}