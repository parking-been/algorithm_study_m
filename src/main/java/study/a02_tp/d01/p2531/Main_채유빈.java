package main.java.study.a02_tp.d01.p2531;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 1. 회전 초밥 종류를 전부 배열에 입력
 * 2. 0번부터 k개 LinkedList에 담기
 * 3. 회전 방향으로 한 칸씩 탐색하면서 LinkedList에 넣고 빼기 (슬라이딩 윈도우)
 * 4. Set으로 변환해 사이즈 확인(중복 제거) + 쿠폰 포함 여부 확인하면서 최댓값 갱신
 */
public class Main_채유빈 {
    public static int getSushiTypeCount(Set<Integer> set, int coupon) {
        return set.size() + (set.contains(coupon) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Integer.parseInt(st.nextToken());  // 초밥의 가짓수는 사용하지 않으므로 변수에 담지 않음
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // 초밥 정보를 담을 배열
        int[] sushi = new int[n];
        
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        // 초기 상태 생성
        LinkedList<Integer> list = new LinkedList<>();
        
        for (int i = 0; i < k; i++) {
            list.add(sushi[i]);
        }
        
        // 중복 제거(초밥 종류의 수를 구해야 하므로)를 위해 Set 사용
        int result = getSushiTypeCount(new HashSet<>(list), c);

        for (int i = 1; i < n; i++) {
            int nextIndex = (i + k - 1) % n;

            list.add(sushi[nextIndex]);
            list.remove(0);

            result = Math.max(result, getSushiTypeCount(new HashSet<>(list), c));
        }

        System.out.println(result);
        br.close();
    }
}
