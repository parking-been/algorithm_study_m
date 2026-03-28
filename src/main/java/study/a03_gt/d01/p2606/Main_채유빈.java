package main.java.study.a03_gt.d01.p2606;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_채유빈 {
    static boolean[] infected;
    static ArrayList<ArrayList<Integer>> connection = new ArrayList<>();
    static int count = 0;

    public static void dfs(int computer) {
        for (int nextComputer : connection.get(computer)) {
            if (infected[nextComputer]) continue;

            infected[nextComputer] = true;
            count++;

            dfs(nextComputer);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int computerCount = Integer.parseInt(br.readLine());
        int computerPairCount = Integer.parseInt(br.readLine());

        infected = new boolean[computerCount];

        for (int i = 0; i < computerCount; i++) {
            connection.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < computerPairCount; i++) {
            st = new StringTokenizer(br.readLine());
            int computer1 = Integer.parseInt(st.nextToken()) - 1;
            int computer2 = Integer.parseInt(st.nextToken()) - 1;

            // 양방향 연결
            connection.get(computer1).add(computer2);
            connection.get(computer2).add(computer1);
        }

        infected[0] = true;
        dfs(0);

        System.out.print(count);
    }
}
