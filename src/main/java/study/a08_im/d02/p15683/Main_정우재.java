package main.java.study.a08_im.d02.p15683;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    static class CCTV{
        int row;
        int col;
        int num;
        List<Integer> dirs = new ArrayList<>();
        public CCTV(int row, int col, int num) {
            super();
            this.row = row;
            this.col = col;
            this.num = num;
        }

        public void addDir(int direction) {
            dirs.add(direction);
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    static int result, area;
    static List<CCTV> cctvs;
    static int[][] delta = new int [][] {
            {0, -1}, //좌
            {-1, 0}, //상
            {0, 1}, //우
            {1, 0} //하
    };
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cctvs = new ArrayList<>();
        result = Integer.MAX_VALUE;
        area = 0;
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    area++;
                if(0 < map[i][j] && map[i][j] < 6)
                    cctvs.add(new CCTV(i, j, map[i][j]));
            }
        }

        permutation(0, new CCTV[cctvs.size()]);

        System.out.println(result);
    }
    private static void permutation(int cnt, CCTV[] cctvArr) {

        if(cnt == cctvs.size()) {
            calculate(cctvArr);
            return;
        }

        CCTV selected = cctvs.get(cnt);
        switch (selected.num) {
            case 1:
                for(int direction = 0 ; direction < 4 ;direction++) {
                    CCTV cctv = new CCTV(selected.row, selected.col, selected.num);
                    cctv.addDir(direction);
                    cctvArr[cnt] = cctv;
                    permutation(cnt+1, cctvArr);
                }
                break;
            case 2:
                for(int direction = 0 ; direction < 2 ;direction++) {
                    CCTV cctv = new CCTV(selected.row, selected.col, selected.num);
                    cctv.addDir(direction);
                    cctv.addDir(direction+2);
                    cctvArr[cnt] = cctv;
                    permutation(cnt+1, cctvArr);
                }
                break;
            case 3:
                for(int direction = 0 ; direction < 4 ;direction++) {
                    CCTV cctv = new CCTV(selected.row, selected.col, selected.num);
                    cctv.addDir(direction);
                    cctv.addDir((direction+1)%4);
                    cctvArr[cnt] = cctv;
                    permutation(cnt+1, cctvArr);
                }
                break;
            case 4:
                for(int direction = 0 ; direction < 4 ;direction++) {
                    CCTV cctv = new CCTV(selected.row, selected.col, selected.num);
                    cctv.addDir(direction);
                    cctv.addDir((direction+1)%4);
                    cctv.addDir((direction+2)%4);
                    cctvArr[cnt] = cctv;
                    permutation(cnt+1, cctvArr);
                }
                break;
            case 5:
                for(int direction = 0 ; direction < 4 ;direction++) {
                    CCTV cctv = new CCTV(selected.row, selected.col, selected.num);
                    cctv.addDir(direction);
                    cctv.addDir((direction+1)%4);
                    cctv.addDir((direction+2)%4);
                    cctv.addDir((direction+3)%4);
                    cctvArr[cnt] = cctv;
                    permutation(cnt+1, cctvArr);
                }
                break;
        }
    }
    private static void calculate(CCTV[] cctvArr) {
        boolean[][] isVisited = new boolean[N][M];
        int cnt = 0;
        for(int i = 0 ; i < cctvArr.length; i++) {
            CCTV cctv = cctvArr[i];
            for(int j = 0 ; j < cctv.dirs.size(); j++) {
                int dir = cctv.dirs.get(j);
                int nextRow = cctv.row + delta[dir][0];
                int nextCol = cctv.col + delta[dir][1];
                while(isIn(nextRow, nextCol)) {
                    if(map[nextRow][nextCol] == 6 ) break;
                    if(map[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                        isVisited[nextRow][nextCol] = true;
                        cnt++;
                    }
                    nextRow += delta[dir][0];
                    nextCol += delta[dir][1];
                }
            }
        }

        result = Math.min(result, area-cnt);

    }

    private static boolean isIn(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col <    M;
    }
}

/*
아래는 최초로 시도한 로직이며, 안되는 이유를 찾지 못했습니다 (추측컨데, 방문처리와 방문처리 롤백하는 과정에서 무언가 놓친게 있는 것 같습니다. 하지만 ai를 활용해도 잘 모르겠네요)
로직을 간단히 설명하자면,
1. 5번에 해당되는 cctv에 대한 방문처리를 미리 해준다
2. 나머지 cctv 각각을 하나씩 조합해가되, 각 cctv의 방향이 정해질 때 마다 방문처리를 해준다.
2-1. 한 방향으로의 조합이 끝나면 방문처리했던 로직들을 롤백시키고 다음 방향으로 진행한다


public class Main_정우재{
    static int N, M;
    static int[][] map;
    static int[][] isMonitored;
    static int result;
    static int notSpaceCount, alwaysMonitoredCount;
    static List<int[]> cctv;
    static List<int[]> allDirectionCctv;
    static int[][] delta = new int [][] {
            {0, -1}, //좌
            {-1, 0}, //상
            {0, 1}, //우
            {1, 0} //하
    };
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isMonitored = new int[N][M];
        cctv = new ArrayList<>();
        result = Integer.MAX_VALUE;
        notSpaceCount = 0;
        allDirectionCctv = new ArrayList<>();
        int cctvCount = 0;
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    continue;
                if(0 < map[i][j] && map[i][j] < 5)
                    cctv.add(new int[] {i, j, map[i][j]});
                if(map[i][j] == 5)
                    allDirectionCctv.add(new int[] {i, j});
                notSpaceCount++;
            }
        }

        alwaysMonitoredCount = 0;
        for(int i = 0 ; i < allDirectionCctv.size(); i++) {
            int[] cctvInfo = allDirectionCctv.get(i);
            int row = cctvInfo[0];
            int col = cctvInfo[1];
            alwaysMonitoredCount += turnOn(0, row, col);
            alwaysMonitoredCount += turnOn(1, row, col);
            alwaysMonitoredCount += turnOn(2, row, col);
            alwaysMonitoredCount += turnOn(3, row, col);
        }

        permutation(0, 0);

        System.out.println(result);
    }
    private static void permutation(int cnt, int sum) {

        if(cnt == cctv.size()) {
            if((N * M - sum - notSpaceCount - alwaysMonitoredCount) < result)
                result = N * M - sum - notSpaceCount - alwaysMonitoredCount;
            return;
        }

        int[] cctvInfo = cctv.get(cnt);
        int count = 0;
        int row = cctvInfo[0];
        int col = cctvInfo[1];

        switch (cctvInfo[2]) {
        case 1:
            for(int direction = 0 ; direction < 4 ;direction++) {
                count += turnOn(direction, row, col);
                permutation(cnt+1, sum+count);
                turnOff(direction, row, col);
                count = 0;
            }
            break;
        case 2:
            for(int direction = 0 ; direction < 1 ;direction++) {
                count += turnOn(direction, row, col);
                count += turnOn(direction+2, row, col);
                permutation(cnt+1, sum+count);
                turnOff(direction, row, col);
                turnOff(direction+2, row, col);
                count = 0;
            }
            break;
        case 3:
            for(int direction = 0 ; direction < 4 ;direction++) {
                count += turnOn(direction, row, col);
                count += turnOn((direction+1)%4, row, col);
                permutation(cnt+1, sum+count);
                turnOff(direction, row, col);
                turnOff((direction+1)%4, row, col);
                count = 0;
            }
            break;
        case 4:
            for(int direction = 0 ; direction < 4 ;direction++) {
                count += turnOn(direction, row, col);
                count += turnOn((direction+1)%4, row, col);
                count += turnOn((direction+2)%4, row, col);
                permutation(cnt+1, sum+count);
                turnOff(direction, row, col);
                turnOff((direction+1)%4, row, col);
                turnOff((direction+2)%4, row, col);
                count = 0;
            }
            break;
        }
    }
    private static void turnOff(int direction, int row, int col) {
        while(true) {
            if(map[row][col] == 0) {
                isMonitored[row][col]--;
            }
            int nextRow = row + delta[direction][0];
            int nextCol = col + delta[direction][1];
            if(!isIn(nextRow, nextCol) || map[nextRow][nextCol] == 6)
                break;
            row = nextRow;
            col = nextCol;
        }
    }

    private static int turnOn(int direction, int row, int col) {
        int count = 0;
        while(true) {
            int nextRow = row + delta[direction][0];
            int nextCol = col + delta[direction][1];

            if(!isIn(nextRow, nextCol) || map[nextRow][nextCol] == 6)
                break;
            row = nextRow;
            col = nextCol;

            if(map[row][col] == 0) {
                if(isMonitored[row][col] == 0) {
                    count++;
                }
                isMonitored[row][col]++;
            }
        }

        return count;
    }

private static boolean isIn(int row, int col) {
    return 0 <= row && row < N && 0 <= col && col <    M;
}
}
 */