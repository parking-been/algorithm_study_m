package main.java.study.a04_greedy.d02.p3109;

import java.io.*;
import java.util.*;

//파이프를 가급적 윗쪽 방향으로 설치를 해야지 다음 라인 설치 시 가장 넓은 설치 가능 구역을 확보 할 수 있다 (greedy)
//오른쪽 위 -> 오른쪽 -> 오른쪽 아래 우선순위로 파이프를 연결한다
//한번 지나간 자리는 X 처리를 한다. 다음번에 그 자리에 또 오게 된다면 어차피 갈 필요가 없으니 x 처리를 한다.

//로직
//왼쪽 벽 위쪽부터 하나씩 아래로 내려오면서 연결 지을 수 있는 파이프 경로를 오른쪽 위 -> 오른쪽 -> 오른쪽 아래 우선순위로 연결을 시도한다
//파이프를 연결하던 도중 더이상 진행을 할 수 없다면 종료
//파이프를 끝까지 연결 성공시 count++

public class Main_정우재 {
    public static int R, C;
    public static int count;
    public static char[][] map;
    public static int[][] delta = {
            {-1, 1},
            {0, 1},
            {1, 1},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = in.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            connectedPipe(i, 0, 0);
        }

        System.out.println(count);
    }

    private static boolean connectedPipe(int row, int col, int cnt) { //map[row, col] 위치에서 다음 파이프를 연결짓는 재귀함수
        if (cnt == C - 1) { //빵집 벽에 도달함
            count++;
            return true;
        }

        for (int i = 0; i < 3; i++) { //오른쪽 위, 오른쪽, 오른쪽 아래 순서로 연결을 지을 수 있는 지 탐색
            int nextRow = row + delta[i][0]; //다음으로 연결할 파이프 위치 row
            int nextCol = col + delta[i][1]; //다음으로 연결할 파이프 위치 col
            if (0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C) { //다음 연결할 위치가 맵을 벗어나지 않았다면
                if (map[nextRow][nextCol] == '.') { //다음 연결할 위치가 연결이 가능하다면
                    map[nextRow][nextCol] = 'x'; //다른 파이프는 오지 못하도록 막고
                    boolean result = connectedPipe(nextRow, nextCol, cnt + 1); //다음 위치로 파이프를 연결
                    if (result) //만약 파이프가 끝까지 연결이 되었다면 최초 호출 함수까지 true를 반환토록 하여 함수 강제 종료
                        return true;
                    //만약 파이프가 끝까지 연결되지 못했다면 다음 위치 연결 시도
                }
            }
        }
        return false; //그 어떤 방향으로도 연결이 되지 못했으므로 return false;
    }
}