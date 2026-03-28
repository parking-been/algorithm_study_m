package main.java.study.a05_shortest.d01.p1389;

import java.util.*;
import java.io.*;

public class Main_조은진 {
	static List<List<Integer>> graph;
	static Queue<int[]> queue;
	static int min, minIndex, N;
	
	static void bfs(int start) {
		queue.clear();
		boolean[] visited=new boolean[N+1];
		int count=0;
		int[] curNode;
		
		List<Integer> friends;
		queue.offer(new int[] {start, 0});
		visited[start]=true;
		
		while(!queue.isEmpty()) {
			curNode=queue.poll();
			friends=graph.get(curNode[0]);
			for(int i=0; i<friends.size(); i++) {
				if(!visited[friends.get(i)]) {
					visited[friends.get(i)]=true;
					count+=curNode[1]+1;
					queue.offer(new int[] {friends.get(i), curNode[1]+1});
				}
			}
		}
		
		if(min>count) {
			min=count;
			minIndex=start;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		st.nextToken();
		
		N=(int) st.nval;
		st.nextToken();
		int M=(int) st.nval;
		
		queue=new ArrayDeque<>();
		//N이 100 이하이고 그래프에 대해서는 조회만 하니까 list로 그래프 구현
		graph=new ArrayList<>();
		for(int i=0; i<N+1; i++) graph.add(new ArrayList<>());
		
		for(int i=0; i<M; i++) {
			st.nextToken();
			int friend1=(int) st.nval;
			st.nextToken();
			int friend2=(int) st.nval;
			
			//무방향 그래프임으로 양쪽 다 연결
			graph.get(friend1).add(friend2);
			graph.get(friend2).add(friend1);
		}
		
		min=Integer.MAX_VALUE;
		minIndex=0;
		for(int i=1; i<=N; i++) {
			bfs(i);
		}
		System.out.println(minIndex);
		
		br.close();
		
	}

}
