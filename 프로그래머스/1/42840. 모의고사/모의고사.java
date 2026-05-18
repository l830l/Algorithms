import java.util.*;

abstract class Student{
    int index;
    int count;
    int[] answer;
    int answerLength;

    Student(int index, int count, int[] answer, int answerLength){
        this.index = index;
        this.count = count;
        this.answer = answer;
        this.answerLength = answerLength;
    }
}
class A extends Student{
    A(){
        super(1, 0, new int[]{1,2,3,4,5}, 5);
    }
}
class B extends Student{
    B(){
        super(2, 0, new int[]{2,1,2,3,2,4,2,5}, 8);
    }
}

class C extends Student{
    C(){
        super(3, 0, new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}, 10);
    }
}

class Solution {
    public int[] solution(int[] answers) {
        Student[] students = {new A(), new B(), new C()};
        int answerLength = answers.length;
        for(int i = 0; i < answerLength; i++){
            for(Student student : students){
                if(student.answer[i % student.answerLength] == answers[i]) student.count++;
            }
        }

        int maxCount = Arrays.stream(students)
                .max(Comparator.comparing(student -> student.count))
                .get().count;

        return Arrays.stream(students)
                .filter(student -> student.count == maxCount)
                .mapToInt(student -> student.index)
                .toArray();
    }
}