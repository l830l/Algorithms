import java.util.*;

class Path{
    int beforeX;
    int beforeY;
    int afterX;
    int afterY;

    Path(int beforeX, int beforeY, int afterX, int afterY){
        this.beforeX = beforeX;
        this.beforeY = beforeY;
        this.afterX = afterX;
        this.afterY = afterY;
    }

    @Override
    public boolean equals(Object obj) {
        Path path = (Path)obj;
        boolean conditionEqauls = path.beforeX == this.beforeX
                && path.beforeY == this.beforeY
                && path.afterX == this.afterX
                && path.afterY == this.afterY;
        boolean conditionReverse = path.afterX == this.beforeX
                && path.afterY == this.beforeY
                && path.beforeX == this.afterX
                && path.beforeY == this.afterY;

        return conditionEqauls || conditionReverse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(beforeX + afterX, beforeY + afterY);
    }
}
class Solution {
    public int solution(String dirs) {
        Set<Path> set = new HashSet<>();

        // 1. 캐릭터 좌표 x, y를 클래스로 지정한다.
        int x = 0;
        int y = 0;


        // 2. command 을 통해 dx 와 dy 를 구한다.
        for(int i = 0; i < dirs.length(); i++){
            int dx = x;
            int dy = y;

            char command = dirs.charAt(i);

            // 단, 범위를 벗어나면 해당은 무시한다.
            if(x <= -5 && command == 'L') continue;
            if(x >= 5 && command == 'R') continue;
            if(y >= 5 && command == 'U') continue;
            if(y <= -5 && command == 'D') continue;

            switch(command){
                case 'U' -> dy++;
                case 'D' -> dy--;
                case 'L' -> dx--;
                case 'R' -> dx++;
            }
            // 3. x, y, dx, dy 를 클래스에 넣는다.
            Path newPath = new Path(x, y, dx, dy);

            // 4. eqauls과 hashCode를 override 하고, HashSet 에 넣는다.
            set.add(newPath);

            // 5. 그 이후 x, y 좌표를 옮긴다.
            x = dx;
            y = dy;
        }


        // 5. count 를 센다.
       return set.size();
    }
}