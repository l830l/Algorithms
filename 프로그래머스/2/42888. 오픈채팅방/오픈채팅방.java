import java.util.*;

class Command{
    String command;
    String uid;

    Command(String command, String uid){
        this.command = command;
        this.uid = uid;
    }
}
class Solution {
    public String[] solution(String[] record) {
        List<Command> result = new ArrayList<>();
        Map<String, String> userList = new HashMap<>();

        for (String cmd : record){
            String[] split = cmd.split(" ");
            String command = split[0];
            String uid = split[1];
            String nickname = "";


            switch (command){
                case "Enter":
                    nickname = split[2];
                    userList.put(uid, nickname);
                    result.add(new Command(command, uid));
                    break;
                case "Change":
                    nickname = split[2];
                    userList.put(uid, nickname);
                    break;
                case "Leave":
                    result.add(new Command(command, uid));
                    break;
            }
        }

        return result.stream().map(command -> {
            StringBuilder sb = new StringBuilder(userList.get(command.uid));
            switch (command.command){
                case "Enter" -> sb.append("님이 들어왔습니다.");
                case "Leave" -> sb.append("님이 나갔습니다.");
            }
            return sb.toString();
        }).toArray(String[]::new);
    }
}