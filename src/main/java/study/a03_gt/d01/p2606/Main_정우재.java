package main.java.study.a03_gt.d01.p2606;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main_정우재 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int computerCount = sc.nextInt();
        sc.nextLine();

        int combinationCount = sc.nextInt();
        sc.nextLine();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        boolean[] isInfected = new boolean[computerCount+1];

        for(int i = 0 ; i < computerCount+1 ; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0 ; i < combinationCount ; i++){
            int computerA = sc.nextInt();
            int computerB = sc.nextInt();
            sc.nextLine();

            graph.get(computerA).add(computerB);
            graph.get(computerB).add(computerA);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        isInfected[1] = true;

        int count =0;
        while (!queue.isEmpty()){
            int computer = queue.poll();

            for (int i = 0 ; i < graph.get(computer).size() ; i++) {
                int connectedComputer = graph.get(computer).get(i);
                if(!isInfected[connectedComputer]) {
                    queue.offer(connectedComputer);
                    isInfected[connectedComputer] = true;
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
