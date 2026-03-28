package main.java.study.a03_gt.d01.p2606;

import java.io.*;
import java.util.*;

/*
 * 전파 -> bfs
 * graph 를 List<List<>> 형식으로 구현
 *
 * 다른 블로그를 보니 어차피 N으로 크기가 정해져 있으니
 * List[] 형식으로 저장하기도 하더라
 *
 * 트러블 슈팅
 * ArrayIndexOutofBound : 1~N 까지 번호를 가진 노드들을 관리해야하는데, visited 배열의 사이즈를 N개만 할당해서 나온 에러다.
 *
 * */



public class Main_박다빈 {
    static List<List<Integer>> graph;
    static int N;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        graph = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];

        //이거 기억하기 - 자바에서 graph 구현하는 법
        for(int i=0;i<N+1;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }


        System.out.println(bfs(1));





    }

    public static int bfs(int x) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(x);
        visited[x] = true;
        int count=0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int m : graph.get(cur)) {
                if(visited[m]) continue;
                queue.add(m);
                visited[m] = true;
                count++;
            }
        }

        return count;
    }

}
