package main.java.study.a04_greedy.d01.p13305;

import java.io.*;
import java.util.*;

public class Main_김인송{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        long[] road = new long[N - 1];
        long[] city = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N - 1; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            city[i] = Integer.parseInt(st.nextToken());
        }

        long result = 0;
        for(int i = 0; i < N - 1; i++) {
            if(city[i] > city[i + 1]) {
                result += city[i] * road[i];
                continue;
            }
            else {
                int t = i;
                long sum = 0;
                while(t < N - 2 && city[i] <= city[t + 1]) t++;
                for(int j = i; j <= t; j++) sum += road[j];
                result += sum * city[i];

                i = t;
            }
        }
        System.out.println(result);

    }
}


