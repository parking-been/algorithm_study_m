package main.java.study.a04_greedy.d02.p3109;

import java.io.*;
import java.util.*;

public class Main_조은진 {
	static int R, C;
	static boolean[][] isHouse;
	static boolean[][] visited;
	static int cnt;
	static int[] dx= {-1,0,1};
	
	public static void init() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		cnt=0;
		isHouse=new boolean[R][C];
		visited=new boolean[R][C];
		for(int i=0; i<R; i++) {
			String s=br.readLine();
			for(int j=0; j<C; j++) if(s.charAt(j)=='x') isHouse[i][j]=true;
		}
		br.close();
	}
	
	public static boolean findPipe(int row, int col) {
		if(col==C-1) {
			++cnt;
			return true;
		}
		int nx;
		for(int i=0; i<3; i++) {
			nx=row+dx[i];
			if(nx>=0&&nx<R&&!isHouse[nx][col+1]&&!visited[nx][col+1]) {
				visited[nx][col+1]=true;
				if(findPipe(nx, col+1)) return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		init();
		for(int i=0; i<R; i++) {
			findPipe(i, 0);
		}
		System.out.println(cnt);
	}
}
