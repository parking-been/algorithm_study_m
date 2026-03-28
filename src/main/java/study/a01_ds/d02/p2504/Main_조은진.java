package main.java.study.a01_ds.d02.p2504;

import java.io.*;
import java.util.*;

public class Main_조은진 {
	public static void main (String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		Stack<Character> stack=new Stack<>();
		Stack<Integer> num_stack=new Stack<>();
		char c, h, prev='0';
		int res=1, total=0, temp, temp2;
		for(int i=0; i<s.length(); i++) {
			c=s.charAt(i);
			
			switch(c) {
			case '(':
				stack.push(c);
				break;
			case '[':
				stack.push(c);
				break;
			case ')':
				h=stack.pop();
				if(h!='(') {
					res=0;
					break;
				}
				if(num_stack.isEmpty()) {
					num_stack.push(2);
				}else {
					temp=num_stack.pop();
					if(prev==')'||prev==']') {
						num_stack.push(temp*2);//중첩되는 경우 *2
					}else {
						if(num_stack.isEmpty()) {
							num_stack.push(temp+2);
						}else {
							temp2=num_stack.pop();
							num_stack.push((temp+temp2)*2);
						}
					}
				}
				break;
			case ']':
				h=stack.pop();
				if(h!='[') {
					res=0;
					break;
				}
				if(num_stack.isEmpty()) {
					num_stack.push(3);
				}else {
					temp=num_stack.pop();
					if(prev==')'||prev==']') {
						num_stack.push(temp*3);
					}else {
						if(num_stack.isEmpty()) {
							num_stack.push(temp+3);
						}else {
							temp2=num_stack.pop();
							num_stack.push((temp+temp2)*3);
						}
					}
				}
				break;
			}
			prev=c;// 이전을 저장한다.
		}
		while(!num_stack.isEmpty()) {
			total+=num_stack.pop();
		}
		System.out.println(total);
	}
}
