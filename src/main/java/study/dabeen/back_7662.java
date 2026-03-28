package main.java.study.dabeen;
import java.io.*;
import java.util.*;

/*
 <인사이트>
 최대힙 최소힙 + 버전 관리용 배열 사용
 삽입시 두 힙종류에 모두 넣어주고,
 삭제 시, 버전 관리용 배열에 삭제된 배열에 대한 정보를 체크해준다.
 그리고 삭제시, 이미 다른 쪽에 의해 삭제된 Node인지 아닌지를 확인해주고 없애준다.


 잘못 푼 부분 : 마지막에 queue 에 남아있는 값에 대해 pop이 아니라 peek (읽어오는것이었다.)


 <팁>
 comparing하는 방법에 대해 외워두자
 maxheap = new PriorityQueue<>(Comparator.comparing((Node n)->n.data).thenComparing(n->n.idx));

 <다른 블로그의 인사이트>
 https://loosie.tistory.com/314
 treeMap 사용

 TreeMap이란? : 이진트리를 기반으로 한 Map 컬렉션
 같은 Tree 구조로 이루어진 TreeSet과의 차이점은 TreeSet은 그냥 값만 저장한다면, TreeMap은 Entry를 저장한다는 점
 TreeMap에 객체를 저장하면 자동정렬되는데, 키는 저장과 동시에 자동 오름차순으로 정렬되고 타입이 숫자일경우, 값으로, 문자일경우 유니코드로 정렬된다.
 treemap 개념에 대하여
 https://dev-coco.tistory.com/39
 - TreeMap<Integer, Integer> map = new TreeMap<>();
 - firstKey
 - lastKey


 treemap 으로 어떻게 풀었는가?
 https://loosie.tistory.com/314


 */
public class back_7662 {
    static int T;
    static int K;
    static int totalIdx;
    static class Node{
        int data;
        int idx;

        public Node(int data, int idx) {
            this.data = data;
            this.idx = idx;
        }
    }

    static PriorityQueue<Node> maxheap;
    static PriorityQueue<Node> minheap;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=T;tc++) {
            K = Integer.parseInt(br.readLine());
            //Queue + visited 배열 초기화

            maxheap = new PriorityQueue<>(Comparator.comparing((Node n)->n.data).reversed()); //최대힙
            minheap = new PriorityQueue<>(Comparator.comparing((Node n)->n.data)); //최소 힙
            visited = new boolean[1000000+1];
            totalIdx = 0;
            for(int i=0;i<K;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine() ," ");
                String command = st.nextToken();
                int comn = Integer.parseInt(st.nextToken());

                solution(command, comn);

            }

            cleanMax();
            cleanMin();

            if(maxheap.isEmpty() || minheap.isEmpty()) {
                sb.append("EMPTY\n");
            }
            else {
                sb.append(maxheap.peek().data).append(" ").append(minheap.peek().data).append("\n");
            }


        }

        System.out.println(sb);



    }


    public static void cleanMax() {
        while(!maxheap.isEmpty() && visited[maxheap.peek().idx]) {
            maxheap.poll();
        }
    }

    public static void cleanMin() {
        while(!minheap.isEmpty() && visited[minheap.peek().idx]) {
            minheap.poll();
        }
    }




    public static void solution(String command, int comn) {
        if(command.equals("I")) { //삽입
            maxheap.add(new Node(comn,totalIdx));
            minheap.add(new Node(comn,totalIdx));
            totalIdx++;


        }else { //삭제
            if(comn==-1) {
                //낮은 것 제거

                cleanMin();
                if(!minheap.isEmpty()) {
                    visited[minheap.poll().idx] = true;
                }



            }else {
                cleanMax();
                if (!maxheap.isEmpty()) {
                    visited[maxheap.poll().idx] = true;
                }

            }
        }

    }

}