package main.java.study.a09_si.d02.p2638;

import java.io.*;
import java.util.*;

/*
기능 (크게 2가지)
- 녹일 치즈 선정
- 치즈 녹이기

1. 녹일 치즈 선정 (Idea : 공기에 대해서 bfs를 수행하고, 해당 공기가 접촉하고 있는 치즈를 찾자!)
- 맨 가장자리는 치즈가 놓이지 않으니 (0, 0) 은 항상 공기.
- 따라서 매 시간마다 (0, 0) 에서 bfs를 수행한다. 이 때 만약, 공기 주변에 치즈가 있는 경우, 해당 치즈의 공기 접촉 count를 +1  (air[][] : 치즈의 공기 접촉면의 갯수를 담는 2차원 배열)

2. 치즈 녹이기
- map을 순회하며 만약 공기 접촉면이 2이상인 부분들은 모두 녹이기(공기로 치환)


---
시간 복잡도 계산
- 결국 모든 치즈는 모서리 기준 각 시간마다 가로, 세로 각각 최소한 2개씩은 줄어들게 되어있다.
- 최대 치즈 사이즈는 98 x 98 이므로, 치즈가 줄어드는데 걸리는 최대 시간은 98 /2 = 49(시간)이다.

시간복잡도 = 49 * ( (V+ E) + (N *M))  = 49 *  ( (10000 + 4 * 10000) + 100 *100) = 약 300만 (OK)
 */

public class Main_정우재 {
    static int[][] map;
    static int[][] air;
    static int N, M;
    static final int CHEEZE = 1;
    static int[][] delta = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0 ; i < N ;i++){
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while(true){
            bfs(); //공기에 대해서 bfs 수행
            boolean isDone = melt(); //치즈 녹이기

            if(isDone)
                break;
            time++;
        }
        System.out.println(time);
    }

    public static void bfs(){ // 치즈를 둘러싸는 공기를 bfs하며 찾고, 만약 해당 공기 주변에 치즈가 있는 경우 공기 접촉면의 갯수 1증가
        boolean[][] isVisited=  new boolean[N][M];
        air = new int[N][M]; //air[][] 배열은 매 시간마다 새롭게 초기화

        Queue<int[]> queue = new ArrayDeque<>();
        isVisited[0][0] = true;
        queue.add(new int[] {0, 0});

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            for(int i = 0 ; i < 4 ; i++){
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M ) continue;
                if(map[nr][nc] == CHEEZE){ //만약 주변에 치즈가 있는 경우 해당 치즈의 공기 접촉면 갯수 +1
                    air[nr][nc]++;
                    continue;
                }

                if(!isVisited[nr][nc]){ // 공기인경우
                    isVisited[nr][nc] = true;
                    queue.add(new int[] {nr, nc});
                }
            }
        }
    }

    private static boolean melt() { //air[][] 배열을 순회하며 공기 접촉면이 2개 이상인 치즈를 녹이는 함수
        int count = 0 ;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M; j++){
                if(air[i][j] >= 2){ //공기 접촉면이 2개 이상이면 녹이기 (공기로 치환)
                    map[i][j] = 0;
                    count++;
                }
            }
        }

        return count == 0;
    }
}