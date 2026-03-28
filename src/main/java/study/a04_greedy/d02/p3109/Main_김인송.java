package main.java.study.a04_greedy.d02.p3109;

import java.io.*;
import java.util.*;

public class Main_김인송 {
    static int R, C, ans;
    static int[] dr = {-1, 0, 1};
    static int[] dc =  {1, 1, 1};
    static boolean[][] v;
    static char[][] map;

    static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
    static boolean dfs(int r, int c) {
        v[r][c] = true;
        if(c == C - 1) {
            ans++;
            return true;
        }
        for(int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(inRange(nr, nc) && !v[nr][nc] && map[nr][nc] == '.') {
                if(dfs(nr,nc)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        v = new boolean[R][C];

        map = new char[R][C];

        for(int i = 0; i < R; i++) {
            String s = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < R; i++) {
            dfs(i,0);
        }

        System.out.println(ans);
    }
}


