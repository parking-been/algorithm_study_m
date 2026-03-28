package AlgoStudy.algorithm_study.src.main.java.study.a04_greedy.d01.p1946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_안희수 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int cnt = 1;   //  늘 2이상 (서류, 면접에서 각각 1위인 사람) -> 서류 1등 카운트

            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {  // 입력
                StringTokenizer st = new StringTokenizer(br.readLine());
                list.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            }list.sort((a, b) -> Integer.compare(a[0], b[0]));   // 서류 기준 내림차순 정렬
//			list.forEach(arr -> System.out.println(Arrays.toString(arr)));

            int cur = list.get(0)[1];   // 서류 1등은
            int min = cur;
//			System.out.println(cur);
            for (int i = 1; i < N; i++) {  // 각 사람이 뽑힐 수 있는지 탐색
                cur = list.get(i)[1];   // 현재 사람의 면접 점수
                if (cur < min) ++cnt;
                else continue;
                min = cur;
//				System.out.println(min + " " + cur + " " + cnt);
            }

            System.out.println(cnt);
        }
    }
}