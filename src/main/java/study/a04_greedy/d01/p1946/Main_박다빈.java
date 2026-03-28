package main.java.study.a04_greedy.d01.p1946;

import java.io.*;
import java.util.*;


/*
 *
 * 인사이트
 * 서류 전형 기반으로 순위 대로 sort해두고,
 * N번 만큼 돌면서 자신보다 서류 전형의 높은 순위인 사람보다 면접도 못보면 탈락.
 *
 * 후회하지 않는다. => greedy
 *
 * 어려웠던 점
 * List 기반 sort 부분이 기억나지 않았다.
 *
 * */

public class Main_박다빈 {
    static List<int[]> arr;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            int N = Integer.parseInt(br.readLine());
            arr = new ArrayList<>();
            for(int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr.add(new int[] {a,b});

            }
            //서류 기준으로 1등 부터 sort
            Collections.sort(arr, (a,b) -> Integer.compare(a[0], b[0]));

            int mini = arr.get(0)[1];
            int count = 1; //첫지원자는 무조건 합격
            for(int i=1;i<N;i++) { //면접 기준으로 따지기
                //만약 자신보다 서류 점수를 좋게 받은 사람에 대비하여 면접 점수도 떨어지면 탈락이다.
                if(arr.get(i)[1]>mini) continue;
                count++;
                mini = arr.get(i)[1];
            }


            sb.append(count).append("\n");

        }
        System.out.println(sb.toString());

    }

}
