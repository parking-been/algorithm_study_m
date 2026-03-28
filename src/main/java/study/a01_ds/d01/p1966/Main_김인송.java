package main.java.study.a01_ds.d01.p1966;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.StringTokenizer;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main_김인송 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayDeque<int[]> q = new ArrayDeque<>();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int max = 0;

            for(int i = 0; i < N; i++){
                int n = Integer.parseInt(st.nextToken());
                q.add(new int[]{n, i});
                max = Math.max(max, n);
            }

            int result = 1;
            while(!q.isEmpty()){
                int[] p = q.poll();
                int pri = p[0];
                int idx  = p[1];
                System.out.println(pri + " " + max);

                if (max == pri && idx != M) result++;
                else if(max == pri) {
                    System.out.println(result);
                    return;
                }
                else q.add(p);

                for(int[] temp : q) {
                    max = Math.max(max, temp[0]);
                }
            }


        }
    }
}