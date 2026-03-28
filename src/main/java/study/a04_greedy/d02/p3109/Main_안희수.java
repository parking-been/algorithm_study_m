package AlgoStudy.algorithm_study.src.main.java.study.a04_greedy.d02.p3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_안희수 {
    static int totalCnt, R, C;
    static String map[][];
    static int[] dr = {-1, 0, 1}, dc = {1, 1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new String[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().split("");
        }

        totalCnt = 0;

        for (int i = 0; i < R; i++) {
            int r = i; int c = 0;  // 시작점 초기화(0열의 각 행)
            flag = false;   // 매 시작점마다 초기화
            dfs(r, c);
        }

        System.out.println(totalCnt);
    }

    static boolean flag = false;  // 아직 찾지 못함
    public static void dfs(int r, int c) {
        if (c>=C-1) {
            totalCnt++;
            flag = true;   // 찾음
            return;
        }

        for (int d = 0; d < 3; d++) {  // 3방 탐색 (항상 오른쪽 위부터 탐색 -> 여기서 탐색 완료했으면 다른 방향에서 도달 가능해도 탐색하면 안됨)
            if(flag) return;   // 만약 이전 방향에서 길을 찾았을 경우, 다음 방향 탐색하지 않음
            int nr = r + dr[d];
            int nc = c + dc[d];

            if ((nr>=0 && nc>=0 && nr<R && nc<C) && map[nr][nc].equals(".")) {   // 범위 체크
                map[nr][nc] = "x";         // 방문처리
                dfs(nr, nc);
            }
        }
    }
}