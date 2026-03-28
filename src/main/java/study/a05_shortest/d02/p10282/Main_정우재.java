package main.java.study.a05_shortest.d02.p10282;

import java.io.*;
import java.util.*;

//접근 방식
// 결국 최초 감염 프로그램으로 부터 갈 수 있는 모든 정점에 대한 최단 거리 알고리즘이다.
// 의존성을 입력 받을 때 a->b 형태로 들어오지만, 감염이 전파될 때는 의존성과 반대 형식으로 전파가 되므로 의존성을 받을 때 from, to를 서로 바꿔서 간선으로 표현해준다
//      예) 들어오는 의존성 : a -> b
//          저장되는 간선 형태 : b(from) -> a (to)
// 최초 감염 컴퓨터로부터 지나갈 수 있는 정점들에 대한 다익스트라 알고리즘을 수행하고, 각 정점들에 대한 distance 값들 중 가장 큰 값이 감염에 걸리는 시간

public class Main_정우재 {
    static class Node implements Comparable<Node>{
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static ArrayList<Node>[] adjList;
    static boolean[] isVisited;
    static int[] distance;
    static int count;
    static ArrayList<Integer> infectedComputers;
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());

        for(int testCase = 1; testCase <= T ; testCase++){
            st = new StringTokenizer(in.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[n+1];
            isVisited = new boolean[n+1];
            distance = new int[n+1];

            for(int i = 0 ; i < n+1 ; i++){
                adjList[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < d ; i++){ //의존성을 받아서 간선으로 저장할 때, 의존성과 반대 방향이 되게끔 저장
                st = new StringTokenizer(in.readLine(), " ");
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                adjList[from].add(new Node(to, cost));
            }

            dijkstra(c); //최초 감염 pc로부터의 다익스트라 알고리즘 수행

            int takenTime = Integer.MIN_VALUE;
            for(int index : infectedComputers){ //감염된 pc중에서 가장 큰 값이 감염 소요 시간
                takenTime = Math.max(takenTime, distance[index]);
            }

            System.out.println(infectedComputers.size() + " " + takenTime);
        }
    }

    private static void dijkstra(int start) { //출발 정점으로부터 갈 수 있는 정점들에 대한 다익스트라 알고리즘 수행
        PriorityQueue<Node> queue = new PriorityQueue<>(); //우선순위 큐
        infectedComputers = new ArrayList<>(); //감염된 pc를 담는 리스트
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0; //출발지의 가중치 0으로 초기화
        queue.add(new Node(start, 0));

        while(!queue.isEmpty()){
            Node lowCostNode = queue.poll();
            int nowIndex = lowCostNode.index;

            if(!isVisited[nowIndex]){ //현재 정점으로부터 인접한 정점들에 대해서 최단 거리 업데이트 수행
                isVisited[nowIndex] = true;
                infectedComputers.add(nowIndex);
                for(Node next : adjList[nowIndex]){
                    if(distance[next.index] > distance[nowIndex] + next.cost){
                        distance[next.index] = distance[nowIndex] + next.cost;
                        queue.add(new Node(next.index, distance[next.index]));
                    }
                }
            }
        }
    }
}
