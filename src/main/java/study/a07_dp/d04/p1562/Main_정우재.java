package main.java.study.a07_dp.d04.p1562;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int[][][] dp = new int[101][10][1024];

        for(int i = 1; i <=9 ; i++){ //초기값 설정. 길이가 1일 때 끝나는 계단의 번호가 1부터 9인 경우는 각각 1개씩
            dp[1][i][1 << i] = 1;
        }
        dp[1][0][1] = 0;  //초기값 설정. 길이가 1일 때 끝나는 계단의 번호가 0인 경우는 없으므로 0

        for(int i = 2 ; i <=100 ; i++){
            for(int k = 1; k < 1024 ;k++){
                dp[i][0][k | 1] = (dp[i][0][k | 1]+ dp[i-1][1][k]) % 1_000_000_000;
                dp[i][9][k | 1 << 9] = (dp[i][9][k | 1 << 9] + dp[i-1][8][k]) % 1_000_000_000;
                for(int j = 1; j <= 8 ; j++){
                    dp[i][j][k | 1 << j] = (dp[i][j][k | 1 << j] + dp[i-1][j-1][k] + dp[i-1][j+1][k]) % 1_000_000_000;
                }
            }
        }
        int result = 0;
        for(int j = 0 ; j <= 9 ; j++)
            result = (result + dp[N][j][1023]) % 1_000_000_000 ;

        System.out.println(result);
    }
}