package main.java.study.a03_gt.d03.p1941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_조은진 {
	static boolean[] isDasom;
	static int totalCnt;
	static boolean[] picked;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};

	
	public static void combination(int start, int cnt, int curDasom) {
		if(cnt==7) {
			if(curDasom>=4&&findPossible()) {
				++totalCnt;
			}
			return;
		}
		if(start<25) {
			picked[start]=true;
			if(isDasom[start]) combination(start+1, cnt+1, curDasom+1);
			else combination(start+1, cnt+1, curDasom);
			picked[start]=false;
			combination(start+1, cnt, curDasom);
		}
	}
	
	public static boolean findPossible() {
		boolean[] visited=new boolean[25];
		int start=0, count=1;
		Queue<Integer> q=new ArrayDeque<>();
		for(int i=0; i<25; i++) {
			if(picked[i]) {start=i; break;}
		}
		q.offer(start);
		visited[start]=true;
		while(!q.isEmpty()) {
			int cur=q.poll();
			int curR=cur/5;
			int curC=cur%5;
			for(int i=0; i<4; i++) {
				int nr=curR+dx[i];
				int nc=curC+dy[i];
				
				if(nr>=0&&nc>=0&&nr<5&&nc<5) {
					int temp=nr*5+nc;
					if(picked[temp]&&!visited[temp]) {
						visited[temp]=true;
						q.offer(temp);
						++count;
					}
				}
			}
		}
		return count==7;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		isDasom=new boolean[25];
		picked=new boolean[25];
		String s;
		totalCnt=0;
		for(int i=0; i<5; i++) {
			s=br.readLine();
			for(int j=0; j<5; j++) {
				if(s.charAt(j)=='S') {
					isDasom[i*5+j]=true;
				}
			}
		}
		combination(0,0,0);
		System.out.println(totalCnt);
		br.close();
	}
}
