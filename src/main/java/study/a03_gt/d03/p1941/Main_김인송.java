import java.io.*;
import java.util.*;

public class Main_김인송 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static char[][] map = new char[5][5];
    static int[][] b = new int[7][2];
    static int[][] a = new int[][]{
            {0,0},{0,1},{0,2},{0,3},{0,4},
            {1,0},{1,1},{1,2},{1,3},{1,4},
            {2,0},{2,1},{2,2},{2,3},{2,4},
            {3,0},{3,1},{3,2},{3,3},{3,4},
            {4,0},{4,1},{4,2},{4,3},{4,4}
    };
    static int ans = 0;

    static boolean inRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    static void bfs(int r, int c) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        boolean[][] isSelected = new boolean[5][5];
        boolean[][] visited = new boolean[5][5];

        for (int i = 0; i < 7; i++) {
            isSelected[b[i][0]][b[i][1]] = true;
        }

        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        int connectedCount = 1;
        while (!queue.isEmpty()) {
            int[] rc = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = rc[0] + dr[i];
                int nc = rc[1] + dc[i];

                if (inRange(nr, nc) && isSelected[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                    connectedCount++;
                }
            }
        }
        if(connectedCount == 7) ans++;
    }

    static void comb(int cnt, int start){
        if(cnt == 7) {
            int y = 0;
            for(int i = 0; i < 7; i++){
                if(map[b[i][0]][b[i][1]] == 'Y') y++;
            }
            if(y < 4) {
                bfs(b[0][0],b[0][1]);
            }

            return;
        }
        for(int i = start; i < 25; i++) {
            b[cnt] = a[i];
            comb(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for(int i = 0; i < 5; i++) {
            String[] s = br.readLine().split("");
            for(int j = 0; j < 5; j++) {
                map[i][j] = s[j].charAt(0);
            }
        }

        comb(0, 0);

        System.out.println(ans);
    }
}
