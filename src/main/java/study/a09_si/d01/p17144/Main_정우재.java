package main.java.study.a09_si.d01.p17144;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    static int[][] map;
    static int[][] nextMap;
    static int R, C, T;
    static int[][] machine;
    static final int UP = 0;
    static final int DOWN = 1;
    static int[][] near = {
            {-1, 0}, {1, 0}, {0 , -1}, {0 , 1}
    };
    static int[][][] delta = {
            {
                    {-1, 0}, {0, 1}, {1, 0}, {0, -1}
            }, //윗 바람
            {
                    {1, 0}, {0, 1}, {-1, 0}, {0, -1}
            } //아래 바람
    };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        machine = new int[2][2];

        boolean isDown = false;
        for(int i = 0 ; i < R ;i++){
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < C ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1 ) {
                    if (!isDown) {
                        machine[0] = new int[]{i, j};
                        isDown = true;
                    } else
                        machine[1] = new int[]{i, j};

                }
            }
        }

        for(int k = 0 ; k <T; k++){
            nextMap = new int[R][C];
            dust();
            map = nextMap;

            wind(0,  machine[UP][0], UP);
            wind(machine[DOWN][0], R-1, DOWN);
        }

        int result = 0;
        for (int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C; j++){
                if((i == machine[0][0] && j == machine[0][1]) || (i == machine[1][0] && j == machine[1][1]))
                    continue;
                result += map[i][j];
            }
        }
        System.out.println(result);
    }

    private static void dust() {
        for (int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C; j++){
                if((i == machine[0][0] && j == machine[0][1]) || (i == machine[1][0] && j == machine[1][1]))
                    continue;

                if(map[i][j] > 0){
                    spread(i , j, map[i][j]);
                }
            }
        }
    }

    private static void spread(int row, int col, int size) {
        nextMap[row][col] += size;

        int spreadSize = size / 5;
        for(int i = 0 ; i < 4 ;i++){
            int nearRow = row + near[i][0];
            int nearCol = col + near[i][1];

            if( 0 <= nearRow && nearRow < R && 0 <= nearCol && nearCol < C){
                if((nearRow == machine[0][0] && nearCol == machine[0][1]) || (nearRow == machine[1][0] && nearCol == machine[1][1]))
                    continue;
                nextMap[nearRow][nearCol] += spreadSize;
                nextMap[row][col] -= spreadSize;
            }
        }
    }

    private static void wind(int minRow, int maxRow, int windDirection) {
        int row = machine[windDirection][0];
        int col = machine[windDirection][1];

        for (int i = 0; i < 4; i++) {
            while (true) {
                int nextRow = row + delta[windDirection][i][0];
                int nextCol = col + delta[windDirection][i][1];

                if(minRow <= nextRow && nextRow <= maxRow && 0 <= nextCol && nextCol < C){
                    map[row][col] = map[nextRow][nextCol];
                    row = nextRow;
                    col = nextCol;
                }
                else
                    break;
            }
        }

        row = machine[windDirection][0];
        col = machine[windDirection][1]+1;
        map[row][col] = 0;
    }
}
