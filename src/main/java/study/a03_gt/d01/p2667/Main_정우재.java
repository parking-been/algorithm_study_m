package main.java.study.a03_gt.d01.p2667;

import java.util.*;
import java.io.*;

//집을 하나씩 돌면서 연결된 집들간에는 하나의 단지로 묶는다.
//하나의 단지를 생성할 때는 연결된 집들 모두를 하나로 묶은 뒤에 단지를 생성해야 하므로 DFS를 이용한다
//연결된 집이 1개도 없음에도 혼자서 단지를 구성할 수 있다
//각 집들을 돌면서, 단지를 구성하지 못한 집과 단지를 구성한다
public class Main_정우재 {
    private static int count = 0;
    //상, 하, 좌, 우
    private static int[] dr = {-1, 1, 0 , 0};
    private static int[] dc = {0, 0, -1 , 1};
    private static boolean[][] isVisited;
    private static int[][] map;
    private static int N;

    public static void main(String[] args) throws IOException {
       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(in.readLine(), " ");

       N = Integer.parseInt(st.nextToken());
       map = new int[N][N];
       isVisited = new boolean[N][N];

       for(int row = 0 ; row < N ; row++){
           st = new StringTokenizer(in.readLine());
           char[] input = st.nextToken().toCharArray();
           for (int col = 0 ; col < N ; col++) {
               map[row][col] = input[col] - '0';
           }
       }

        ArrayList<Integer> groups = new ArrayList<>();
        for(int row = 0 ; row < N ; row++){
            for (int col = 0 ; col < N ; col++) {
                if(map[row][col] == 1 && !isVisited[row][col]) {
                    dfs(row, col);
                    groups.add(count);
                    count = 0;
                }
            }
        }

        System.out.println(groups.size());
        Collections.sort(groups);
        for (Integer group : groups) {
            System.out.println(group);
        }
    }

    private static void dfs(int row, int col) {
        isVisited[row][col] = true;
        count++;

        for(int i = 0;  i < 4 ; i++){
            int nearRow = row + dr[i];
            int nearCol = col + dc[i];
            if (!isIn(nearRow, nearCol))
                continue;
            if(map[nearRow][nearCol] == 1 && !isVisited[nearRow][nearCol])
                dfs(nearRow, nearCol);
        }

    }

    private static boolean isIn(int nearRow, int nearCol) {
        return ( 0 <= nearRow && nearRow < N && 0 <= nearCol && nearCol <N);
    }
}
