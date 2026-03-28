package main.java.study.a07_dp.d02.p2302;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    static int N, M;
    static int[] D;
    static int[] vips;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        D = new int[N+1];
        vips = new int[M+2];
        vips[0] = 0;
        vips[M+1] = N+1;

        for(int i = 1 ; i < M+1 ; i++){
            vips[i] = Integer.parseInt(in.readLine());
        }

        int result = 1;
        for(int i = 0 ; i <M+1 ; i++){
            if(vips[i+1] - vips[i] == 1 )
                continue;
            result *= dp(vips[i+1]-1, vips[i]);
        }
        System.out.println(result);
    }

    public static int dp(int x, int prevVip){
        if(x == prevVip+1)
            return 1;
        if(x == prevVip+2)
            return 2;
        if(D[x]!= 0)
            return D[x];
        return D[x] = dp(x-1, prevVip) + dp(x-2, prevVip);
    }
}
