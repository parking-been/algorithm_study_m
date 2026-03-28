package main.java.study.a01_ds.d01.p1966;

import java.util.*;

public class Main_정우재 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        for(int testCase = 0 ; testCase < T ; testCase++){
            int docsCount = sc.nextInt();
            int targetLocation = sc.nextInt();
            ArrayList<Integer> priority = new ArrayList<>();

            Queue<Integer> queue = new LinkedList<>();
            sc.nextLine();

            for(int i = 0 ; i < docsCount; i++){
                int priorityValue = sc.nextInt();
                queue.add(priorityValue);
                priority.add(priorityValue);
            }

            Collections.reverse(priority);
            int max = priority.getFirst();
            int order = 1;

            while(!queue.isEmpty()){
                if(queue.peek() == max ) { //맨 앞이 max인 경우
                    if(targetLocation != 0) {
                        queue.poll();
                        order++;
                        targetLocation--;
                        priority.removeFirst();
                        max = priority.getFirst();
                        continue;
                    }
                    break;
                }

                //맨 앞이 맥스가 아닌 경우
                int priorityNum = queue.poll();
                queue.add(priorityNum);
                if(targetLocation != 0) {
                    targetLocation--;
                }
                else {
                    targetLocation = queue.size();
                }
            }

            System.out.println(order);
        }
    }
}
