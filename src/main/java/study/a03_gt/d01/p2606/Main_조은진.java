package main.java.study.a03_gt.d01.p2606;

import java.util.*;
import java.io.*;
public class Main_조은진 {
	public static int countVirusBFS(boolean[][]branches, int n) {
		boolean[] visited=new boolean [n+1];
		Queue<Integer> queue=new ArrayDeque<>();
		int virusCount=0;
		int curNode;
		
		queue.offer(1);
		visited[1]=true;
		while(!queue.isEmpty()) {
			curNode=queue.poll();
			
			for(int i=1; i<=n; i++) {
				if(branches[curNode][i] && !visited[i]) {
					queue.offer(i);
					visited[i]=true;
					++virusCount;
				}
			}
		}
		
		return virusCount;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		st.nextToken();
		int n=(int)st.nval;
		st.nextToken();
		int branchCount=(int)st.nval;
		int node1, node2;
		
		boolean [][] branches=new boolean[n+1][n+1];
		for(int i=0; i<branchCount; i++) {
			st.nextToken();
			node1=(int)st.nval;
			st.nextToken();
			node2=(int)st.nval;
			branches[node1][node2]=true;
			branches[node2][node1]=true;
		}
		
		System.out.println(countVirusBFS(branches, n));
	}
}
