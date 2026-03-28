package main.java.study.a05_shortest.d03.p1865;

import java.io.*;
import java.util.*;

public class Main_김인송 {
    static List<int[]>[] G;
    static int[] dist;
    static int N, M, W;

    static boolean bf(int start){
        dist[start] = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int[] ET : G[j]){
                    int E = ET[0];
                    int T = ET[1];

                    if(dist[j] != Integer.MAX_VALUE && dist[E] > dist[j] + T){
                        dist[E] =  dist[j] + T;
                        if(i == N -1) return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            G = new List[N]; for(int i = 0; i < N; i++) G[i] = new ArrayList<>();
            dist = new int[N]; Arrays.fill(dist, Integer.MAX_VALUE);

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()) - 1;
                int E = Integer.parseInt(st.nextToken()) - 1;
                int T = Integer.parseInt(st.nextToken());

                G[S].add(new int[]{E, T});
                G[E].add(new int[]{S, T});
            }

            for(int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()) - 1;
                int E = Integer.parseInt(st.nextToken()) - 1;
                int T = Integer.parseInt(st.nextToken());

                G[S].add(new int[]{E, -T});
            }

            boolean ans = false;
            for(int i = 0; i < N; i++) {
                if(dist[i] != Integer.MAX_VALUE) continue;
                if(bf(i)) ans = true;
            }
            if(ans) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }
}
