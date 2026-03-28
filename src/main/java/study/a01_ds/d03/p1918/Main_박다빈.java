package main.java.study.a01_ds.d03.p1918;
import java.io.*;
import java.util.*;
/*
* 1. 알파벳 -> 바로 sb에 붙임
* 2. 연산자 -> 스텍에 넣되, 넣기전에 스택 top 연산자가 나보다 우선수누이가 높거나 같으면 먼저 빼서 sb 출력
* 3. 괄호 : (는 스택에 그냥 넣음
*           ) 를 만나면 ( 나올 때까지 스택에서 빼서 출력하고 (는 버린다.
*
* */

public class Main_박다빈 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();


        String str = br.readLine().trim();
        for(int i=0;i<str.length();i++) {
            char tmp = str.charAt(i);

            switch(tmp){
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!stack.isEmpty() && priority(stack.peek())>=priority(tmp)) {
                        sb.append(stack.pop());
                    }
                    stack.add(tmp);
                    break;
                case '(':
                    stack.add(tmp);
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peek()!='(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(tmp);
            }

        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }

    public static int priority(char operator) {
        if(operator =='+' || operator =='-') {
            return 1;
        }
        else if(operator =='*'||operator=='/') {
            return 2;
        }
        return -1;
    }


}