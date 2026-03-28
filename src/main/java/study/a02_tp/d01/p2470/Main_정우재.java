package main.java.study.a02_tp.d01.p2470;

import java.util.Arrays;
import java.util.Scanner;

//for문 돌리기 => 시간 초과

//1. 두 용액의 합에 대한 절대값이 minSum보다 작다면, 업데이트
//2. 두 용액의 합이 음수이라면 start 1증가, 양수라면 end 감소
//3. sum이 0이 되거나 start와 end가 만나면 종료
public class Main_정우재 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = sc.nextInt();
        sc.nextLine();
        int[] solution = new int[count];

        for (int i = 0 ; i < count ; i ++)
            solution[i] = sc.nextInt();
        sc.nextLine();

        Arrays.sort(solution); //정렬

        int start = 0;
        int end = count-1;
        int sum = 0;

        int closestValue1 = 0; //minSum에 해당되는 용액 중 특성값이 낮은 용액의 특성값
        int closestValue2 = 0; //minSum에 해당되는 용액 중 특성값이 높은 용액의 특성값
        int minSum = Integer.MAX_VALUE; //초기 값은 max값으로 설정

        while(start != end) {
            sum = solution[start] + solution[end];

            if(Math.abs(sum) < minSum) { //두 용액의 합에 대한 절댓값이 minSum보다 작다면 업데이트
                minSum = Math.abs(sum);
                closestValue1 = solution[start];
                closestValue2 = solution[end];
            }

            if(sum == 0) //sum값이 0이면 종료
                break;

            if(sum > 0) //sum 값이 0보다 크면 end 감소
                end--;
            else         //sum 값이 0보다 작으면 start 증가
                start++;
        }
        System.out.println(closestValue1 + " " + closestValue2 );

    }
}