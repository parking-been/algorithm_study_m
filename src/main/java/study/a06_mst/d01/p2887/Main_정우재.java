package main.java.study.a06_mst.d01.p2887;

import java.io.*;
import java.util.*;

/*
시간복잡도 계산
1. 단순하게 연결할 수 있는 행성간의 모든 간선을 구해서 정렬 시 (49억 * log 49억)이라는 큰 시간 복잡도 => 시간초과
2. x축, y축, z축 방향으로 행성들을 정렬하고, 인접한 행성들간의 간선 갯수인 n-1개를 각기 간선으로 만들어준다 => 3 * 10만 = 30만, 30만 log 30만 = 약 570만 => 통과

로직
- 입력받은 행성 데이터를 x축, y축, z축을 기준으로 각기 정렬한다
- 정렬이 되면, 인접한 행성들간의 간선만 생성해서 추가한다 (각 축마다 총 n-1개의 간선이 생성. 총 3개의 축이므로 3*(n-1)개의 간선 생성)
    이유 : 결국 행성들간의 최소 간선은 x축, y축, z축 중 좌표가 가까운 행성과 나오게 된다. 즉, 특정 행성 a의 최소 간선은 x축으로 가까운 b와의 행성, y축으로 가까운 c와의 행성, z축으로 가까운 d와의 행성간의 간선 중 하나이다.
          그렇기에 각 축 방향으로 정렬 후 인접한 행성끼리만 간선을 만든다. (핵심 : 최소 간선 후보는 좌표가 가까운 행성들 사이에서만 나온다)
- 간선을 정렬하고, kruskal 알고리즘 수행
 */

public class Main_정우재 {
    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        long weight;
        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }
    static class Planet{
        int index;
        int x;
        int y;
        int z;

        public Planet(int index, int x, int y, int z) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static int N;
    static int[] parents;
    static Edge[] edgeList;
    static Planet[] planets;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());

        planets = new Planet[N];
        edgeList = new Edge[3*(N-1)];
        parents = new int[N];
        for(int i = 0; i < N ; i++){
            st = new StringTokenizer(in.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(i, x, y, z);
        }

        Planet[] x_planets = planets.clone();
        Planet[] y_planets = planets.clone();
        Planet[] z_planets = planets.clone();

        Arrays.sort(x_planets, (a, b) -> a.x - b.x);
        Arrays.sort(y_planets, (a, b) -> a.y - b.y);
        Arrays.sort(z_planets, (a, b) -> a.z - b.z);

        int count = 0;
        for(int i = 0 ; i < N-1 ;i++){
            Planet a = x_planets[i];
            Planet b = x_planets[i+1];
            edgeList[count++] = new Edge(a.index, b.index, Math.abs(a.x - b.x));
        }

        for(int i = 0 ; i < N-1 ;i++){
            Planet a = y_planets[i];
            Planet b = y_planets[i+1];
            edgeList[count++] = new Edge(a.index, b.index, Math.abs(a.y- b.y));
        }

        for(int i = 0 ; i < N-1 ;i++){
            Planet a = z_planets[i];
            Planet b = z_planets[i+1];
            edgeList[count++] = new Edge(a.index, b.index, Math.abs(a.z - b.z));
        }

        long result = kruskal();
        System.out.println(result);
    }

    private static long kruskal() {
        for(int i = 0 ; i < N ; i++){ //makeSet
            parents[i] = -1;
        }
        Arrays.sort(edgeList);

        int count = 0;
        long weight = 0;
        for(Edge edge : edgeList){
            if(union(edge.from, edge.to)){
                count++;
                weight += edge.weight;
                if(count == N-1)
                    break;
            }
        }

        return weight;
    }

    private static boolean union(int x, int y){
        int xRoot = findSet(x);
        int yRoot = findSet(y);

        if(xRoot == yRoot)
            return false;
        if(parents[xRoot] <= parents[yRoot]){
            parents[xRoot] += parents[yRoot];
            parents[yRoot] = xRoot;
        }
        else{
            parents[yRoot] += parents[xRoot];
            parents[xRoot] = yRoot;
        }
        return true;
    }

    private static int findSet(int x){
        if(parents[x] < 0)
            return x;
        return parents[x] = findSet(parents[x]);
    }
}

