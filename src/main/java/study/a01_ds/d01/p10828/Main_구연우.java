package main.java.study.a01_ds.d01.p10828;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_구연우 {

    public static int[] stack;
    public static int size = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        stack = new int[N];
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            switch (cmd) {
                case "push":
                    int val = Integer.parseInt(st.nextToken());
                    push(val);
                    break;

                case "pop":
                    sb.append(pop()).append("\n");
                    break;

                case "size":
                    sb.append(size()).append("\n");
                    break;

                case "empty":
                    sb.append(empty()).append("\n");
                    break;

                case "top":
                    sb.append(top()).append("\n");
                    break;
            }

        }
        System.out.print(sb);
        br.close();

    }

    public static void push(int x) {
        stack[size] = x;
        size++;
    }

    public static int pop() {
        if (size == 0) return -1;

        int ans = stack[size-1];
        size--;
        return ans;
    }

    public static int size() {
        return size;
    }

    public static int empty() {
        return (size == 0) ? 1 : 0;
    }

    public static int top() {
        if (size == 0) return -1;
        return stack[size-1];
    }
}
