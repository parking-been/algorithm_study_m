package AlgoStudy.algorithm_study.src.main.java.study.a04_greedy.d03.p2812;

import java.io.*;
import java.util.*;

public class Main_안희수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String number = br.readLine();

        Stack<Character> stack = new Stack<>();

        int deleteCount = K;
        for (int i = 0; i < N; i++) {
            char now = number.charAt(i);
            // 스택이 비어있지 않고, 지울 기회가 남았고, 스택 맨 위 숫자가 현재보다 작으면 제거
            while (!stack.isEmpty() && deleteCount > 0 && stack.peek() < now) {
                stack.pop();
                deleteCount--;
            }
            stack.push(now);
        }

        // 결과 출력 (K개를 다 못 지운 경우 앞쪽에서 N-K개만 취함)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N - K; i++) {
            sb.append(stack.get(i));
        }
        System.out.println(sb.toString());
    }
}