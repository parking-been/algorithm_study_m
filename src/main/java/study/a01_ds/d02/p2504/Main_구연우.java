package main.java.study.a01_ds.d02.p2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_구연우 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        int result = 0;
        int temp = 1;

        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);

            if (now == '(') {
                stack.push(now);
                temp *= 2;
            } else if (now == '[') {
                stack.push(now);
                temp *= 3;
            } else if (now == ')') {
                try {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        System.out.println(0);
                        return;
                    }
                    stack.pop();
                    if (str.charAt(i-1) == '(') {
                        result += temp;
                    }
                    temp /= 2;
                } catch (Exception e) {
                    System.out.println(0);
                    return;
                }
            } else if (now == ']') {
                try {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        System.out.println(0);
                        return;
                    }
                    stack.pop();
                    if (str.charAt(i-1) == '[') {
                        result += temp;
                    }
                    temp /= 3;
                } catch (Exception e) {
                    System.out.println(0);
                    return;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }

    }

}
