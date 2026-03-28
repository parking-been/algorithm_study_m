package main.java.study.a02_tp.d02.p7453;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_채유빈 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int size = n * n;
        long[] sumAB = new long[size];
        long[] sumCD = new long[size];

        // A+B, C+D 합 배열 만들기
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumAB[idx] = (long) A[i] + B[j];
                sumCD[idx] = (long) C[i] + D[j];
                idx++;
            }
        }

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        long count = 0;
        int p1 = 0;
        int p2 = size - 1;

        while (p1 < size && p2 >= 0) {
            long sum = sumAB[p1] + sumCD[p2];

            if (sum == 0) {
                long valA = sumAB[p1];
                long valB = sumCD[p2];

                long cntA = 0;  // sumAB[p1] 값이 몇 번 연속 등장하는지 계산
                while (p1 < size && sumAB[p1] == valA) {
                    cntA++;
                    p1++;
                }

                long cntB = 0;
                while (p2 >= 0 && sumCD[p2] == valB) {
                    cntB++;
                    p2--;
                }

                count += cntA * cntB;
            } else if (sum < 0) {
                p1++;
            } else { // sum > 0
                p2--;
            }
        }

        System.out.println(count);
        br.close();
    }
}
