package main.java.study.a05_shortest.d01.p1389;

import java.io.*;
import java.util.*;

// 접근 방식
//
// 각 사람마다 bfs를 수행하면서, 연결된 정점들을 방문 처리할 때 마다 이 사람까지의 단계 즉, 너비를 더해주면 된다. 이를 큐가 빌 때까지 반복하고, 더해준 값들의 합이 케빈 베이컨의 수이다.
// bfs를 마치고 본인의 케빈 베이컨의 수가 현재 가장 작은 케빈 베이컨의 수보다 작다면 업데이트를 한다
public class Main_정우재 {
    static class Person{
        int index;
        int width;
        Person (int index, int width){
            this.index = index;
            this.width = width;
        }
    }

    public static int N;
    public static int minKevinBaconNumber = Integer.MAX_VALUE;
    public static int answer;
    public static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        int M= Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];

        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++) { //양방향 인접 리스트 구현
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        for(int i = 1 ; i < N+1 ; i++) { //각 사람마다 bfs를 돌며 최소 케빈 베이컨 수를 구함
            bfs(i, 0);
        }
        System.out.println(answer);
    }
    private static void bfs(int start, int width) {
        Queue<Person> queue = new ArrayDeque<>();
        boolean[] isVisited = new boolean[N+1];
        queue.add(new Person(start, width));
        isVisited[start] = true;

        int count = 0;
        while(!queue.isEmpty()) {
            Person p = queue.poll();
            int listSize = adjList[p.index].size(); //인접한 사람수
            for(int i = 0 ; i < listSize; i++) {
                int adjIndex = adjList[p.index].get(i); //인접한 사람의 index를 가져옴
                if(!isVisited[adjIndex]) { //인접한 사람이 방문하지 않은 경우 방문처리를 해주고 queue에 추가
                    queue.add(new Person(adjIndex, p.width+1));
                    isVisited[adjIndex] = true;
                    count += p.width+1; //count에 너비값을 더해줌
                }
            }
        }

        if(minKevinBaconNumber > count) { //bfs 종료 후 minKevinBaconNumber, answer 업데이트
            minKevinBaconNumber = count;
            answer = start;
        }
    }
}

