import java.util.*;

class Person{
    int num;
    int count;

    Person(int num){
        this.num = num;
        this.count = 0;
    }

    @Override
    public String toString(){
        return "Person{" + "num=" + num + ", count=" + count + '}';
    }
}
class Solution {
    public int[] solution(int n, String[] words) {
        // 1. 사람 클래스를 작성한다. 속성은 count, num. 그 이후 1부터 n 까지 사람 클래스를 만들어 큐에 넣는다.
        Queue<Person> queue = new ArrayDeque<>();
        char prevWordLast = ' ';
        for(int i = 1; i <= n; i++){
            queue.add(new Person(i));
        }

        // 2. 끝말잇기 단어용 HashSet 을 넣는다.
        Set<String> alreadyWords = new HashSet<>();

        // 3. word 배열을 돌며 사람 큐를 빼서 넣고 count 를 +1 증가 시킨 후 그 사람이 말한 단어를 HashSet 에 넣는것을 반복한다.
        for(String word : words){
            Person current = queue.poll();
            current.count++;
            boolean isSuccess = alreadyWords.add(word);
            // 4. 만약 첫 단어가 앞사람의 끝 단어와 다르거나 Set 에 추가를 실패하면(중복), 그 사람의 번호와 count를 리턴한다.
            if(prevWordLast != ' ' && (prevWordLast != word.charAt(0)|| !isSuccess)){
                return new int[]{current.num, current.count};
            }
            prevWordLast = word.charAt(word.length() - 1);
            queue.add(current);
        }
        // 5. 만약 끝까지 word 배열을 다 돌면 0, 0 을 리턴한다.
        return new int[]{0, 0};
    }
}