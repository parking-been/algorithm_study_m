package main.java.study.a02_tp.d01.p2470;
import java.io.*;
import java.util.*;

public class Main_김인송 {
    static int[] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(map);

        int s = 0, e = map.length - 1;
        int ans_s = 0, ans_e = 0;
        int minAbs = Integer.MAX_VALUE;

        while (s < e) {
            int sum = map[s] + map[e];

            if (Math.abs(sum) < minAbs) {
                minAbs = Math.abs(sum);
                ans_s = s;
                ans_e = e;
                if (sum == 0) break;
            }

            if (sum < 0) {
                s++;
            } else {
                e--;
            }
        }

        System.out.print(map[ans_s] + " " + map[ans_e]);
    }
}