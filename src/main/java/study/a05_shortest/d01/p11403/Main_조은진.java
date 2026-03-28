package main.java.study.a05_shortest.d01.p11403;

import java.util.*;
import java.io.*;

public class Main_조은진 {
	static int[][] res;
	static int[][] adjMatrix;
	static Deque<Integer> stack;
	static int N;
	
	public static void dfs(int start) {
		stack.push(start);
		int curnum;
		while(!stack.isEmpty()) {
			curnum=stack.pop();
			for(int i=0; i<N; i++) {
				if(adjMatrix[curnum][i]==1&&res[start][i]!=1) {
					res[start][i]=1;
					stack.push(i);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		st.nextToken();
		N=(int)st.nval;
		res=new int[N][N];
		adjMatrix=new int[N][N];
		stack=new ArrayDeque<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				st.nextToken();
				adjMatrix[i][j]=(int)st.nval;
			}
		}
		
		for(int i=0; i<N; i++) dfs(i);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
		
		br.close();
	}
}
