package main.java.study.a04_greedy.d01.p1946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정우재 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        int T = Integer.parseInt(st.nextToken());
        for(int testCase = 0 ; testCase < T ; testCase++){
            st = new StringTokenizer(in.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            for(int i = 0 ; i< N ; i++){ //성적을 입력 받음과 동시에 서류 순위를 기준으로 정렬
                st = new StringTokenizer(in.readLine(), " ");
                int docsRank = Integer.parseInt(st.nextToken());
                int interviewRank = Integer.parseInt(st.nextToken());

                arr[docsRank-1] = interviewRank;
            }

            int count = 1; //서류 기준 1등은 바로 합격
            int currentInterviewHighRank= arr[0]; //현재 면접 등수가 제일 높은 사람은 임시로 서류 기준 1등인 지원자로 초기화
            for(int i = 1 ; i < N ; i++ ) {
                if (currentInterviewHighRank > arr[i]) { //앞 지원자들의 제일 높은 서류 등수보다 높으면 업데이트
                    currentInterviewHighRank = arr[i];
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
