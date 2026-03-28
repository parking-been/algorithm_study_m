package main.java.study.a05_shortest.d01.p11403;

//접근 방식
//
// bfs를 통해서 탐색을 했을 때, 탐색이 가능한 지점들은 모두 간접적으로 이어진 것이라고 판단
// N 개의 정점들에 대해서 각각 BFS를 하되, 탐색한 정점들을 별도로 저장해두고, BFS가 마무리되면 별도로 저장된 정점들은 start 정점으로부터 모두 경로가 있는 것이므로, 인접행렬에 이를 1로 표기해준다
// 본인 점에 대해서는 bfs 과정 중에 본인으로 다시 돌아와야지만 1로 표기가 가능하므로, bfs 탐색 전visited 배열에서 본인에 대한 방문 처리는 하지 않는다.

import java.io.*;
import java.util.*;

public class Main_정우재 {
    public static int N;
    public static int[][] graph;
    public static int[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        graph = new int[N][N];
        answer = new int[N][N];

        StringTokenizer st;

        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < N ; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int start = 0 ; start < N ; start++) {
            bfs(start);
        }

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void bfs(int start) {
        ArrayList<Integer> reached = new ArrayList<>();
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            Integer from = queue.poll();
            for(int i = 0 ; i < N ; i++) {
                if(!visited[i] && graph[from][i] == 1 ) {
                    queue.add(i);
                    visited[i] = true;
                    reached.add(i);
                }
            }
        }

        for(int i = 0 ; i < reached.size() ; i++) {
            int to = reached.get(i);
            answer[start][to] = 1;
        }
    }
}