package main.java.study.a01_ds.d03.p1918;

import java.util.Scanner;
import java.util.Stack;

//사전 지식
//연산자 우선순위 : (, ) -> *, / -> +, -
//우선순위가 높은 연산일수록 후위 표기식에서 먼저 표기 되어야 한다

// 연산자를 stack에 넣되, 들어오는 연산자보다 우선순위가 높거나, 우선순위는 같지만 먼저 들어온 연산자는 먼저 표기해주어야 한다 => pop
// 후위 표기식으로 치환해 주어야 하기에 피연산자는 stack에 넣지 않고 바로바로 결과값에 붙여준다
// (은 우선순위가 제일 높지만, ) 사이에 있는 모든 연산자들의 순위를 높여주는 것이므로 바로 push. 반대로 )이 들어오면 안에 있는 연산자들은 모두 pop해주어 연산 순위를 높여준다
// 피연산자는 후위 표기식으로 stack에 넣지 않고 result에 append를 계속함

public class Main_정우재 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = sc.nextLine().toCharArray();

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i  = 0 ; i < input.length; i++){
            char now = input[i];

            switch (now) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!stack.isEmpty() && topIsMoreImportant(stack.peek(), now) ){ //현재 값보다 우선순위가 높은 애들은 다 pop
                        sb.append(stack.pop());
                    }
                    stack.push(now); //현재 값보다 우선순위가 높은 애들이 없어진 후에 push
                    break;
                case '(' : // (는 무조건 push
                    stack.push(now);
                    break;
                case ')' :
                    while(stack.peek() != '('){ // )는 () 사이에 있는 연산자들의 우선순위를 최우선으로 승격시키므로, ( 까지 있는 연산자들을 모두 pop
                        sb.append(stack.pop());
                    }
                    stack.pop(); // 남아있는 ( 제거
                    break;
                default : //피연산자들은 stack에 넣지 않고, result에 append
                    sb.append(now);

            }
        }

        while (!stack.isEmpty()){ //만약 남아있는 연산자가 있을 경우 모두 pop.   예) a + (b * c) => ()가 사라진 후에 a가 여전히 남아있음.
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }

    private static boolean topIsMoreImportant(char top, char now) { //stack에 들어있는 연산자들이 현재 값보다 우선순위가 높은 지 판단하는 함수
        int rankOfTop = getRank(top);
        int rankOfNow = getRank(now);
        return rankOfTop >= rankOfNow;
    }

    private static int getRank(char value) {
        int rank = 0; // 초기화
        switch(value) {
            case '+':
            case '-':
                rank = 1;
                break;
            case '*' :
            case '/' :
                rank = 2;
                break;
        }

        return rank;
    }
}
