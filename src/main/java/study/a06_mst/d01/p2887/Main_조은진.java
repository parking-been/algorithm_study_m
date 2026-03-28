package main.java.study.a06_mst.d01.p2887;

import java.util.*;
import java.io.*;

public class Main_조은진 {
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int dist;
		
		Edge(int start, int end, int dist){
			this.start=start;
			this.end=end;
			this.dist=dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}
		
	}
	
	static class Point{
		int index;
		int x;
		int y;
		int z;
		
		Point(int index, int x, int y, int z){
			this.index=index;
			this.x=x;
			this.y=y;
			this.z=z;
		}
	}
	
	static int[] parents;
	static Edge[] edges;
	static Point[] locationsX;
	static Point[] locationsY;
	static Point[] locationsZ;
	static int N;
	
	static int findParent(int a) {
		if(parents[a]<0) return a;
		return parents[a]=findParent(parents[a]);
	}
	
	
	static boolean union(int a, int b) {
		int pa=findParent(a);
		int pb=findParent(b);
		if(pa==pb) return false;
		if(parents[pa]<parents[pb]) {
			parents[pa]+=parents[pb];
			parents[pb]=pa;
		}else {
			parents[pb]+=parents[pa];
			parents[pa]=pb;
		}
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		st.nextToken();
		N=(int)st.nval;
		locationsX=new Point[N];
		locationsY=new Point[N];
		locationsZ=new Point[N];
		parents=new int[N];
		Arrays.fill(parents,-1);
		for(int i=0; i<N; i++) {
			st.nextToken();
			int x=(int)st.nval;
			st.nextToken();
			int y=(int)st.nval;
			st.nextToken();
			int z=(int)st.nval;
			//이거 x y z 중에 자신보다 큰 애에 대해 없으면
			Point p=new Point(i, x, y, z);
			locationsX[i]=p;
			locationsY[i]=p;
			locationsZ[i]=p;
		}
		
		Arrays.sort(locationsX, (o1, o2)->Integer.compare(o1.x, o2.x));
		Arrays.sort(locationsY, (o1, o2)->Integer.compare(o1.y, o2.y));
		Arrays.sort(locationsZ, (o1, o2)->Integer.compare(o1.z, o2.z));
		
		Edge[] edges=new Edge[3*(N-1)];
		int idx=0;
		
		for(int i=0; i<N-1; i++) {
			int dist=Math.abs(locationsX[i].x-locationsX[i+1].x);
			edges[idx++]=new Edge(locationsX[i].index, locationsX[i+1].index, dist);
		}
		
		for(int i=0; i<N-1; i++) {
			int dist=Math.abs(locationsY[i].y-locationsY[i+1].y);
			edges[idx++]=new Edge(locationsY[i].index, locationsY[i+1].index, dist);
		}
		
		for(int i=0; i<N-1; i++) {
			int dist=Math.abs(locationsZ[i].z-locationsZ[i+1].z);
			edges[idx++]=new Edge(locationsZ[i].index, locationsZ[i+1].index, dist);
		}
		
		Arrays.sort(edges);
		
		int cnt=0; long res=0;
		for(int i=0; i<3*N; i++) {
			Edge e=edges[i];
			if(union(e.start, e.end)) {
				res+=e.dist;
				if(++cnt==N-1) break;
			}
		}
		System.out.println(res);
		br.close();
	}
}
