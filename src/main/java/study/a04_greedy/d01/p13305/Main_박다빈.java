package main.java.study.a04_greedy.d01.p13305;

import java.io.*;
import java.util.*;


/*
 * <인사이트>
 * 예시를 들어보며 풀이 법을 생각했다.
 *
 * 1 2 3 4 5
 * 와 같이 있으면, 1번에서 풀충전하고 가야한다
 *
 * 2 3 1 4 5
 * 와 같이 있으면, 2번에서 출발해서 1번에 멈췄다가 1번에서 충전하고 가야한다.
 *
 * => 결국 멈춰서 충전을 하는 곳. 이전 가격보다 낮은 곳.
 * => greedy, 후회하지 않는다. (명확함)
 *
 *
 * */


public class Main_박다빈 {

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dis = new long[N-1];
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        //dis 받기
        for(int i=0;i<N-1;i++) {
            dis[i] = Integer.parseInt(st.nextToken());
        }
        //cost 받기
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        int star = 0;
        int cur = 1;
        long totalSum = 0;
        long tmp = 0;
        while(true) {
            if(cur>=N) break;
            //누적부터
            tmp+=dis[cur-1];

            if(arr[star] > arr[cur]) {
                //이동해야지
                totalSum += arr[star]*tmp;
                star = cur;
                tmp=0;
            }
            cur++;
            if(cur==N) {
                //누적되어있던 것 한번에 계산
                totalSum += arr[star]*tmp;
            }

        }
        System.out.println(totalSum);
    }

}