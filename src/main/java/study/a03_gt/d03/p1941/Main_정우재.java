package main.java.study.a03_gt.d03.p1941;

import java.util.*;
import java.io.*;

public class Main_정우재 {
    public static int[] selected = new int[7];
    public static boolean[] isVisited;
    public static char[][] map;
    public static int[][] move = new int [][] {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    public static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        map = new char[5][5];
        for (int i = 0; i < 5; i++) {
            map[i] = sc.nextLine().toCharArray();
        }

        permutation(0, 0, 0);
        System.out.println(answer);
    }

    private static void permutation(int cnt, int start, int somCount) {
        if(cnt - somCount > 3)
            return;

        if(cnt == 7){
            isVisited = new boolean[7];
            bfs();
            return;
        }

        for(int i = start ; i < 25; i++){
            selected[cnt] = i;
            int row = i / 5;
            int col = i % 5;
            permutation(cnt+1, i+1, map[row][col] == 'S' ? somCount+1 : somCount);
        }
    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        isVisited[0] = true;
        queue.add(selected[0]);
        int count = 1;
        while(!queue.isEmpty()){
            int student = queue.poll();
            int row = student / 5;
            int col = student % 5;

            for (int i = 0 ; i < 4 ; i++){
                int nextRow = row + move[i][0];
                int nextCol = col + move[i][1];

                if (isIn(nextRow, nextCol)) {
                    for (int index = 0 ; index < 7 ; index++){
                        int nextStudent = nextRow * 5 + nextCol;

                        if ( !isVisited[index] &&  selected[index] == nextStudent){
                            isVisited[index] = true;
                            queue.add(nextStudent);
                            count++;
                        }
                    }
                }
            }
        }
        if (count == 7)
            answer++;
    }

    public static boolean isIn (int row, int col) {
        return  0 <= row && row <5 && 0 <= col && col <5;
    }
}

