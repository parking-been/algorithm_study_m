package main.java.study.a02_tp.d01.p2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_채유빈 {
    public static int nextInt(StreamTokenizer st) throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        int n = nextInt(st);

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = nextInt(st);
        }
        
        Arrays.sort(arr);  // 오름차순 정렬

        int p1 = 0;
        int p2 = n - 1;
        int minSum = Integer.MAX_VALUE;
        int[] minSumArr = new int[2];

        while (p1 < p2) {
            int sumAbs = Math.abs(arr[p1] + arr[p2]);
            int sum = arr[p1] + arr[p2];

            if (sumAbs < minSum) {
                minSum = sumAbs;
                minSumArr[0] = arr[p1];
                minSumArr[1] = arr[p2];
            }

            if (sum > 0) {
                p2--;
            } else if (sum < 0) {
                p1++;
            } else {
                minSumArr[0] = arr[p1];
                minSumArr[1] = arr[p2];
                break;
            }
        }

        System.out.println(minSumArr[0] + " " + minSumArr[1]);
        br.close();
    }
}
