package main.java.study.a07_dp.d03.p3687;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    static int N;
    static long[] D;
    static int[] minCount;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        D = new long[101];
        minCount = new int[] { 1, 7, 4, 2, 0, 8}; //성냥개비 2,3,4,5,6,7개를 통해 만들 수 있는 가장 작은 수

        Arrays.fill(D, Long.MAX_VALUE);
        D[2] = 1;
        D[3] = 7;
        D[4] = 4;
        D[5] = 2;
        D[6] = 6;
        D[7] = 8;
        D[8] = 10; //점화식에서 8에 대해서 계산을 하게 되면 음수값이 들어가게 되므로 사전에 초기화
        for(int i = 9 ; i <=100; i++){//최솟값 구하기
            for(int j = 2 ; j <= 7; j++){
                D[i] = Math.min(D[i], D[i-j] * 10 + minCount[j-2]);
            }
        }

        for(int testCase = 1; testCase <= T; testCase++){
            N = Integer.parseInt(in.readLine());
            String maxValue = getMax(N);

            System.out.println(D[N] + " " + maxValue);
        }
    }

    private static String getMax(int N){
        if(N % 2 == 0){
            String result = "";
            for(int i = 0 ; i < N /2 ;i++){
                result = result + "1";
            }

            return result;
        }
        else {
            String result = "";
            for(int i = 0 ; i < N /2 ;i++){
                result = result + "1";
            }

            return "7" + result.substring(1);
        }
    }
}
