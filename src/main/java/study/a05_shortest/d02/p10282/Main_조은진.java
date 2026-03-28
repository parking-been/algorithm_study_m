package main.java.study.a05_shortest.d02.p10282;



import java.io.*;
import java.util.*;
import java.lang.*;


public class Main_조은진 {
	

	//다익스트라 알고리즘을 통해서 풀어보자->사유: 각 테스트 케이스에서 1개에서 뻗어나가기 때문에
	//최단 거리 알고리즘이 필요하다.->가장 shortestpath가 긴게 가장 마지막에 감염되는 컴퓨터가 가는 거기 때문
	//다익스트라 시간 복잡도 O(ElogV)->100*100000*100(테스트)->1억 시간 초과 될지도 모르겠는데. 일단 생각나는 방법이 없어서 해봄
	//가장 오래걸릴 경우->1000*10000->10의 7승->int 배열로 가능
	//마지막 감염된 컴퓨터는 한번 돌아야 할 것.
	
	
	static class ConnectInfo{
		int to;
		int time;
		
		public ConnectInfo( int to, int time) {
			this.to=to;
			this.time=time;
		}
	}
	
	static PriorityQueue<ConnectInfo> pq;
	static int N;
	static boolean[] visited;//방문한 컴퓨터 수를 세기 위한 거
	static int[] timeCount;
	static List<List<ConnectInfo>> graph;
	static int computerCount;
	
	
	
	static void dijckstra(int start) {
		List<ConnectInfo> curEdges=graph.get(start);
		ConnectInfo e;
		for(int i=0; i<curEdges.size(); i++) {
			pq.add(new ConnectInfo(curEdges.get(i).to, curEdges.get(i).time));//바로 연결되어 있는 거 넣기
		}
		timeCount[start]=0;
		visited[start]=true;
		
		while(!pq.isEmpty()) {
			e=pq.poll();
			if(!visited[e.to]) {++computerCount; visited[e.to]=true;}
			if(timeCount[e.to]>e.time) {
				timeCount[e.to]=e.time;
				curEdges=graph.get(e.to);
				for(int i=0; i<curEdges.size(); i++) {
					pq.add(new ConnectInfo(curEdges.get(i).to, e.time+curEdges.get(i).time));//해당 거리랑 연결되어 있는 거 넣기
				}
			}
		}
		
	}//시작점부터 pq에 넣어도 된다. 그랬다면 틀리지 않았을텐데.
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		StringBuilder sb=new StringBuilder();
		
		pq=new PriorityQueue<>((e1, e2)->Integer.compare(e1.time, e2.time));
		graph=new ArrayList<>();
		
		st.nextToken();
		int testCase=(int)st.nval;
		
		for(int t=1; t<=testCase; t++) {
			st.nextToken();
			N=(int)st.nval;
			
			computerCount=1;
			pq.clear();
			graph.clear();
			visited=new boolean[N+1];
			timeCount=new int[N+1];
			Arrays.fill(timeCount, Integer.MAX_VALUE);
			for(int i=0; i<N+1; i++) {
				graph.add(new ArrayList<>());
			}
			
			st.nextToken();
			int d=(int)st.nval;
			st.nextToken();
			int start=(int)st.nval;
			
			for(int i=0; i<d; i++) {
				st.nextToken();
				int a=(int)st.nval;
				st.nextToken();
				int b=(int)st.nval;
				st.nextToken();
				int s=(int)st.nval;
				
				graph.get(b).add(new ConnectInfo(a, s));
			}
			
			dijckstra(start);
			
			int maxTime=0;
			for(int i=1; i<=N; i++) {
				if(visited[i]&&timeCount[i]>maxTime) maxTime=timeCount[i];
			}
			
			sb.append(computerCount).append(" ").append(maxTime).append("\n");
			
		}
		System.out.println(sb);
		
		br.close();
	}
}
