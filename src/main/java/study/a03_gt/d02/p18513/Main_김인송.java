package main.java.study.a03_gt.d02.p18513;

import java.io.*;
import java.util.*;

public class Main_김인송 {

    static int N, K;
    static long result;
    static int[] arr;

    static void bfs () {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        HashSet<Integer> v = new HashSet<>();

        for(int n: arr) {
            q.offer(new int[] {n , 0});
            v.add(n);
        }

        while(!q.isEmpty()) {
            int[] nd = q.poll();
            int n = nd[0];
            int d = nd[1];


            if(!v.contains(n - 1)) {
                q.offer(new int[] {n - 1 , d + 1});
                v.add(n - 1);
            }
            if(!v.contains(n + 1)) {
                q.offer(new int[] {n + 1 , d + 1});
                v.add(n + 1);
            }
//			System.out.println(n + " " + d + " "+ K);
            if(d != 0) {
                result += d;
                if(--K == 0) return;
            }

        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bfs();

        System.out.println(result);
    }
}
