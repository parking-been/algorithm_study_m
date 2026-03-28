package main.java.study.a04_greedy.d03.p2812;
import java.io.*;
import java.util.*;

/*
 * 주유소문제와 같은 원리
 * 1. 앞에서 부터 현재 수보다 이전에 작은 수가 나오면 그 수들 부터 제거
 * 2. 이후 뒤에서 부터 제거
 *
 * 막혔던 부분
 * deque 에서 pollFrist , pollLast, offerLast , peekLast 등의 메서드를 잘못씀
 *
 * 아이디에이션 부분을 길게 (30분정도) 가져갔는데 도움이 된거 같다.
 *
 * */

public class Main_박다빈 {
    static char[] arr;
    static boolean[] delete;
    static int K;
    static int N;
    public static void main(String[] args)throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = br.readLine().toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        int top=0;

        for (int i = 0; i < N; i++) {
            char c = arr[i];

            // c가 더 크면, 앞의 작은 것들을 즉시 연쇄 삭제
            while (K > 0 && !stack.isEmpty() && stack.peekLast() < c) {
                stack.pollLast();
                K--;
            }

            stack.add(c); // push
        }

        // 아직 덜 지웠으면 뒤에서 제거
        while (K-- > 0) stack.pollLast();

        // 결과는 앞에서부터 N-K개
        int need = N - K;
        while (!stack.isEmpty()) sb.append(stack.pollFirst());

        System.out.println(sb);

    }


}
