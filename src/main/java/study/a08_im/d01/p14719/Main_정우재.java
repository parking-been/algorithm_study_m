package main.java.study.a08_im.d01.p14719;

import java.io.*;
import java.util.*;

public class Main_정우재 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        Integer.parseInt(st.nextToken()); //H는 버림
        int W = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();

        st = new StringTokenizer(in.readLine(), " ");
        int left= Integer.parseInt(st.nextToken()); //초기 왼쪽 기둥 초기화
        int result = 0; //고인 물의 높이
        for(int i = 1 ; i < W; i++){
            int newBlock = Integer.parseInt(st.nextToken());
            if(left > newBlock){ //왼쪽 기둥보다 낮으면 stack에 푸쉬
                stack.push(newBlock);
                continue;
            }

            while(!stack.isEmpty()){ //왼쪽 기둥과 같거나 높으면 stack에 있는 블록들의 고인 물의 높이를 계산 후 left 업데이트
                int block = stack.pop();
                result += (left - block);
            }

            left = newBlock;
        }

        if(!stack.isEmpty()){ //모든 기둥을 다 넣었음에도 stack이 비어있지 않았으면, 이는 고인물의 높이를 측정할 블록이 남아있다는 뜻.
            int right = stack.pop();
            while(!stack.isEmpty()){
                int block = stack.pop();
                if(right > block) //오른쪽 기둥보다 낮으면 고인물의 높이 계산
                        result += (right - block);
                else              //오른쪽 기둥과 같거나 크면 오른쪽 기둥 업데이트
                    right = block;
            }
        }

        System.out.println(result);
    }
}
