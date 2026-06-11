
import java.util.*;

// 1. 노드를 만드는데, 위치인 x 좌표, y 좌표, 자신의 번호, 오른쪽 자식, 왼쪽 자식으로 만든다.
class Node {
    int x;
    int y;
    int num;
    Node leftChild;
    Node rightChild;

    public Node(int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}

class Solution {
    private ArrayList<Integer> preOrderResult = new ArrayList<>();
    private ArrayList<Integer> postOrderResult = new ArrayList<>();

    public int[][] solution(int[][] nodeinfo) {
        // 2. TreeMap 을 만드는데, y좌표를 키로, Node 리스트를 값으로 만든다.
        TreeMap<Integer, List<Node>> map = new TreeMap<>(Collections.reverseOrder());
        int nodeinfoLen = nodeinfo.length;
        for(int i= 0; i < nodeinfoLen; i++){
            Node newNode = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
            if(!map.containsKey(newNode.y)){
                map.put(newNode.y, new ArrayList<>());
            }
            map.get(newNode.y).add(newNode);
        }
        System.out.println(map);

        // 3. y의 리스트를 하나씩 꺼내와서 root 부터 비교하여 값을 넣는다.
        Node root = map.firstEntry().getValue().get(0);
        System.out.println(root);
        for(int y : map.keySet()){
            if(y == root.y) continue;
            List<Node> currentList = map.get(y);
            for(Node node : currentList){
                makeTree(root, node);
            }
        }

        // 4. 전위순회를 짠다.
        preOrder(root);

        // 5. 후위순회를 짠다.
        postOrder(root);

        return new int[][]{
                preOrderResult.stream().mapToInt(i -> i).toArray(),
                postOrderResult.stream().mapToInt(i -> i).toArray()
        };
    }

    private void postOrder(Node current){
        if(current.leftChild != null){
            postOrder(current.leftChild);
        }

        if(current.rightChild != null){
            postOrder(current.rightChild);
        }

        postOrderResult.add(current.num);
    }

    private void preOrder(Node current){
        preOrderResult.add(current.num);
        if(current.leftChild != null){
            preOrder(current.leftChild);
        }
        if(current.rightChild != null){
            preOrder(current.rightChild);
        }
    }

    private void makeTree(Node current, Node node){
        if(current.x > node.x){
            if(current.leftChild == null){
                current.leftChild = node;
            }else{
                makeTree(current.leftChild, node);
            }
        }else{
            if(current.rightChild == null){
                current.rightChild = node;
            }else{
                makeTree(current.rightChild, node);
            }
        }
    }
}