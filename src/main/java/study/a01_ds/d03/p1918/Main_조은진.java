package main.java.study.a01_ds.d03.p1918;

import java.util.*;
import java.io.*;

public class Main_조은진 {
	//-2: ( -1: ) 1:+- 2:*/ 0: 그외 피연산자
		public static int find_type(Character c) {
			switch(c) {
			case '(':
				return -2;
			case ')':
				return -1;
			case '*':
			case'/':
				return 2;
			case '+':
			case '-':
				return 1;
			}
			return 0;
		}
		
		public static void main(String[] args) throws IOException {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			Stack<Character> stack=new Stack<>();
			StringBuilder sb=new StringBuilder();
			String s=br.readLine();
			Character cur, temp;
			int cur_type, prev_type;
			for(int i=0; i<s.length(); i++) {
				cur=s.charAt(i);
				cur_type=find_type(cur);
			
				if(cur_type==0) {//피연산자의 경우
					sb.append(cur);
				}else if(cur_type==-1){//괄호가 닫힐 때
					while(!stack.isEmpty()&&(temp=stack.pop())!='(') {
						sb.append(temp);
					}
				}else{
					if(!stack.isEmpty()&&cur_type>0) {
						temp=stack.peek();
						prev_type=find_type(temp);
						if(prev_type>=cur_type) {
							while(!stack.isEmpty()&&(temp=stack.peek())!='(') {
								if(find_type(temp)>=cur_type) {
									sb.append(stack.pop());
								}else {
									break;
								}
							}
						}//새로온 애들이 이전 연산자와 같거나 낮은 경우->pop 해준다.
					}
					
					stack.add(cur);
				}
			}
			
			while(!stack.isEmpty()) {
				if((temp=stack.pop())!='('){
					sb.append(temp);
				}
			}
			System.out.println(sb);
			br.close();
		}
}
