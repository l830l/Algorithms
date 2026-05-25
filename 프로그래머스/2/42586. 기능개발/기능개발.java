import java.util.*;

class Work{
    int progress;
    int speed;

    Work(int progress, int speed){
        this.progress = progress;
        this.speed = speed;
    }

    void updateProgress(){
        this.progress += this.speed;
    }
}
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        // 1. 큐를 만든다.
        // 2. 각 진행률과 개발 속도를 클래스로 묶는다.
        Queue<Work> workList = new ArrayDeque<>();
        int len = progresses.length;
        for(int i = 0; i < len; i++){
            workList.add(new Work(progresses[i], speeds[i]));
        }

        // 6. 이것을 큐가 빌 때까지 반복한다.
        while(!workList.isEmpty()){
            int num = 0;

            //3. 하루가 지나면 진행률을 전부 업데이트 해준다.
            workList.forEach(Work::updateProgress);

            //4. 앞에서 부터 진행률이 100%인 것을 꺼내서 세는 것을 반복하고 100% 미만이 있다면 멈춘다.
            while(true){
                if(workList.isEmpty() || workList.peek().progress < 100) break;
                workList.poll();
                num ++;
            }
            // 5. num 이 0 이면 넣지 말고 다시 하루를 진행한다. num 이 1이상이면 응답 answer 에 넣어준다.
            if(num > 0) answer.add(num);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}