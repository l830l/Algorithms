import java.util.*;

class Solution {
    public int[] solution(int n) {
        // 1. 크기가 n*n인 부모를 선언한다.
        int [][] board = new int[n][n];

        // 2. 넣어줄 숫자 num 을 선언한다.
        int num = 1;

        // 3. x, y 좌표를 선언한다.
        int x = 0, y = 0;

        // 7. 이를 계속 반복한다.
        while(true) {
            // 4-1 아래로 갈 수 없을 때까지 간다.
            while(true){
                board[y][x] = num;
                num++;
                if(y == n - 1 || board[y + 1][x] != 0) break;
                y++;
            }

            // 4-2 만약 오른쪽으로 가려 할 때, 갈 수 없는 상황이면 중단한다.
            if(x == n - 1 || board[y][x + 1] != 0) break;
            x++;

            // 5-1. 오른쪽으로 갈 수 없을 때까지 간다.
            while(true){
                board[y][x] = num;
                num++;
                if(x == n - 1 || board[y][x + 1] != 0) break;
                x++;
            }

            // 5-2. 만약 왼쪽 위로 가려할 때, 갈 수 없는 상황이면 중단한다.
            if(x == 0 || y == 0 || board[y - 1][x - 1] != 0) break;
            x--;
            y--;

            // 6-1. 왼쪽 위로 갈 수 없을 때까지 간다.
            while(true){
                board[y][x] = num;
                num ++;
                if(x == 0 || y == 0 || board[y - 1][x - 1] != 0) break;
                x--;
                y--;
            }

            // 6-2. 만약 아래로 가려할 때, 갈 수 없는 상황이면 중단한다.
            if(y == n - 1 || board[y + 1][x] != 0) break;
            y++;
        }


        return Arrays.stream(board)
                .flatMapToInt(Arrays::stream)       // row를 stream 으로 펼친다.
                .filter(value -> value != 0)    // filter 를 이용하여 0 이 아닌 것들만 남긴다.
                .toArray();                         // 다시 배열로 바꾼다.
    }
}