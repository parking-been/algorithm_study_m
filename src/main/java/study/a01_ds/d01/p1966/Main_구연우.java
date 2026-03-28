package main.java.study.a01_ds.d01.p1966;

import java.util.*;

public class Main_구연우 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            LinkedList<int[]> q = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                q.offer(new int[] {i, sc.nextInt()});
            }

            int count = 0;
            while (!q.isEmpty()) {
                int[] current = q.poll();
                boolean isMax = true;

                for (int i = 0; i < q.size(); i++) {
                    if (q.get(i)[1] > current[1]) {
                        isMax = false;
                        break;
                    }
                }

                if (isMax) {
                    count++;
                    if (current[0] == M) {
                        System.out.println(count);
                        break;
                    }
                } else {
                    q.offer(current);
                }
            }
        }
    }
}