package main.java.study.a06_mst.d02.p1944;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    static class Edge implements Comparable<Edge>{
        int index;
        int weight;
        public Edge(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N, M;
    static char[][] map;
    static int[][] vertex;
    static ArrayList<Edge>[] adjList;
    static boolean[] visited;
    static int[] minEdge;
    static int[][] delta ={
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        vertex  = new int [2+1+M][2];
        visited = new boolean[2+1+M];
        minEdge = new int[2+1+M];
        adjList = new ArrayList[2+1+M];
        for(int i = 2 ; i < M+1+2 ; i++){
            adjList[i] = new ArrayList<>();
        }

        int keyIndex = 3;
        for(int i = 0 ; i < N ; i++){
            char[] input = in.readLine().toCharArray();
            map[i] = input;
            for(int j = 0 ; j < N ; j++){
                if(map[i][j] == 'S') {
                    map[i][j] = '2';
                    vertex[2] = new int[]{i, j};
                }
                else if(map[i][j] == 'K'){
                    map[i][j] = (char) (keyIndex + '0');
                    vertex[keyIndex++] = new int[]{i, j};
                }
            }
        }

        makeAdjList();
        int result = -1;
        if(adjList[2].size() == M)
            result = prim();
        System.out.println(result);
    }

    private static int prim() {
        Queue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[2] = 0; //시작지점에 대한 minEdge는 0으로 초기화
        pq.add(new Edge(2, 0));
        int count = 0;
        int result = 0;

        while(count != M+1){
            Edge edge = pq.poll();
            int current = edge.index;
            if(visited[current])
                continue;
            visited[current] = true;
            count++;
            result += edge.weight;

            for(Edge next : adjList[current]){
                if(!visited[next.index] && minEdge[next.index] > next.weight){
                    pq.add(next);
                }
            }
        }

        return result;
    }

    private static void makeAdjList() {
        for(int i = 2; i < 1+2+M ; i++){
            bfs(i);
        }
    }

    private static void bfs(int startIndex){
        int startRow = vertex[startIndex][0];
        int startCol = vertex[startIndex][1];
        boolean[][] isVisited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        isVisited[startRow][startCol] = true;
        queue.add(new int[] {startRow, startCol, 0});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];

            if(map[row][col] != '0' && map[row][col] != '1' && map[row][col] != (char) (startIndex + '0') ){
                int vertexIndex = map[row][col] - '0';
                adjList[startIndex].add(new Edge(vertexIndex, distance));
            }

            for(int i = 0 ; i < 4 ; i++) {
                int nextRow = row + delta[i][0];
                int nextCol = col + delta[i][1];
                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                    continue;
                if (!isVisited[nextRow][nextCol] && map[nextRow][nextCol] != '1') {
                    queue.add(new int[]{nextRow, nextCol, distance + 1});
                    isVisited[nextRow][nextCol] = true;
                }
            }
        }
    }
}
