package main.java.study.a03_gt.d01.p2667;

import java.io.*;
import java.util.*;

/*
 * 인사이트
 * 2차원 배열에서 단지 묶음을 계산하는 것 => 단지중 하나의 아파트에서 사방으로 뻗어나가 개수를 세아리는 것
 * => bfs로 선택
 *
 * 풀이
 * 전체 아파트를 돌아다니며 단지를 계산
 * 단지 내 하나의 아파트에서 사방으로 뻗어나 단지 묶음 계산
 * 이때 이미 처리된 단지의 경우 visited 를 사용하여 체크해준다.
 *
 * 한번 실패 이유
 * 출력하기 전에 sort를 하지 못했다.
 * */


public class Main_박다빈 {
    static int N;
    static char[][] world;
    static int[][] move = {{-1,0},{1,0},{0,1},{0,-1}};
    static boolean[][] visited;
    static List<Integer> counts ;
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        world = new char[N][];
        visited = new boolean[N][N];
        counts = new ArrayList<>();

        for(int i=0;i<N;i++) {
            String st = br.readLine();
            world[i] = st.toCharArray();
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(world[i][j]=='1' && !visited[i][j]) {
                    bfs(i,j);
                }
            }
        }
        Collections.sort(counts);

        sb.append(counts.size()).append("\n");
        for(int c : counts) {
            sb.append(c).append("\n");
        }



        System.out.println(sb.toString());

    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {x, y});
        visited[x][y] = true;
        int count=1;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            for(int[] m : move) {
                int nx = cur[0] + m[0];
                int ny = cur[1] + m[1];
                if(0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny] && world[nx][ny]=='1') {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx,ny});
                    count++;
                }
            }
        }
        counts.add(count);

    }

}