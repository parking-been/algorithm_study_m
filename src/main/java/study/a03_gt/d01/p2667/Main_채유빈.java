package main.java.study.a03_gt.d01.p2667;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Main_채유빈 {
    private static int N;
    private static char[][] map;  // 요소에 접근만 하면 되므로 연결리스트가 아닌 배열 선택
    private static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // 상, 하, 좌, 우
    
    public static int getHouseCount(int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {row, col}); 
        int houseCount = 0;
        
        while (!queue.isEmpty()) {
            // 큐에서 빼내고 방문 처리, 집 개수 카운트
            int[] housePosition = queue.poll();
            map[housePosition[0]][housePosition[1]] = '0';
            houseCount++;

            for (int i = 0; i < direction.length; i++) {
                int nextRowPos = housePosition[0] + direction[i][0];
                int nextColPos = housePosition[1] + direction[i][1];

                if (nextRowPos < 0 || nextRowPos >= N || nextColPos < 0 || nextColPos >= N || map[nextRowPos][nextColPos] == '0') continue;

                queue.offer(new int[] {nextRowPos, nextColPos});
                map[nextRowPos][nextColPos] = '0';
            }
        }

        return houseCount;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        List<Integer> houseCountList = new ArrayList<>();  // 단지의 개수는 미리 알 수 없으므로 리스트 선택
        int complexCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '1') {  // 1인 경우 탐색 시작
                    complexCount++;  // 탐색 시작 시 단지 개수 카운트
                    houseCountList.add(getHouseCount(i, j));
                }
            }
        }

        Collections.sort(houseCountList);

        sb.append(complexCount).append("\n");

        for (int num : houseCountList) {
            sb.append(num).append("\n");
        }

        System.out.print(sb);
    }
}