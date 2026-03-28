package AlgoStudy.algorithm_study.src.main.java.study.a04_greedy.d01.p13305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_안희수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dis = new long[N-1];
        int[] store = new int[N];
        long price = 0;   // 최대 10억
        int minIdx = N;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) {   // 거리 입력
            dis[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {   // 주유소 입력
            store[i] = Integer.parseInt(st.nextToken());
        }

        List<int[]> list = new ArrayList<>();   // (주유가격, 가게 인덱스)
        for (int i = 0; i < N-1; i++) {   // 마지막 주유소는 넣지 않음(필요없음)
            list.add(new int[] {store[i], i});
        }

        // 오름차순 정렬 : 주유가격을 첫번째 정렬 기준으로 삼고 -> 같은 경우는 인덱스 기준 정렬
        list.sort((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
//		list.forEach(arr -> System.out.println(Arrays.toString(arr)));  // 정렬 확인

        for (int i = 0; i < N-1; i++) {  // 각 주유소
            int[] cur = list.get(i);
            long curPrice = cur[0];
            int curIdx = cur[1];
            long curDis = dis[curIdx];
//			System.out.println(curIdx + " " + curPrice + " "  + curDis);

            if (minIdx < curIdx) continue;   // 이미 더 작은 인덱스에서 주유했을 경우
            for (int j = curIdx; j < N-1; j++) {  // 최소 가격에서 이후 인덱스 것들도 두 구매
                price += (dis[j] * curPrice);
                dis[j] = 0;  //이후 거리 모두 구매
                minIdx = Math.min(minIdx, curIdx);
//				System.out.println("price:" + price + ", dis: " + Arrays.toString(dis) + ", minIdx: " + minIdx);
            }
        }

        System.out.println(price);

    }
}