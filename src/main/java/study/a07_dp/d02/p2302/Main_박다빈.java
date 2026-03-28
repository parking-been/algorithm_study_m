package main.java.study.a07_dp.d02.p2302;
import java.io.*;
import java.util.*;

/*
 * 

<인사이트>
1. 현재좌석이 vip가 아닐경우

1-1. 이전꺼가 vip 가 아닐 경우 dp[x-1] + dp[x-2]

1-2. 이전꺼가 vip일경우 dp[x-1]

2. 현재 좌석이 vip 일 경우 dp[x-1]

dp로 쉽게 풀린다
 
https://steady-coding.tistory.com/178
vip 좌석 기준으로 끊은 후 각각 에 대한 경우의 수를 구한후 곱해준다. 
dp 인 접근은 비슷하나 끈은후 곱한다는 인사이트가 참신했다. 

 * 
 * */

public class Main_박다빈 {
    static int N;
    static int M;
    static int arr[];
    static int dp[];
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1];
        for(int i=0;i<M;i++) {
            arr[Integer.parseInt(br.readLine())] = 1;
        }

        dp[0] = 1;
        dp[1] = 1;

        for(int i=2;i<=N;i++) {
            if(arr[i]!=1) {
                //white
                if(i-2>=0) {
                    if(arr[i-1]!=1) {
                        //white
                        dp[i] = dp[i-2] + dp[i-1];
                    }else {
                        dp[i] = dp[i-1];
                    }

                }else {
                    dp[i] = 2; //예외 처리
                }
            }else {
                //black : vip
                dp[i] = dp[i-1];

            }
        }

        //System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);




    }

}

