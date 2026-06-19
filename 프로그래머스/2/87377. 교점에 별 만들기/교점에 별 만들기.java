import java.util.*;

class Line{
    long nx;
    long ny;
    long c;

    Line (long nx, long ny, long c) {
        this.nx = nx;
        this.ny = ny;
        this.c = c;
    }
}

class Point {
    long x;
    long y;

    Point (long x, long y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    List<Line> lineList = new ArrayList<>();
    Set<Point> pointSet = new HashSet<>();
    ArrayList<StringBuilder> result = new ArrayList<>();
    Long minCrossX;
    Long minCrossY;
    Long maxCrossX;
    Long maxCrossY;

    public String[] solution(int[][] lines) {
        for (int[] line : lines) {
            Line newLine = new Line(line[0], line[1], line[2]);
            lineList.stream().map(oldLine -> getCrossPoint(oldLine, newLine))
                    .filter(Objects::nonNull)
                    .forEach(point -> {
                        crossMinMax(point);
                        pointSet.add(point);
                    });
            lineList.add(newLine);
        }

        for(long i = minCrossY; i <= maxCrossY; i++) {
            StringBuilder sb = new StringBuilder();
            for(long j = minCrossX; j <= maxCrossX; j++) {
                sb.append('.');
            }
            result.add(sb);
        }
        pointSet.forEach(point -> {
            result.get((int)(result.size() - (point.y - minCrossY) - 1)).setCharAt((int)(point.x - minCrossX), '*');
        });

        return result.stream().map(StringBuilder::toString).toArray(String[]::new);
    }

    private Point getCrossPoint(Line oldLine, Line newLine) {
        long denominator = oldLine.nx * newLine.ny - oldLine.ny * newLine.nx;
        if(denominator == 0) return null;

        long numeratorX = oldLine.ny * newLine.c - oldLine.c * newLine.ny;
        if(numeratorX % denominator != 0) return null;

        long numeratorY = oldLine.c * newLine.nx - oldLine.nx * newLine.c;
        if(numeratorY % denominator != 0) return null;

        return new Point(numeratorX / denominator, numeratorY / denominator);
    }

    private void crossMinMax(Point point){
        if(minCrossX == null || point.x < minCrossX) minCrossX = point.x;
        if(minCrossY == null || point.y < minCrossY) minCrossY = point.y;
        if(maxCrossX == null || point.x > maxCrossX) maxCrossX = point.x;
        if(maxCrossY == null || point.y > maxCrossY) maxCrossY = point.y;
    }
}