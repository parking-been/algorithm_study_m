package main.java.study.a02_tp.d01.p2531;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_구연우 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호 (보너스)
        int[] sushi = new int[N];

        for (int i=0; i<N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        // 원형 구조 계산을 위해 원래 배열 끝에 k-1만큼 배열을 복사해서 붙이기
        int[] arr = new int[N+k-1];
        System.arraycopy(sushi, 0, arr, 0, N);
        for (int i=0; i<k-1; i++) {
            arr[N+i] = sushi[i];
        }

        System.out.print(maxDiffSushi(N, d, k, c, arr));

    }

    static int maxDiffSushi(int N, int d, int k, int c, int[] arr) {

        int[] count = new int[d+1];
        int total = 0, cur = 0, left = 0, right = 0;

        // 초기 윈도우 생성
        for (int i=0; i<k; i++) {
            if (count[arr[i]]==0) {
                total++;
            }
            count[arr[i]]++;
        }

        // 초기 윈도우 기준 최댓값 설정
        int maxSushi = (count[c] == 0) ? total + 1 : total;

        // 윈도우 범위 옮겨가며 최댓값 찾기
        for (int j=0; j<N-1; j++) {
            // 앞쪽, 뒤쪽 초밥 종류 구하기
            left = arr[j];
            right = arr[j+k];

            // 앞쪽 초밥 빼기
            count[left]--;
            if (count[left] == 0) total--;

            // 뒤쪽 초밥 더하기
            if (count[right] == 0) total++;
            count[right]++;

            // 가짓수 계산
            int curTotal = (count[c] == 0) ? total + 1 : total;
            if (curTotal>maxSushi) maxSushi = curTotal;
        }

        return maxSushi;
    }

}
