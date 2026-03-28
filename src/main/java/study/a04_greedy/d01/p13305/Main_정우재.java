package main.java.study.a04_greedy.d01.p13305;

import java.io.*;
import java.util.*;

//그리디적인 생각
//가장 싼 주유소에서 도착지까지 갈 만큼 만땅을 채운다  => 그 주유소가 만약에 중간에 있다면 출발지에서 그 도시까지 가지못함.
//해결 : 가장 싼 주유소에 도착하면 도착지까지는 가장 싼 주유소에서 남은 거리 만큼 주유를 하고 간다
//       그럼 출발지로부터 가장 싼 주요소까지는 그 구간 내에서 가장 싼 주유소에서 주유를 하고 간다 (최적 부분 구조 문제임을 확인)

//로직
//- 주유소의 위치, 다음 도시까지의 거리를 담는 클래스를 만든다. 정렬 시, 주유 금액이 낮은 것들로 정렬되도록 compareTo 를 만든다.
//
//- 재귀 함수에 구간이 주어진다면, 구간 범위 내에서 가장 주유 금액이 작은 주유소를 찾고 그 주유소로 부터 도착지까지의 비용을 계산한다
//    - 남은 출발지 ~ 가장 싼 주유소 구간은 다시 재귀 함수로 호출한다

public class Main_정우재 {
    static class City implements Comparable<City>{
        int index;
        int distance; //다음 도시까지의 거리
        int oilCost; //해당 도시 주유소의 주유 비용

        public City(int index, int oilCost, int distance) {
            super();
            this.index = index;
            this.distance = distance;
            this.oilCost = oilCost;
        }

        @Override
        public int compareTo(City o) {
            return Integer.compare(this.oilCost, o.oilCost);
        }
    }

    public static City[] cities;
    public static long sum;
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        cities = new City[N];

        st = new StringTokenizer(in.readLine());
        for(int i = 0 ; i < N ; i++) {  //City 배열 생성
            if(i != N-1) {
                int distance = Integer.parseInt(st.nextToken());
                cities[i] = new City(i, 0, distance); //임시로 주유비는 0으로 대입
                continue;
            }

            cities[i] = new City (i, 0, 0); // 마지막 도시에서는 distance가 존재하지 않으므로 0 대입
        }

        st = new StringTokenizer(in.readLine());
        for(int i = 0 ; i < N ; i++) { //oilCost 대입
            cities[i].oilCost = Integer.parseInt(st.nextToken());
        }

        solution(0, N);
        System.out.println(sum);
    }

    private static void solution(int startIndex, int endIndex) {
        if(startIndex == endIndex) {
            return;
        }

        City[] copiedCities = Arrays.copyOfRange(cities, startIndex, endIndex); // startIndex 부터 endIndex 전까지 배열 복사
        Arrays.sort(copiedCities); //구간 내 도시 중 주유 금액으로 오름차순 정렬

        int lowestPriceCityIndex = copiedCities[0].index; //주유 금액이 가장 적은 도시의 인덱스
        int lowestPrice = copiedCities[0].oilCost; //주유 금액이 가장 적은 도시의 리터당 비용
        for(int i = lowestPriceCityIndex ; i < endIndex ; i++) { //주유 금액이 가장 적은 도시부터 end까지의 비용 계산
            sum += ((long)lowestPrice * cities[i].distance);
        }


        solution(startIndex, lowestPriceCityIndex); //출발지 ~ 주유 금액이 가장 적은 도시 구간에서의 최소 비용 계산

    }
}

