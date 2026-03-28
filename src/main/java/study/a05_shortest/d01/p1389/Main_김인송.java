package main.java.study.a05_shortest.d01.p1389;

import java.io.*;
import java.util.*;

public class Main_김인송 {
    static int N;
    static boolean[] v;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(map[i], 100000);

        // 1. 인접 행렬 입력받기
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            map[from][to] = 1;
            map[to][from] = 1;
        }

        // 2. 플로이드-워셜 알고리즘 수행
        for(int k = 0; k < N; k++) {          // k: 거쳐가는 정점
            for(int i = 0; i < N; i++) {      // i: 출발하는 정점
                for(int j = 0; j < N; j++) {  // j: 도착하는 정점
                    // i에서 k로 갈 수 있고, k에서 j로 갈 수 있다면 -> i에서 j로 갈 수 있음
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for(int[] e: map) System.out.println(Arrays.toString(e));

        System.out.println(sb);


    }
}
