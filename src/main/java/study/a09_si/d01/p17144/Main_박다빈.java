package main.java.study.a09_si.d01.p17144;

import java.io.*;
import java.util.*;

/*
<인사이트>

요약 : 동시 변화(확산) + 경로 이동(청정기) 로 분리해서 푼 시뮬레이션 문제!

1. 문제를 두 단계로 분리
확산 (동시에 일어남) → diffuse()
공기청정기 (순차 이동) → machine(), machineLow()

👉 서로 성질이 달라서 반드시 나눠야 함

2. 확산은 “보조 배열” 사용
map → 읽기용
nextMap → 결과 저장

👉 동시에 퍼지는 걸 정확히 구현

3. 공기청정기는 “회전”이 아니라 “한 칸씩 밀기”
경로를 따라 값을 당겨오는 방식
map[x][y] = map[nx][ny];

👉 배열 회전보다 훨씬 구현 쉬움

4. 방향 배열 + 순서(order)로 순환 구현
위 / 아래 공기청정기 → order만 다르게

👉 코드 재사용 + 깔끔한 구현

5. 상태 갱신 문제로 바라봄
매 시간마다
확산 → 공기청정기 → 반복

<다른 블로그 인사이트>
https://dingdingmin-back-end-developer.tistory.com/entry/%EB%B0%B1%EC%A4%80-17144-%EC%9E%90%EB%B0%94-java-%EB%AF%B8%EC%84%B8%EB%A8%BC%EC%A7%80-%EC%95%88%EB%85%95
https://jsw5913.tistory.com/195
공기 청정기 순환 부분을 고민을 많이 했는데 단순히 for문 배열 여러개를 직렬적으로 사용해도 빠르게 문제를 풀 수 있었을 것 같다.


 * */


public class Main_박다빈 {
    static int R;
    static int C;
    static int T;
    static int[][] map;
    static int[] machineL; //젤 아래꺼 저장하기 (위, 아래 중에서)
    static int[][] movePolicy = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];


        for(int i=0;i<R;i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

            for(int j=0;j<C;j++) {

                map[i][j] = Integer.parseInt(st2.nextToken());
                if(map[i][j]==-1) {
                    machineL= new int[] {i,j};
                }


            }
        }


        for(int tc=1;tc<=T;tc++) {
            //1. 확산
            map = diffuse();

            //2. 공기 청정기 가동
            machine();
            machineLow();

            //System.exit(0);
        }


        int sum=0;
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {

                if (map[i][j]>0) sum+=map[i][j];
            }
        }

        System.out.println(sum);




    }



    public static void machineLow() {
        //위
        int[] order = {2,0,3,1};

        int x = machineL[0]+1; //출발 지점
        int y = machineL[1];
        int indx = 0;


        while(true) {
            int nx = x + movePolicy[order[indx]][0];
            int ny = y + movePolicy[order[indx]][1];
            //만약 범위가 넘어간다면 udpate
            if (nx<machineL[0] || nx>=R || ny<0 || ny>=C) {
                indx++;

                continue;
            }
            //공기청정기 부딪히면 끝!
            if(map[nx][ny]==-1) {
                map[x][y]=0;
                break;}

            map[x][y] = map[nx][ny];
            x = nx;
            y = ny;


        }


    }



    public static void machine() {
        //위
        int[] order = {3,0,2,1};



        int x = machineL[0]-2; //출발 지점
        int y = machineL[1];
        int indx = 0;

        while(true) {
            int nx = x + movePolicy[order[indx]][0];
            int ny = y + movePolicy[order[indx]][1];

            //만약 범위가 넘어간다면 udpate
            if (nx<0 || nx>=machineL[0] || ny<0 || ny>=C) {
                indx++;
                continue;
            }
            //공기청정기 부딪히면 끝!
            if(map[nx][ny]==-1) {
                map[x][y]=0;
                break;}

            map[x][y] = map[nx][ny];
            x = nx;
            y = ny;

        }




        //아래
    }

    public static int[][] diffuse() {
        int[][] nextMap = new int[R][C];

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(map[i][j]>0) {
                    int count=0;
                    int separate = map[i][j] / 5;
                    for(int[] m : movePolicy) {
                        int nx = i+m[0];
                        int ny = j+m[1];
                        if(nx<0 || nx>=R || ny<0 ||ny>=C || map[nx][ny]==-1) continue;
                        count++;
                        nextMap[nx][ny]+=separate;
                    }
                    nextMap[i][j]+=map[i][j] - count*separate;

                }

            }
        }


        nextMap[machineL[0]][machineL[1]]=-1;
        nextMap[machineL[0]-1][machineL[1]]=-1;


        return nextMap;
    }



}
