package main.java.study.a06_mst.d02.p1944;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_조은진 {
    //이게 반드시 이동 해야 하는 조건이 없음->갔다가 멈춰도 된다.
	//복제를 하는 경우: 한 지점에서 2개 이상의 위치로 가는게 더 유리할 경우->뭔가 트리 같이 생기겠다
	//->tree? K개를 모두 연결하면서 가장 작은 루트를 찾는 거니까 MST겠다.
	//크루스칼로 풀어야 하나 아니면 프림으로 풀어야 하나?->시작 지점이 명확하기에  prim으로 풀어보자.
	//0. 시작의 edge를 pq에 넣는다.
	//1. pq에서 방문 안했으면서 가장 cost가 낮은 edge를 찾는다.
	//1-1. 만약 해당 edge를 못 찾을 경우->갈 수 없는 곳이 있는 것 -1을 출력한다.
	//2. 연결 되면서 cost 가장 작은node를 찾는다.
	//3. BFS를 돌리면서 현재 것에서 갈 수 있는 곳들을 찾는다(O(N^2))->이걸 하는 이유: 갈 수 있는지 여부를 다시 봐야 해서
	//4. minEdge를 갱신하는 edge들을 pq에 넣는다.
	
	
	static int[][] maze;
	static int N;
	static int keyCount=0;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int[] dist;
	
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
	
	static class Point{
		int x;
		int y;
		int index;
		public Point(int x, int y, int index) {
			this.x = x;
			this.y = y;
			this.index=index;
		}
		
	}
	
	public static void bfs(int startrow, int startcol) {
		boolean[][] visited=new boolean[N][N];
		Queue<Point> q=new ArrayDeque<>();
		q.add(new Point(startrow, startcol, 0));
		visited[startrow][startcol]=true;
		Arrays.fill(dist, Integer.MAX_VALUE);
		while(!q.isEmpty()) {
			Point p=q.poll();
			for(int i=0; i<4; i++) {
				int nx=p.x+dx[i]; int ny=p.y+dy[i];
				if(!visited[nx][ny]&&maze[nx][ny]!=-1) {
					if(maze[nx][ny]>0) dist[maze[nx][ny]]=p.index+1;
					visited[nx][ny]=true;
					q.offer(new Point(nx, ny, p.index+1));
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		maze=new int[N][N];
		int startrow=0; int startcol=0;
		
		int[][] keyList=new int[M+1][2];
		
		for(int i=0; i<N; i++) {
			String s=br.readLine();
			for(int j=0; j<N; j++) {
				Character c=s.charAt(j);
				if(c=='1') maze[i][j]=-1;//키와의 구분을 위해 벽을 -1로 바꿨다.
				else if(c=='S') {
					startrow=i;
					startcol=j;
				}
				else if(c=='K'){
					maze[i][j]=++keyCount;//map에 key의 index를 저장한다.(각 key를 식별하기 위해)
					keyList[keyCount][0]=i;
					keyList[keyCount][1]=j;
				}//1부터 key의 index를 매긴다.
			}
		}
		keyList[0][0]=startrow;
		keyList[0][1]=startcol;//0번은 시작을 저장한다.
		
		
		PriorityQueue<Edge> pq=new PriorityQueue<>();
		pq.add(new Edge(0,0));
		boolean[] visited=new boolean[M+1];
		int[] minEdge=new int[M+1];
		dist=new int[M+1];
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0]=0;
		
		int res=0;
		
		for(int i=0; i<=M; i++) {
			int minIndex=-1; int minNum=0;
			while(!pq.isEmpty()) {
				Edge e=pq.poll();
				if(!visited[e.to]) {
					minIndex=e.to;
					minNum=e.weight;
					break;
				}
			}
			if(minIndex==-1) {
				res=-1;
				break;
			}
			visited[minIndex]=true;
			res+=minNum;
			
			bfs(keyList[minIndex][0], keyList[minIndex][1]);
			//현재 위치로 시작해서 갈 수 있는 node들의 길이를 구한다.
			
			for(int j=1; j<=M; j++) {
				if(!visited[j]&&dist[j]<minEdge[j]) {
					minEdge[j]=dist[j];
					pq.add(new Edge(j, dist[j]));
				}
			}
		}
	
		System.out.println(res);
		br.close();
	}
}
