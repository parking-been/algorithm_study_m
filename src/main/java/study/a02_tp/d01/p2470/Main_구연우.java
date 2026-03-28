package main.java.study.a02_tp.d01.p2470;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_구연우 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] potions = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            potions[i] = Integer.parseInt(st.nextToken());
        }

        potionSum(N, potions);
        System.out.print(potion1+" "+potion2);

    }

    static int potion1, potion2;

    static void potionSum(int N, int[] potions) {
        // 배열 정렬
        Arrays.sort(potions);

        int left = 0;
        int right = N - 1;

        // 절대값의 최솟값 초기화
        int minAbsSum = 2000000000;

        // 두 포인터가 만날 때까지 진행
        while (left < right) {
            int sum = potions[left] + potions[right];
            int absSum = Math.abs(sum);

            if (absSum < minAbsSum) {
                minAbsSum = absSum;
                potion1 = potions[left];
                potion2 = potions[right];
            }

            // 합이 0보다 작으면 더 큰 수를 더해야 함 -> left를 오른쪽으로
            if (sum < 0) {
                left++;
            }
            // 합이 0보다 크면 더 작은 수를 더해야 함 -> right를 왼쪽으로
            else if (sum > 0) {
                right--;
            }
            // 합이 0이면 종료
            else {
                break;
            }
        }
    }

}

