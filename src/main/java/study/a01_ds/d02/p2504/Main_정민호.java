package main.java.study.a01_ds.d02.p2504;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 1. 여는 괄호를 스택에 넣는다.
// 2. 닫는 괄호를 만나면 여는 괄호까지 POP하여 계산을 시작한다.
//  2-1. 올바른 여는 괄호를 못 찾으면 0을 반환하고 종료한다.
// 3. 닫는 괄호부터 여는 괄호 사이에 숫자가 있다면, 여는 괄호 종류 * 숫자 합을 계산한다
// 4. 계산 결과를 다시 스택에 넣는다.
// 5. 2~4를 반복하면 결과적으로 스택에 숫자만 남는다.
    // 괄호가 남아있다면 0을 반환하고 종료한다.
// 6. 남은 숫자를 모두 더하여 출력한다.

public class Main_정민호 {
    static Stack<Integer> stack = new Stack<>();
    static Stack<Integer> calStack = new Stack<>();

    static int popUntilSmall() {
        int num = 0;
        while(!stack.isEmpty()) {
            int top = stack.pop();
            if (top == 0) {
                if (num == 0) {
                    num = 2;
                }
                else {
                    num *= 2;
                }
                break;
            }
            else if (top == 1) return -1;
            else num += top;
        }
        return num;
    }

    static int popUntilBig() {
        int num = 0;
        while(!stack.isEmpty()) {
            int top = stack.pop();
            if (top == 1) {
                if (num == 0) {
                    num = 3;
                }
                else {
                    num *= 3;
                }
                break;
            }
            else if (top == 0) return -1;
            else num += top;
        }
        return num;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = 0;
        for (char word: br.readLine().toCharArray()) {
            int result = 0;
            if (word == '(' || word == '[') {
                stack.add(word == '(' ? 0 : 1);
                continue;
            }

            if (word == ')') {
                result = popUntilSmall();
                stack.add(result);
            } else if (word == ']') {
                result = popUntilBig();
                stack.add(result);
            }
            if (result == -1) {
                System.out.println(0);
                return;
            }
        }
        for (int s: stack) {
            if (s < 2) {
                total = 0;
                break;
            }
            else total += s;
        }
        System.out.println(total);
    }
}
