package main.java.study.a05_shortest.d03.p1865;
import java.io.*;
import java.util.*;


/*
 *
 * <인사이트>
 * 처음 시작점이 정해진게 아니라 사이클 경우의 수를 모두 파악해야함으로 시간 복잡도로 따졌을떄
 * O(n^3) 인, 플로이드 와샬을 생각했다.
 * 심지어 n이 100정도 되어서 그렇게 크지 않아서 풀릴거라고 생각했다.
 *
 * <트러블>
 * 경로를 받을때 기존 경로에 덮어씌워지는 부분이 에러사항이었다.
 *
 * <다른 뷰>
 * 음수 사이클을 탐지할 수 있는 벨만포드가 더 좋다는 의견을 gpt를 통해 받았다.
 * 기존에 벨만포드를 생각하지 않은 이유는 모든 시작이 될 수 있는 점에 대해 탐지해야해서 플로이드 와샬보다 느리다고 생각해서 이다.
 * 하지만? 어떤 곳을 처음으로 잡던간에 음의 사이클을 참지할 수 있는 벨만포드를 사용하면 더 짧은 시간복잡도로 문제를 풀 수 있다.
 * O(n*m)
 * 실제로 블로그들을 뒤져보니 그렇게 푼 사람들이 많은거 같다.
 *
 *
 *
 *
 *
 *
 * */
public class Main_박다빈 {
    static int[][] world;
    static int INF = (int)1e9;
    static int N ;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            world = new int[N+1][N+1];

            for(int i=1;i<N+1;i++) {
                Arrays.fill(world[i], INF);
                world[i][i] = 0;
            }



            //도로 정보
            for(int i=0;i<M;i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
                int S = Integer.parseInt(st2.nextToken());
                int E = Integer.parseInt(st2.nextToken());
                int TT = Integer.parseInt(st2.nextToken());
                world[S][E] = Math.min(world[S][E], TT);
                world[E][S] = Math.min(world[E][S], TT);
            }

            //웜홀 정보
            for(int i=0;i<W;i++) {
                StringTokenizer st3 = new StringTokenizer(br.readLine(), " ");
                int S = Integer.parseInt(st3.nextToken());
                int E = Integer.parseInt(st3.nextToken());
                int TT = Integer.parseInt(st3.nextToken());
                world[S][E] = Math.min(world[S][E], -TT);;

            }

            //문제 풀기
            boolean result = solution();
//			for(int[] e : world) {
//				System.out.println(Arrays.toString(e));
//			}
            if (result ) {
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }


        }


    }

    static public boolean solution() {
        for(int k=1;k<=N;k++) {
            for(int i=1;i<=N;i++) {
                for(int j=1;j<=N;j++) {
                    if(world[i][k] != INF && world[k][j] != INF && world[i][j] > world[i][k]+world[k][j]) {
                        world[i][j] =world[i][k]+world[k][j];
                    }


                }
            }
        }

        for(int i=1;i<=N;i++) {
            if(world[i][i]<0) return true;
        }
        return false;
    }
}
