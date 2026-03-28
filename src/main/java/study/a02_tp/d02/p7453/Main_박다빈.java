package main.java.study.a02_tp.d02.p7453;

import java.io.*;
import java.util.*;

/*
 * 백준 7453
 * 처음 봤을때, N*N*N*N 의 시간 복잡도에서
 * AB, CD 를 만들고 two pointer를 사용하며  N*N 시간 복잡도로 줄이는 것 까지는 생각을 했다.
 * 다만 완벽하게 구현을 못한 몇가지 이유가 있다.
 * 1. int와 long의 차이
 * 	- 파이썬에서 쓰던 습관대로 (파이썬은 자료형의 크기를 굳이 생각하지 않아도 된다.) int로 써버렸는데, 문제에 맞지 않는 타입이었다.
 * 2. AB, CD 의 sum 이 0일 경우 중복을 고려하지 않고 계산했다.
 *
 * 각각에 대한 정리
 * 1. int와 long의 범위 차이
 * 	- int : 약 2 x 10^9
 * 	- long : 약 2 x 10^18
 *  우리 문제의 경우 최대 경우의 수가 약 10^12 이므로 long을 썼어야했다.
 *
 * 2. AB, CD 배열안에 생기는 중복 값 처리
 *  - tmp는 0일때 만약 AB 안의 해당 값이 여러개 연속해있고, CD안의 해당값이 여러개 연속해 있다면 다 count를 해줘야할 것이다. 중복이 일어날 수 있겠다라는 생각을 배제하고 푼 것 같다.
 *  - 문제에는 중복이라는 단어가 없었지만 test case에서는 중복이 존재하는 예시가 보여지긴 했다.
 *  - 테스트 케이스를 잘 보자
 *
 * 3. & 과 && 의 차이
 * - 생각없이 썼는데 생각보다 중요한 부분이다. 나처럼 몰랐다면 어디 정리해두자
 * - && : 앞이 false면 뒤에는 아예 실행을 안함
 * - & : 모든 조건을 무조건 평가
 * - 이전에 미로 찾기 같은 문제에서 (nx>N & !visited[nx]) 이런식으로 코드를 짰는데, nx 에서 outOfIndex 에러가 나왔다.
 * - 당시에는 그냥 if문 두개를 박아 없앴는데, &&를 썼다면 해결될 문제였다. 웬만해서 코테에서는 &&를 쓰자
 *
 *
 * 참고 블로그
 * - 어떻게던 풀긴했지만 다른 블로그 어떻게 풀었는지 대강 봤다.
 * - 크게 다르게 구현한 부분이 없어서 넘어가도록 하자
 * https://loosie.tistory.com/553
 *
 * */

public class Main_박다빈 {

    static int N;
    static long count = 0;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] AB = new int[N*N];
        int[] CD = new int[N*N];

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                AB[i*N+j] = A[i]+B[j];
                CD[i*N+j] = C[i]+D[j];
            }
        }


        Arrays.sort(AB);
        Arrays.sort(CD);

        solution(AB, CD);


        System.out.println(count);

    }

    public static void solution(int[] X, int[] Y){
        int s=0; //AB
        int e= N*N-1; //CD

        while(e>-1 && s<N*N) {
            int tmp = X[s] + Y[e];
            // 중복에 대해 아예 생각을 안했다. 
            // int long 차이에 대해 생각을 안했다...
            if(tmp==0) {
                long xc = 0;
                long yc = 0;
                int tX = X[s];
                int tY = Y[e];
                while(s<N*N && tX==X[s]) {
                    s++;
                    xc++;
                }
                while(e>-1 && tY==Y[e]) {
                    e--;
                    yc++;
                }
                count+=xc*yc;

            }
            else if(tmp<0) {
                s++;
            }
            else if(tmp>0) {
                e--;
            }
        }



    }

}