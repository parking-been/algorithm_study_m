package main.java.study.a03_gt.d02.p18513;

import java.io.*;
import java.util.*;

/*
 * 샘터 양옆을 기준으로 뻗어나가며, 비어있을 경우 집을 세워야한다.
 * => greedy로 할 수도 있겠지만, 이렇게 되면 이미 세워진 집에 대해 edge case가 많이 발생한다.
 *
 * 뻗어나간다 + 이미 세워진 경우 확장을 멈춘다 => bfs 저격
 *
 * 중간 트러블 슈팅
 * 1. long 문제... (문제 조건을 잘 확인하자)
 *
 *
 * */


public class Main_박다빈 {
    static long[] arr;
    static int N;
    static int K;
    static int[] move = {-1,1};
    static Set<Long> store;
    static long sum;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        //샘터의 위치
        arr = new long[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++) {
            arr[i] = Long.parseLong(st2.nextToken());
        }

        bfs();
        System.out.println(sum);

    }

    public static void bfs() {
        Queue<long[]> queue = new ArrayDeque<>();
        store = new HashSet<>();
        sum=0;
        int count=0;
        for(long e : arr) {
            queue.add(new long[] {e,e});
            store.add(e);
        }

        while(!queue.isEmpty()) {
            long[] cur = queue.poll();
            long root = cur[1];
            long x = cur[0];
            for(long m : move) {
                long next = x+ m;
                if(!store.contains(next)) {
                    store.add(next);
                    sum+=Math.abs(root-next);
                    queue.add(new long[] {next,root});
                    count++;
                    if(count==K) {
                        return;
                    }
                }

            }
        }
    }

}
