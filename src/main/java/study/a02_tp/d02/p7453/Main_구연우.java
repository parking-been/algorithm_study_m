package main.java.study.a02_tp.d02.p7453;

import java.io.*;
import java.util.*;

public class Main_구연우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        // A와 B 합의 모든 경우의 수 저장
        int[] ABSum = new int[N*N];
        int idx = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                ABSum[idx++] = A[i] + B[j];
            }
        }

        // C와 D 합의 모든 경우의 수 저장
        int[] CDSum = new int[N*N];
        idx = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                CDSum[idx++] = C[i] + D[j];
            }
        }

        // AB합, CD합 배열 정렬
        Arrays.sort(ABSum);
        Arrays.sort(CDSum);

        // 투 포인터로 합이 0인 쌍의 개수 구하기
        long cnt = 0;
        int left = 0;
        int right = N*N - 1;

        while (left<N*N && right>=0) {
            int valAB = ABSum[left];
            int valCD = CDSum[right];
            int total = valAB + valCD;

            if (total == 0) {
                // 중복된 값 개수 체크
                long ABcnt = 0;
                long CDcnt = 0;

                while (left < N * N && ABSum[left] == valAB) {
                    ABcnt++;
                    left++;
                }
                while (right >= 0 && CDSum[right] == valCD) {
                    CDcnt++;
                    right--;
                }
                cnt += ABcnt * CDcnt;
            } else if (total<0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(cnt);
    }
}
