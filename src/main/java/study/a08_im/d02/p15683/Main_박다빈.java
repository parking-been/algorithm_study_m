package main.java.study.a08_im.d02.p15683;

import java.io.*;
import java.util.*;

/*
<인사이트>
최소의 사각지대를 알아야한다.
=> 모든 경우의 수를 돌려보지 않고는 모른다.
-> 완탐.

고민했던 부분
map 부분을 dfs로 돌리고나서 다시 복구시킬지 아니면 copy해서 넘겨줄지
그런데, N, M 이 크기가 아주 작았기 때문에 copy 해도 된다고 판단하여 copy해서 파라미터 값으로 넘겨줌

<다른 블로그는 어떻게 풀었나>
 https://jsw5913.tistory.com/124
 구현이라 그런가 가지각색이다.
 나는 내 코드가 규칙을 정해놓고 풀어서그런지 더 깔끔하니 좋은것 같다.

 <알아야할 것>
 배열 copy
 tMap[i] = map[i].clone();

*/



public class Main_박다빈 {
    static int[][][] dirPolicy = {
            {{0},{1},{2},{3}}, //1번의 경우

            {{1,3},{0,2}}, //2번의 경우

            {{0,3},{2,3},{1,2},{0,1}}, //3번의 경우

            {{0,1,2}, {1,2,3},{0,2,3},{0,1,3}}, //4번의 경우

            {{0,1,2,3}} //5번의 경우
    };
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
    static List<int[]> cameras;
    static int num;
    static int N;
    static int M;
    static int result;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " " );
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cameras = new ArrayList<>();
        int[][] map = new int[N][M];
        result = Integer.MAX_VALUE;
        for(int i=0;i<N;i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
                if(map[i][j]>0 && map[i][j]<6 ) {
                    cameras.add(new int[] {i,j});
                }
            }
        }

        num = cameras.size();

        dfs(map, 0);
        System.out.println(result);

    }



    public static void dfs(int[][] map, int depth) {
        if(depth==num) {
            //최종 사각지대 확인
            int count=0;
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(map[i][j]==0) count++;
                }
            }

            result = Math.min(result, count);
            return;
        }

        int[] pos = cameras.get(depth);

        int kin = map[pos[0]][pos[1]];

        //System.out.println("디버깅" + kin);

        for(int[] moves : dirPolicy[kin-1]) {

            int[][] tMap = new int[N][M];

            for(int i=0;i<N;i++) {
                tMap[i]= map[i].clone();
            }

            //하나씩 옮겨 보자
            for(int m : moves) {

                int dx = dir[m][0];
                int dy = dir[m][1];

                int x = pos[0];
                int y = pos[1];

                while(x+dx>=0 && x+dx<N && y+dy>=0 && y+dy<M && tMap[x+dx][y+dy]!=6) {
                    x = x+dx;
                    y = y+dy;

                    if (tMap[x][y]!=0) continue;

                    tMap[x][y] = -1;

                }

            }

//			for(int i=0;i<N;i++) {
//				System.out.println(Arrays.toString(tMap[i]));
//			}
//			System.out.println();
            dfs(tMap, depth+1);

            //System.exit(0);



        }
    }

}
