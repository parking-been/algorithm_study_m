package main.java.study.a01_ds.d01.p1966;
import java.util.*;

public class Main_박다빈 {
    static int N;
    static int M;
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t = 0;t<T;t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            int[] arr = new int[N];
            for(int i=0;i<N;i++) {
                int prior = sc.nextInt();
                arr[i] = prior;
                q.offer(new int[] {i,prior});
            }

            Arrays.sort(arr);

            while(!q.isEmpty()) {
                int[] tmp = q.poll();
                //if (tmp[1]==)
            }


        }



    }

}
