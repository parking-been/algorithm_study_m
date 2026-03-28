package main.java.study.a02_tp.d01.p2531;

import java.util.*;
import java.io.*;

public class Main_김인송 {
    static int[] sushi;
    static int[] eatable;
    static int count(int[] arr) {
        int cnt = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > 0) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        sushi = new int[N];
        eatable = new int[d + 1];
        eatable[c] = 1000000;

        int tmp_k = k;
        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            sushi[i] = n;
            if(tmp_k --> 0) eatable[n] += 1;
        }

        int result = count(eatable);

        for(int i = 0, j = k; i < N; i++, j++){
            eatable[sushi[i]] --;
            eatable[sushi[j % N]] ++;

            result = Math.max(result, count(eatable));
        }

        System.out.println(result);
    }
}
