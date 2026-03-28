package main.java.study.a04_greedy.d02.p3109;

import java.io.*;
import java.util.*;


/*
 *
 * 생각 플로우
 *
 * 처음에는 첫번째 test case결과가 왜 2가 나오는지 이해 못함
 * => 이해함
 *
 * 1. 봤을때, 이거 결국 경로를 찾아가는거니까 dfs아닌가? 싶었음
 * dfs 를 풀 때
 * - 재귀형
 * - 비재귀형
 * 이있는데, 최종 경로를 뽑는데는 재귀형이 올바른 방식임
 * 비재귀형의 경우 틀린 경로로 갔을 때 visited[] =false를 안해줘서 별로라고 생각함 -> 하지만... 3번
 * 비재귀형은 언제? : 깊이가 매우 깊을 가능성, 엄청 깊을 경우 ...
 *
 * 2. 순서 고정 - 그리디
 * 첫번째 행에서 출발하면 최대한 앞쪽의 행에 도착하는것이 좋음
 * -> 그래야 뒤에 행에서 더 많은 파이프를 뽑아낼 수 있음.
 *
 * 막혔던 곳
 * 3. 시간 초과
 * -> 분명 잘 풀었는데? 이거 말고는 답이 없는데 ?
 * 문제점 : visited 를 했을 때 최종 경로가 아닌 곳은 다시 false로 처리했던게 문제였음
 * -> 앞에서 막혀서 안쓰는 root라면 뒤에서도 탐지할 필요가 없다. -> false로 유지했어야했다.
 *
 *
 * 골드 2인지 모르고 그냥 덤볐는데, 어찌저찌하다보니 풀렸다..
 * 앞으로 dfs 를 구현할때 어떨때 비재귀형으로 풀고 어떨때 재귀형으로 풀지 고민해보자.
 *
 *
 * */



public class Main_박다빈 {
    static int R;
    static int C;
    static char[][] world;
    static boolean[][] visited;
    static int[][] movePolicy = {{-1,1},{0,1},{1,1}};
    static int count;
    static boolean stopFlag ;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C= Integer.parseInt(st.nextToken());
        world = new char[R][];
        visited = new boolean[R][C];
        for(int i=0;i<R;i++) {
            world[i] = br.readLine().toCharArray();
        }
        count=0;

        //각 행마다 dfs 돌리기
        for(int i=0;i<R;i++) {
            if(visited[i][0]) continue;
            stopFlag = false;
            visited[i][0] = true;
            dfs(i,0);

        }
        System.out.println(count);

    }
    public static void dfs(int x, int y) {

        if(y==C) return;

        if(stopFlag) return;

        for(int[] m : movePolicy) {
            int nx = x + m[0];
            int ny = y + m[1];
            if(nx<0 || nx>=R || ny<0 || ny>=C || visited[nx][ny]) continue;
            if(world[nx][ny]=='x') continue;

            visited[nx][ny] = true;
            if(ny==C-1) {
                count++;
                stopFlag =true;
                return;
            }

            dfs(nx, ny);
            if(stopFlag) return;
            //여기서 greedy가 쓰였다. -> 시간 초과 해결 부분
            //visited[nx][ny] = false;
        }


    }

}
