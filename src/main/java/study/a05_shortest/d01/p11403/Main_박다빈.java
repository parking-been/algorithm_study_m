package main.java.study.a05_shortest.d01.p11403;
import java.io.*;
import java.util.*;
/*
 * 플로이 와샬 문제네...
 *
 * 블로그 몇개 뒤져보니 나랑 똑같이 푼 사람들이 수두룩빽뺵
 * https://steady-coding.tistory.com/94#google_vignette
 *
 * */


public class Main_박다빈 {
    static int[][] world;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        world = new int[N][N];
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++) {
                int tmp = Integer.parseInt(st.nextToken());
                world[i][j] = tmp;
            }

        }


        //플로이 와샬
        for(int k=0;k<N;k++) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(world[i][j]==1) continue;
                    if(world[i][k]==1 && world[k][j]==1) {
                        world[i][j] = 1;
                    }
                }
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(world[i][j] + " ");
            }
            System.out.println();
        }


    }
}
