import java.util.*;
import java.util.stream.Collectors;

// 1. Animal 클래스를 만든다.(자신의 번호, 방문 여부)
class Animal {
    int number;
    boolean visited;
    boolean checked;
    Animal parent;

    public Animal(int number) {
        this.number = number;
    }

    public void visit() {
        this.visited = true;
    }

    @Override
    public String toString() {
        return "Animal{number=" + number + ", visited=" + visited + ")}\n";
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Animal)) return false;
        return ((Animal) o).number == this.number;
    }

    @Override
    public int hashCode() {
        return this.number;
    }
}

// 2. Wolf와 Sheep 클래스를 만든다.
class Wolf extends Animal {
    public Wolf(int number) {
        super(number);
    }
}

// 3. Sheep 클래스에 Path 속성을 추가한다. 이 Path 는 해당 양에서 root 로 가는 경로이다.
class Sheep  extends Animal {
    ArrayList<Animal> path;

    public Sheep(int number) {
        super(number);
        path = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sheep{");
        sb.append("number=" + number + " [");
        for (Animal animal : path) {
            sb.append(animal instanceof Sheep ? "Sheep" : "Wolf");
            sb.append("(");
            sb.append(animal.number);
            sb.append(") ");
            sb.append(animal.visited ? "방문" : "미방문");
            sb.append(" | ");
            sb.append(animal.checked ? "체킹" : "미체킹");
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.append("}\n").toString();
    }
}

// 7. Owner 클래스를 만들고 속성은 양 마릿수, 늑대 마릿수, 자신의 위치로 한다.
class Owner {
    int sheepNum;
    int wolfNum;

    void increaseSheep(int num){
        this.sheepNum += num;
    }

    void increaseWolf(int num){
        this.wolfNum += num;
    }

    @Override
    public String toString() {
        return "Owner{sheepNum = "+ sheepNum + ", wolfNum = " + wolfNum + "}\n";
    }
}

class Solution {
    public int solution(int[] info, int[][] edges) {
        // 1. Animal 클래스를 만든다.(자신의 번호, 방문 여부)
        int infoLen = info.length;
        Map<Integer, Animal> animalMap = new HashMap<>();
        List<Sheep> sheepList = new ArrayList<>();
        Owner owner = new Owner();

        for(int i = 0; i < infoLen; i++) {
            if(info[i] == 1) {
                animalMap.put(i, new Wolf(i));
            } else {
                Sheep sheep = new Sheep(i);
                animalMap.put(i, sheep);
                sheepList.add(sheep);
            }
        }

        // 4. Tree는 edge를 통해 부모와 자식간의 관계를 서로 이어준다. 즉, Animal 에 parent 속성을 추가한다.
        for (int[] edge : edges) {
            Animal parent = animalMap.get(edge[0]);
            Animal child = animalMap.get(edge[1]);
            child.parent = parent;
        }

        // 5. 위 parent 속성을 이용하여 양마다 path 속성을 채운다.
        sheepList.forEach(sheep -> {
            Animal current = sheep;
            while(true){
                sheep.path.add(0, current);
                if(current.parent == null) break;
                current = current.parent;
            }
        });


        // 6. 양을 바로 먹을 수 있으면 먹는다.
        for(Sheep sheep : sheepList){
            if(sheep.path.stream().filter(animal -> !animal.visited).count() == 1) {
                sheepList = eatSheep(sheep, owner, sheepList);
            }
        }
        // 7. 양을 먹는 순서를 생각해서 모든 양을 먹을 수 있는지 파악한다.
        List<List<Integer>> sheepNumList = Algorithm.permutation(sheepList.stream().mapToInt(sheep -> sheep.number).toArray());


        int max = 0;
        for(List<Integer> numList :sheepNumList){
            int sheepNum = owner.sheepNum;
            int wolfNum = owner.wolfNum;
            animalMap.values().forEach(animal -> {
                if(!animal.visited) animal.checked = false;
            });

            secondLoop: for(Integer num : numList){
                Sheep current = (Sheep) animalMap.get(num);
                for(Animal animal : current.path){
                    if(!animal.checked){
                        animal.checked = true;
                        if(animal instanceof Sheep) sheepNum++;
                        else wolfNum++;
                        
                        if(wolfNum >= sheepNum) break secondLoop;
                    }
                }


            }
            if(sheepNum > max) max = sheepNum;
        }
        return max;
    }

    private List<Sheep> eatSheep(Sheep sheep, Owner owner, List<Sheep> sheepList){
        for(Animal animal : sheep.path){
            if(animal.visited) continue;
            animal.visit();
            animal.checked = true;
            if(owner.sheepNum != 0 && owner.wolfNum >= owner.sheepNum) break;
            if(animal instanceof Sheep) owner.increaseSheep(1);
            else owner.increaseWolf(1);
        }
        return sheepList.stream().filter(modSheep -> !modSheep.visited).collect(Collectors.toCollection(ArrayList::new));
    }
}
class Algorithm {
    static List<List<Integer>> permutation(int[] numbers) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[numbers.length];

        dfs(numbers, visited, new ArrayList<>(), result);

        return result;
    }

    static void dfs(
            int[] numbers,
            boolean[] visited,
            List<Integer> current,
            List<List<Integer>> result
    ) {
        if (current.size() == numbers.length) {
            result.add(new ArrayList<>(current)); // 핵심: 복사해서 넣기
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            current.add(numbers[i]);

            dfs(numbers, visited, current, result);

            current.remove(current.size() - 1); // 핵심: 선택 취소
            visited[i] = false;
        }
    }
}