package main.java.study.a01_ds.d01.p1966;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main_채유빈 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());  // 문서의 개수
            int M = Integer.parseInt(st.nextToken());  // 몇 번째로 인쇄되었는지 궁금한 문서

            // [문서 인덱스, 중요도]를 담을 큐
            Queue <int[]> q = new LinkedList<>();

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int importance = Integer.parseInt(st.nextToken());
                q.offer(new int[]{i, importance});
            }

            int result = solve(N, M, q);

            sb.append(result).append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    private static int solve(int N, int M, Queue<int[]> q) {
        int printOrder = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();  // 첫 번째 문서 꺼내기
            boolean printable = true;

            for (int[] doc : q) {
                if (current[1] < doc[1]) {  // 만약 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 있다면
                    printable = false;  // 인쇄 불가 처리
                    break;
                }
            }

            if (printable) {
                printOrder++;

                if (current[0] == M) {
                    return printOrder;
                }
            } else {
                q.offer(current);  // 인쇄할 수 없는 경우 가장 뒤로 다시 추가
            }
        }

        return -1;
    }
}