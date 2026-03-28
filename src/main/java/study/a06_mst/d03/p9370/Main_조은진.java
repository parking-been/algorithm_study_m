package main.java.study.a06_mst.d03.p9370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_조은진 {
    // 시작 지점이 있으니까 다익스트라로 가도 될 거 같다.
	// 그 visited 배열을 두고 g와 h 사이의 다리로 갱신되면 false->true로 해야 하나
	// 이미 visited 된 거->결정 되서 더 이상 정할 필요 없음
	//visited 안 된거->이걸 갱신하는 visited의 상태에 의해 true랑 false가 정해진다.
	// 이게 일반적으로 다익스트라가(pq 쓸 때) O(ElogV)->약 200만*100->2억?->3초니까 할 만할 듯
	static class Node{
		int to;
		int weight;
		Node next;
		
		public Node(int to, int weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
		
		
	}
	
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		StringBuilder sb=new StringBuilder();
		st.nextToken();
		int testCase=(int)st.nval;
		for(int t=1; t<=testCase; t++) {
			st.nextToken();
			int N=(int)st.nval;
			st.nextToken();
			int M=(int)st.nval;
			st.nextToken();
			int T=(int)st.nval;
			
			boolean[] isValidDest=new boolean[N+1];
			boolean[] isDest=new boolean[N+1];
			boolean[] visited=new boolean[N+1];
			Node[] adjList=new Node[N+1];
			int[] minEdge=new int[N+1];
			Arrays.fill(minEdge, Integer.MAX_VALUE);
			
			st.nextToken();
			int S=(int)st.nval;
			st.nextToken();
			int G=(int)st.nval;
			st.nextToken();
			int H=(int)st.nval;
			
			for(int i=0; i<M; i++) {
				st.nextToken();
				int a=(int)st.nval;
				st.nextToken();
				int b=(int)st.nval;
				st.nextToken();
				int d=(int)st.nval;
				adjList[a]=new Node(b, d, adjList[a]);
				adjList[b]=new Node(a, d, adjList[b]);
			}
			
			for(int i=0; i<T; i++) {
				st.nextToken();
				int a=(int)st.nval;
				isDest[a]=true;
			}
			
			PriorityQueue<Edge> pq=new PriorityQueue<>();
			pq.offer(new Edge(S, 0));
			minEdge[S]=0;
			while(!pq.isEmpty()) {
				Edge e=pq.poll();
				if(visited[e.to]) continue;
				visited[e.to]=true;
				for(Node temp=adjList[e.to]; temp!=null; temp=temp.next) {
					if(!visited[temp.to]&&minEdge[temp.to]>minEdge[e.to]+temp.weight) {
						isValidDest[temp.to]=isValidDest[e.to];
						if((temp.to==G&&e.to==H)||(temp.to==H&&e.to==G)) {
							isValidDest[temp.to]=true;
						}
						minEdge[temp.to]=minEdge[e.to]+temp.weight;
						pq.offer(new Edge(temp.to, minEdge[temp.to]));
					}
					else if(minEdge[temp.to]==minEdge[e.to]+temp.weight) {
						if(isValidDest[e.to]) {
							isValidDest[temp.to]=isValidDest[e.to];
						}
						if((temp.to==G&&e.to==H)||(temp.to==H&&e.to==G)) {
							isValidDest[temp.to]=true;
						}
					}
				}
			}
			
			for(int i=0; i<N+1; i++) {
				if(isValidDest[i]&&isDest[i]) System.out.print(i+" ");
			}
			System.out.println();
		}
		
		br.close();
		
	}
}
