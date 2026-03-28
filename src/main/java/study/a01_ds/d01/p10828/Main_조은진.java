package main.java.study.a01_ds.d01.p10828;

import java.util.*;
import java.io.*;

public class Main_조은진 {
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st;
		String command;
		int element;
		
		int n=Integer.parseInt(br.readLine());
		
		Stack<Integer> stack=new Stack<>();
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			command=st.nextToken();
			switch(command){
			case "push":
				stack.push(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if(!stack.isEmpty()) {
					System.out.println(stack.pop());
				}else {
					System.out.println(-1);
				}
				break;
			case "size":
				System.out.println(stack.size());
				break;
			case "empty":
				if(stack.isEmpty()) {
					System.out.println(1);
				}else {
					System.out.println(0);
				}
				break;
			case "top":
				if(!stack.isEmpty()) {
					System.out.println(stack.peek());
				}else {
					System.out.println(-1);
				}
				break;
			}
		}
		
	}
}
