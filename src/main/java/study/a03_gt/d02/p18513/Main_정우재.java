package main.java.study.a03_gt.d02.p18513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//완전 탐색은 범위도 정해지지 않았으며, 샘의 좌표 범위만 해도 1초는 넘어가므로 완탐은 제외시켰습니다.
//샘을 기준으로 점차 퍼져나가는 형태이기에 BFS를 선택하였습니다.

//각 샘들의 위치를 스택에 넣어줌
//pop 해오고
//불행도에 더해줌. 본인 레벨 값을 더해줌
//본인 기준 좌우 공간에 아무것도 없다면, 해당 좌표와 (본인 레벨+1) 값을 배열로 묶어서 넣어줌
//      자식을 넣고나서 방문처리

public class Main_정우재 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> isSelected = new HashSet<>();

        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0 ;  i < N ; i++){
            int location  = Integer.parseInt(st.nextToken());
            queue.add( new int[] {location, 0} );
            isSelected.add(location); //본인 위치 추가
        }

        int count = 0;
        long unhappiness = 0;
        while(count != (N+K)){
            int[] value = queue.poll();
            int location = value[0];
            int score = value[1];
            unhappiness += score;
            count++;

            if( !isSelected.contains(location-1)){
                queue.add(new int[]{location-1, score+1});
                isSelected.add(location-1);
            }

            if( !isSelected.contains(location+1)){
                queue.add(new int[]{location+1, score+1});
                isSelected.add(location+1);
            }
        }

        System.out.println(unhappiness);

    }
}
