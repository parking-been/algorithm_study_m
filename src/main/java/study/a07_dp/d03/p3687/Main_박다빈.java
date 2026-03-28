package main.java.study.a07_dp.d03.p3687;
import java.io.*;
import java.util.*;

/*

<인사이트>
작은 값 구할 때
for문을 돌며
dp[x] = dp[x-i] + (i개 성냥으로 만들 수 있는 한 자리 숫자)

큰 값 구할 때
짝수개면 : 1111...
홀수개면 : 7 + 111...

<어려웠던 점>
Long으로 두고 풀었는데 에러가 떴다.
생각해보니 10**18 을 훨씬 넘을 수도 있을 것 같아 String으로 바꿨다.
String과 String간의 대소 비교 부분에서 시간이 걸렸다.

dp[x].compareTo("-1")
String간의 비교시, 이런식으로 비교한다는 사실을 잊지말자!

*/

public class Main_박다빈 {
    static int T;
    static int N;
    //2~7까지만 사용할 예정
    //2획 : 1
    //3획 : 7 ...
    static String[] mappingNumber = {"0","0","1","7","4","2","0","8"};
    //작은수 구하기에 사용될 dp
    static String[] dp;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        //초기셋팅
        dp = new String[101];
        Arrays.fill(dp, "-1");
        dp[2] = "1";
        dp[3] = "7";
        dp[4] = "4";
        dp[5] = "2";
        dp[6] = "6";
        dp[7] = "8";

        T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            N = Integer.parseInt(br.readLine());

            sb.append(dping(N)).append(" ").append(big(N)).append("\n");

        }
        System.out.println(sb);


    }

    public static String big(int x) {

        StringBuilder sb2 = new StringBuilder();

        for(int i=0;i<(int)x/2-1;i++) {
            sb2.append("1");
        }

        String s;
        if (x%2==1) {
            //홀수면
            s = "7" + sb2.toString();
        } else {
            s = "1" + sb2.toString();
        }

        //Long result = Long.valueOf(s);

        return s;
    }

    public static String dping(int x) {

        if (dp[x].compareTo("-1")!=0) return dp[x];
        for(int i=2;i<=7;i++) {

            if(x-i<2) break;
            String tmp = dping(x-i) + mappingNumber[i];
            if(isSmaller(tmp, dp[x])) {
                dp[x] = tmp;
            }
            //dp[x] = Math.min(dping(x-i) + mappingNumber[i], dp[x]);
        }

        return dp[x];
    }

    //문자열 비교법!
    public static boolean isSmaller(String a, String b) {

        if(b.compareTo("-1")==0) return true;

        if(a.length()!=b.length()) {
            return a.length() < b.length();
        }
        return a.compareTo(b)<0;
    }
}
