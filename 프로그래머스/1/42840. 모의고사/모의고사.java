import java.util.*;

class A{
    int index = 1;
    int count = 0;
    int[] answer = {1,2,3,4,5};
    int answerLength = 5;
}
class B{
    int index = 2;
    int count = 0;
    int[] answer = {2,1,2,3,2,4,2,5};
    int answerLength = 8;
}

class C{
    int index = 3;
    int count = 0;
    int[] answer = {3,3,1,1,2,2,4,4,5,5};
    int answerLength = 10;
}

class Solution {
    public int[] solution(int[] answers) {
        A a = new A();
        B b = new B();
        C c = new C();

        int answerLength = answers.length;
        for(int i = 0; i < answerLength; i++){
            if(a.answer[i % a.answerLength] == answers[i]) a.count++;
            if(b.answer[i % b.answerLength] == answers[i]) b.count++;
            if(c.answer[i % c.answerLength] == answers[i]) c.count++;
        }

        int maxCount = a.count;
        if(b.count >= maxCount) maxCount = b.count;
        if(c.count >= maxCount) maxCount = c.count;

        List<Integer> list = new ArrayList<>();

        System.out.println(maxCount);
        System.out.println(a.count + " " + b.count + " " + c.count);
        if(maxCount == a.count) list.add(a.index);
        if(maxCount == b.count) list.add(b.index);
        if(maxCount == c.count) list.add(c.index);

        return list.stream().mapToInt(i->i).toArray();
    }
}