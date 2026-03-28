package main.java.study.a03_gt.d01.p2667;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main_구연우 {

    static int n;
    static int[][] matrix;
    static boolean[][] visited;
    static int houseCnt; // 현재 단지의 집 수
    static ArrayList<Integer> results = new ArrayList<>(); // 단지별 집 수

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        visited = new boolean[n][n];

        // 지도 입력 받기
        for (int i=0; i<n; i++) {
            String str = br.readLine();
            for (int j=0; j<n; j++) {
                matrix[i][j] = str.charAt(j)-'0';
            }
        }

        // dfs로 단지와 집의 수 계산
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                // 방문하지 않은 집이면 새로운 단지를 만들고 dfs 실행
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    houseCnt = 0; // 새 단지를 시작할 때마다 집 개수 초기화
                    dfs(i, j);
                    results.add(houseCnt); // 단지 탐색이 종료되면 집 수를 저장
                }
            }
        }

        // 단지의 수 출력
        System.out.println(results.size());

        // 단지별 집의 수를 오름차순으로 출력
        Collections.sort(results);
        for (int cnt : results) {
            System.out.println(cnt);
        }
    }



    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    // dfs로 한 구간의 연결된 단지 수를 체크
    static void dfs(int x, int y) {
        visited[x][y] = true;
        houseCnt++; // 탐색을 시작한 집 카운트

        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx>=0 && nx<n && ny>=0 && ny<n) {
                // 방문하지 않은 집이면 dfs 실행
                if (!visited[nx][ny] && matrix[nx][ny]==1) dfs(nx, ny);
            }

        }
    }
}
