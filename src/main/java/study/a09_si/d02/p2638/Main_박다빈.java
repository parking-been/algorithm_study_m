package main.java.study.a09_si.d02.p2638;

import java.io.*;
import java.util.*;


/*
<인사이트>

1. bfs로 공기와 맞닿는 부분 count하기
제일 가장자리에는 치즈가 없을 것이라고 했기 때문에 
가장자리부터 bfs를 돌리고, 
인접한 부분이 치즈일 경우 +1을 해준다.

2. count한 것중 2이상인 치즈는 없애기

<다른 블로그 인사이트>
https://minhamina.tistory.com/156
비슷하다. 

 * */

public class Main_박다빈 {
    static int N;
    static int M;
    static int[][] map;
    static int[][] check;
    static int[][] movePolicy = {{0,-1},{0,1},{1,0},{-1,0}};
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];


        for(int i=0;i<N;i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
        int result = 0;
        //1. bfs돌리기 (0,0) + check를 기반으로 땅 지우기
        int contin = 1;
        while(contin==1) {
            contin = bfs(0,0);
            result ++;
        }

        System.out.println(result);


    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        check = new int[N][M];
        boolean [][] visited = new boolean[N][M];
        queue.add(new int[] {x,y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int[] m : movePolicy) {
                int nx = cur[0] + m[0];
                int ny = cur[1] + m[1];

                if(nx<0 || nx>=N || ny<0 ||ny>=M || visited[nx][ny]==true ) {
                    continue;
                }
                if(map[nx][ny]==1) {
                    //update해주기
                    check[nx][ny] +=1;
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new int[] {nx, ny});

            }


        }

//        for(int[] ch: check) {
//            System.out.println(Arrays.toString(ch));
//        }
//        System.out.println();

        int flag = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {

                if(check[i][j]>=2) {
                    map[i][j]=0;
                }
                if(map[i][j]==1) {
                    flag = 1;
                }
            }
        }

        return flag;

    }


}