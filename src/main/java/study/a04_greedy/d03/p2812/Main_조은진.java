package main.java.study.a04_greedy.d03.p2812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_조은진 {
	static String s;
	static boolean[] isRemoved;
	static Deque<Integer> stack;
	static int N, K;
	
	
	public static void greedy() {
		int curk=0;
		for(int i=0; i<N; i++) {
			int curNum=(int)s.charAt(i)-'0';
			while(!stack.isEmpty()&&stack.peekLast()<curNum&&curk<K) {
				stack.pollLast();
				++curk;
			}
			stack.addLast(curNum);
		}
		while(curk<K) {
			stack.pollLast();
			++curk;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		s=br.readLine();
		stack=new ArrayDeque<>();
		greedy();
		int size=stack.size();
		for(int i=0; i<size; i++) {
			System.out.print(stack.pollFirst());
		}
		br.close();
	}
}
