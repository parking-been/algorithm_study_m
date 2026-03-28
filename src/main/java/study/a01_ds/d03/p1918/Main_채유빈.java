package main.java.study.a01_ds.d03.p1918;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// 1. 결국 연산자 간의 우선순위를 따져봐야 하는 문제 -> 연산자를 스택으로 관리
// 2. 연산자 우선순위는 (, ) > *, / > +, -
// 3. 우선순위가 자신보다 높은 연산자가 스택에 기록되어 있으면, 해당 연산자를 먼저 pop하고 push해야 한다.
// 3-1. A+B*C => ABC*+ (*가 +보다 우선순위가 높으므로 +보다 먼저 기록되어야 한다.)
// 4. +) 괄호 안의 연산자는 괄호 밖의 연산자보다 우선순위가 높으므로 해당 부분 처리 필요
public class Main_채유빈 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        String result = "";
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : expression.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                result += c;
            } else {
                if (c == '(') {
                    stack.push(c);
                } else if (c == '*' || c == '/') {
                    // *, / 일 때만 pop (순서는 지켜야 하므로)
                    while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                        result += stack.pop();
                    }

                    stack.push(c);
                } else if (c == '+' || c == '-') {
                    // +, -는 우선순위가 가장 낮으니 스택에 있는 연산자들을 먼저 pop해야 함
                    // 그러나 앞에 (가 있다면 이는 괄호 안의 연산자라는 것이고, 우선순위가 가장 높다는 뜻이므로
                    // 더이상 pop을 진행하지 않고 push
                    while (!stack.isEmpty() && (stack.peek() != '(')) {
                        result += stack.pop();
                    }
                    
                    stack.push(c);
                } else if (c == ')') {
                    // 괄호는 가장 우선순위가 높으므로 스택에 있는 연산자를 모두 pop
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        result += stack.pop();
                    }
                    // '(' 꺼내기
                    stack.pop();  
                }   
            }
        }

        // 남아있는 연산자들 처리
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        System.out.println(result);
        br.close();
    }
}
