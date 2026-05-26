import java.util.*;

// 1. 사람과 신고 당한 횟수를 하는 클래스 User를 만든다. 이 때 User name 으로만 equals를 오버라이딩 한다.
class User{
    String name;
    int complainCount;

    User(String name){
        this.name = name;
        this.complainCount = 0;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User)obj;
        return this.name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return this.name + " " + this.complainCount;
    }
}

// 2. 신고 클래스를 만들고 신고한 사람과 신고 당한사람을 필드로 만든다. 이 때 equal 을 구현하여 신고한 사람과 User 가 같으면 같도록 만든다.
class Complain{
    User sender;
    User receiver;

    Complain(User sender, User receiver){
        this.sender = sender;
        this.receiver = receiver;
    }
    @Override
    public boolean equals(Object obj){
        Complain complain = (Complain)obj;
        return this.sender.equals(complain.sender) && this.receiver.equals(complain.receiver);
    }

    @Override
    public int hashCode() {
        return this.sender.hashCode() + this.receiver.hashCode();
    }

    @Override
    public String toString() {
        return "신고한 사람: "+this.sender + ", 신고당한 사람: " + this.receiver + "\n";
    }
}

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 3. 이것을 중복되지 않도록 HashSet 으로 넣고, stream 으로 돌려 아래 map 에 넣는다.
        Set<Complain> complainSet = new HashSet<>();
        Arrays.stream(report).forEach(r -> {
            String[] split = r.split(" ");
            complainSet.add(new Complain(new User(split[0]), new User(split[1])));
        });

        // 4. HashMap 을 만들어서 User 를 키로하고, List\<User> 로 신고한 사람들을 넣는다. 신고 맵을 만든다.
        Map<User, List<User>> complainMap = new HashMap<>();
        complainSet.forEach(complain -> {
            if(!complainMap.containsKey(complain.receiver)){
                complainMap.put(complain.receiver, new ArrayList<>());
            }
            complainMap.get(complain.receiver).add(complain.sender);
        });

        // 5. HashMap 을 만들어서 사람이름을 키로 처리 메일을 받은 횟수를 Integer 로 하는 Map 을 만든다.
        Map<String, Integer> mailMap = new HashMap<>();
        Arrays.stream(id_list).forEach(id -> mailMap.put(id, 0));

        // 6. 위의 신고맵에서 값의 size 가 k 이상인 것을 필터링 하고, 그 이후 신고한 사람에 해당하는 사람들의 count 를 1씩 update 해준다.
        complainMap.values().stream()
                .filter(list -> list.size() >= k)
                .forEach(list -> list.forEach(user -> mailMap.put(user.name, mailMap.get(user.name) + 1)));

        // 7. id_list 를 stream 으로 돌려서 map 으로 바꿔준다음 map 의 값을 뽑아내서 결과 리스트를 반환한다.
        return Arrays.stream(id_list).mapToInt(mailMap::get).toArray();
    }
}