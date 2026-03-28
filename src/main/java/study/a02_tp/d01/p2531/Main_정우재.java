package main.java.study.a02_tp.d01.p2531;

import java.util.HashMap;
import java.util.Scanner;

//입력 조건
//2 <= 벨트에 놓인 접시의 수 <= 30,000
//2 <= 연속해서 먹어야 하는 접시의 수 <= 3,000
//단순하게 이중 for문으로 해결하려고 하면 시간 초과의 우려가 있음 (연산횟수 약 9천)

//1. start, end를 하나씩 이동시키면서 슬라이딩 윈도우 방식을 한다
//    포인터가 이동할 때마다 해당되는 메뉴 번호를 추가해주고, 삭제해준다. (단, 추가나 삭제 시 보너스 번호는 무시한다)
//2. 윈도우 이동 시 % N 을 통해서 초과 시 자동으로 윈도우 범위가 앞쪽으로 가게끔 한다
//3. max 값보다 많이 담겨있으면 업데이트
//4. start가 리스트 끝에 도달 할 때까지 반복
public class Main_정우재 {
    public static HashMap<Integer, Integer> menus = new HashMap<>(); //윈도우 내 초밥 메뉴의 종류와 그 갯수를 담는 map
    public static int couponMenu; //쿠폰에 적힌 메뉴

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int dishCount = sc.nextInt(); //초밥 벨트에 놓인 접시 수
        int menuCount = sc.nextInt(); //초밥의 종류 수
        int continuousDishCount = sc.nextInt(); //연속해서 먹는 접시의 수
        couponMenu = sc.nextInt();
        sc.nextLine();

        int[] belt = new int[dishCount]; //초밥 벨트 생성

        for (int i= 0 ; i < dishCount ; i++) { //벨트에 초밥을 놓음
            belt[i] = sc.nextInt();
            sc.nextLine();
        }

        for(int i = 0 ; i < continuousDishCount ; i++) { //초기 갯수 초밥 종류 갯수 계산
            addMenu(belt[i]);
        }
        addMenu(couponMenu); //쿠폰 메뉴를 추가하여 항상 먹을 수 있는 가짓수에 포함되도록 한다

        int max = menus.size();
        int start = 0;
        int end = continuousDishCount-1;

        while(true) { //윈도우 이동 실시
            deleteMenu(belt[start]); //start 원소 삭제 및 이동
            start = (start+1) % dishCount;

            end = (end+1) % dishCount; //end 이동 및 다음 원소 추가
            addMenu(belt[end]);

            max = Math.max(menus.size(), max);

            if(start == dishCount-1) //start가 끝에 도달 시 종료
                break;
        }

        System.out.println(max);
    }

    public static void addMenu(int menu) {
        if(!menus.containsKey(menu)) { //기존에 놓여있지 않은 메뉴는 새롭게 추가
            menus.put(menu, 1);
            return;
        }

        if(menu == couponMenu) //쿠폰 메뉴는 무시
            return;

        menus.put(menu, menus.get(menu)+1 ); //기존에 놓여있던 메뉴는 그 갯수 증가
    }

    public static void deleteMenu(int menu) {
        if(menu == couponMenu) //쿠폰 메뉴는 무시
            return;

        if(menus.get(menu) == 1) { //기존에 놓여있던 메뉴가 1개인 경우 즉, 삭제하려는 메뉴가 윈도우 내에 딱 1개 뿐인 메뉴로 map에서 삭제
            menus.remove(menu);
            return;
        }

        menus.put(menu, menus.get(menu)-1);

    }
}