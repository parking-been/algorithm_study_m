package main.java.study.a01_ds.d02.p2504;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_채유빈 {
    public static boolean isSmallBracketPair(char a, char b) {
        return a == '(' && b == ')';
    }

    public static boolean isBigBracketPair(char a, char b) {
        return a == '[' && b == ']';
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        String str = br.readLine();

        // 아예 첫 입력이 잘못된 경우 바로 리턴
        // 한번에 처리 가능한지 확인해보기
        // if (str.charAt(0) == ')' || str.charAt(0) == ']') {
        //     System.out.println(0);
        //     return;
        // }

        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[') {
                stack.push(c);
            } else {  // c가 ) 또는 ]인 경우
                char temp1 = stack.peek();

                if (Character.isDigit(temp1)) {
                    // temp1이 숫자인 경우
                    int num = stack.pop() - '0';
                    char temp2 = stack.peek();

                    if (isSmallBracketPair(temp2, c)) {
                        stack.pop();
                        stack.push((char) (2 * num + '0'));

                        char ch1 = stack.pop();
                        char ch2 = stack.pop();

                        if (Character.isDigit(ch1) && Character.isDigit(ch2)) {
                            stack.push((char) ((ch1 - '0') + (ch2 - '0') + '0'));
                        } else {
                            stack.push(ch2);
                            stack.push(ch1);
                        }
                    } else if (isBigBracketPair(temp2, c)) {
                        stack.pop();
                        stack.push((char) (3 * num + '0'));

                        char ch1 = stack.pop();
                        char ch2 = stack.pop();

                        if (Character.isDigit(ch1) && Character.isDigit(ch2)) {
                            stack.push((char) (((ch1 - '0') + (ch2 - '0')) + '0'));
                        } else {
                            stack.push(ch2);
                            stack.push(ch1);
                        }
                    } else {
                        stack.push((char) (num + '0'));
                        stack.push(c);
                    }
                } else {  
                    // temp1이 괄호인 경우
                    if (isSmallBracketPair(temp1, c)) {
                        stack.pop();
                        stack.push('2');
                    } else if (isBigBracketPair(temp1, c)) {
                        stack.pop();
                        stack.push('3');
                    } else {
                        stack.push(c);
                    }
                }
            }
        }
    }
}