import java.util.*;
import java.util.stream.IntStream;

class Stage {
    int stage;
    double failRate;

    Stage(int stage, double failRate) {
        this.stage = stage;
        this.failRate = failRate;
    }
}
class Solution {
    public int[] solution(int N, int[] stages) {
        // N 만큼 반복한다. => N으로 range 를 통해 stream 을 만든다.
        // 스테이지들의 실패율에 따른 스테이지를 정렬한다.
        return IntStream.range(1, N + 1)
                .mapToObj(stage -> new Stage(stage, getFailRate(stage, stages)))
                .sorted(
                        Comparator.comparing((Stage s) -> s.failRate)
                        .reversed()
                        .thenComparing(s -> s.stage)
                )
                .mapToInt(stage -> stage.stage)
                .toArray();
    }

    // 실패율 계산 함수
    public double getFailRate(int baseStage, int[] stages) {
        // 스테이지 별로 실패율을 계산한다.(아래 함수 작성)
        // stage만큼 반복하여 filter 를 통해 실패율을 계산한다.
        // 실패율 = 클리어 못한 사람수 / 도달한 사람수

        long tryPeopleNums = Arrays.stream(stages)
                .filter(stage -> stage >= baseStage)
                .count();

        if(tryPeopleNums == 0) return 0;

        long failPeopleNums = Arrays.stream(stages)
                .filter(stage -> stage == baseStage)
                .count();

        return (double)failPeopleNums / tryPeopleNums;
    }
}