//package main.java.study.a02_tp.d02.p7453;
package p7453;

import java.io.*;
import java.util.*;

public class Main_김인송 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> AB = new HashMap<>();
        HashMap<Integer, Integer> CD = new HashMap<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int ABsum = A[i] + B[j];
                int CDsum = C[i] + D[j];
                AB.put(ABsum, AB.getOrDefault(ABsum, 0) + 1);
                CD.put(CDsum, CD.getOrDefault(CDsum, 0) + 1);
            }
        }

        int result = 0;
        for(int k : AB.keySet()) {
            result += AB.get(k) * CD.getOrDefault(-k, 0);
        }

        System.out.println(result);
    }
}