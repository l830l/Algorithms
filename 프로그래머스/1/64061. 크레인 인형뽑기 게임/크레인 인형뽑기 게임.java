import java.util.*;
//1. board 맨 위의 위치와 수를 나타내는 클래스 배열을 N길이만큼 만든다. 
class InBox {
    static int LIMIT;
    int y;
    int num;
    
    InBox(int y, int num){
        if(y == LIMIT) this.y = -1;
        else this.y = y;
        updateNum(num);
    }
    
    InBox plusY(){
        this.y ++;
        if(y >= LIMIT) this.y = -1;
        return this;
    }
    
    InBox updateNum(int num){
        if(y == -1) this.num = -1;
        else this.num = num;
        return this;
    }
    
    @Override
    public String toString(){
        return "y: "+ y + ", num:" + num;
    }
}
class Solution {
    public int solution(int[][] board, int[] moves) {
        int result = 0;
        // 1. 배열 만들기
        int n = board.length;
        InBox.LIMIT = n;
        InBox[] topList = new InBox[n];
        Stack<Integer> bucket = new Stack<>();
        
        for(int x = 0; x < n; x++){
            int y = 0;
            while(true){
                int currentNum = board[y][x];
                if(currentNum != 0){
                    topList[x] = new InBox(y, currentNum);
                    break;
                }
                if(++y >= n) break;
            }
        }
        // System.out.println("topList" + Arrays.toString(topList));
            
        // 7. 이것을 move 만큼 반복한다. 
        int moveLen = moves.length;
        for(int i = 0; i < moveLen; i++){
            // System.out.println("i :"+i);
            // 2. move 에서 현재 수가 있으면 해당 tops 의 위치로 가서 위치를 +1 한 다음 수를 가져와 바구니에 넣고 길이를 늘린다.
            int pickX = moves[i] - 1;
            InBox pickedTop = topList[pickX];
            // System.out.println("moves[i] : "+moves[i]);
            // System.out.println("뽑는 거 = x: "+ pickX +", " + pickedTop);
            if(pickedTop.y == -1) continue;
            // 바구니의 들어온 수와 top이 같으면 pop 을 하고 result + 2 를 해준다.
            int pickingNum = pickedTop.num;
            if(bucket.isEmpty()){
                bucket.push(pickingNum);
                // System.out.println(i+"번째에 바구니 비었음");
            } else {
                // System.out.println("바구니 맨 위: "+ bucket.peek() + ", 들어온 값: "+ pickingNum);
                if(bucket.peek() == pickingNum){
                    // System.out.println("바구니랑 들어온거 같음");
                    bucket.pop();
                    result += 2;
                } else {
                    // System.out.println("달라서 걍 넣음");
                    bucket.push(pickingNum);
                }
            }
            
            pickedTop.plusY();
            if(pickedTop.y == -1) {
                pickedTop.num = -1;
            }else{
                // System.out.println("pickX : "+ pickX +"pickTop.y : " + pickedTop.y);
                pickedTop.updateNum(board[pickedTop.y][pickX]);              
            }
            
            // System.out.println("뽑힌거 바뀐위치 :  = x: "+ pickX +", " +pickedTop);
            // System.out.println("바뀐 topList" + Arrays.toString(topList));
            // System.out.println("=================");
        }
        return result;
    }
}