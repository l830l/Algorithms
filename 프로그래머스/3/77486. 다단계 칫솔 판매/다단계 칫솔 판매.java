import java.util.*;

class Person {
    String name;
    int profit;
    Queue<Integer> profitList;
    String recommenderName;

    Person(String name) {
        this.name = name;
        this.profit = 0;
        this.profitList = new ArrayDeque<>();
    }

    void addProfit(int profit) {
        this.profit += profit;
    }

    void updateRecommender(String recommenderName) {
        this.recommenderName = recommenderName;
    }

    void addProfitList(int profit) {
        this.profitList.add(profit);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Person)) return false;
        return name.equals(((Person)obj).name);
    }

    @Override
    public String toString(){
        return name + " : " + profitList + " : " + profit + "\n";
    }
}
class Solution {
    public static final int TOOTHBRUSH_SALARY = 100;

    public int[] solution(String[] enroll, String[] referral, String[] sellers, int[] amount) {
        // 1. 사람 클래스를 만드는데, 이름, 이익금으로 만든다. 이익금 초기는 0원이다. 이 때 사람을 편하게 집기 위하여 키는 이름 값은 Person 인 것을 하나 만들어준다.
        int len = enroll.length;
        Map<String, Person> peoples = new HashMap<>();

        for(int i = 0; i < len; i++) {
            peoples.put(enroll[i], new Person(enroll[i]));
            peoples.get(enroll[i]).updateRecommender(referral[i]);
        }

        // 2. sellerList를 돌면서 해당 사람에 맞춰 Queue 에 전부 넣는다. 이 때 계산은 100% 로 넣는다.
        int selLen = sellers.length;
        for(int i = 0; i < selLen; i++) {
            peoples.get(sellers[i]).addProfitList(amount[i] * TOOTHBRUSH_SALARY);
        }

        // 3. 그 이후 사람 클래스를 한번 돌려서 자신의 이익금에 0.9를 계산하여 올림처리하여 넣고 부모가 있다면 0.1 배분한 것을 ArrayList 에 넣는다.
        for(int i = len - 1; i >= 0; i--){
            Person person = peoples.get(enroll[i]);
            Person recommender = peoples.get(person.recommenderName);
            while(!person.profitList.isEmpty()){
                int profit = person.profitList.poll();
                person.addProfit((int)Math.ceil(profit * 0.9));
                if(recommender != null && profit * 0.1 > 0){
                    recommender.addProfitList((int)(profit * 0.1));
                }
            }
        }

        return Arrays.stream(enroll).mapToInt(name -> peoples.get(name).profit).toArray();
    }
}