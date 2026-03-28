package main.java.study.a05_shortest.d01.p1389;
import java.io.*;
import java.util.*;
/*
 * 최단 거리 문제를 풀기전
 * 다익스트라
 * 벨만 포드
 * 플로이드 와샬
 *
 * 등등을 공부했는데,
 * 이 문제는 weight이 아예 없는 문제라서
 * 그냥 bfs 로 풀기로했다.
 * 어차피 bfs는 문제가 원하는 케빈 베이컨의 수를 구하기 적합한 알고리즘이기 때문에
 * 그것을 각 정점마다 돌려주었다.
 *
 * 그런데 지금 생각해보면 플로이드 와샬을 써도 되었을 것 같다.
 * 내가 푼 방법
 * : O((n+m)*n) -> 500000 -> 내꺼가 조금더 빠르다.
 * 플로이드 와샬
 * : O(n**3) -> 1000000
 * https://steady-coding.tistory.com/95
 *
 * */


public class Main_박다빈 {
    static List<Integer>[] graph;
    static int N;
    public static void main(String[] args)throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];

        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        int mini=Integer.MAX_VALUE;
        int result = 1;
        for(int i=1;i<=N;i++) {
            int tmp = bfs(i);
            if(tmp<mini) {
                mini = tmp;
                result = i;
            }

        }

        System.out.println(result);


    }

    static public int bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        int[] count = new int[N+1];
        queue.add(start);
        count[start] = 0;
        visited[start] = true;
        int totalSum =0;

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            int dis = count[cur];
            for(int e : graph[cur]) {
                if (visited[e]) continue;
                queue.add(e);
                visited[e] = true;
                count[e] = dis+1;
                totalSum+=count[e];
            }
        }

        return totalSum;


    }
}
