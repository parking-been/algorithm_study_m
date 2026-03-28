package main.java.study.a08_im.d02.p15683;

import java.io.*;
import java.util.*;

public class Main_조은진 {
	//1. 어차피 5번은 정해져 있음->5번 범위를 다 하고 하자.
	//이거 조합 문제랑 비슷하게 풀면 된다. 백트래킹으로 가면 된다. 4^8*24?->약 157만
	static int[][] currentfield;//벽은 -1로 치환.현재 장소에 몇개의 cctv가 겹쳐 있는지 확인한다.
	static boolean[][] notRoom;//해당 장소에 cctv가 있는지 확인. cctv 있는 곳은 마크도 캔슬도 안할 거임
	static int N, M, cctvnum, minRes;
	static List<int[]> cctvInfo;//cctv 위치 기록. 0->row, 1->col, 2->몇번인지

	
	public static void mark(int cctvnum, int dir, int cctvRow, int cctvCol, int option) {
		
		boolean[] command=new boolean[4];
		
		switch(cctvnum) {
		case 1:
			command[dir]=true;
			break;
		case 2:
			if(dir/2==0) {
				command[0]=true;
				command[1]=true;
			}else {
				command[2]=true;
				command[3]=true;
			}
			break;
		case 3:
			// 0 2 0 3 1 2 1 3
			command[dir/2]=true;
			command[(dir%2)+2]=true;
			break;
		case 4:
			Arrays.fill(command, true);
			command[dir]=false;
			break;
		}
		
		if(command[0])forUp(option, cctvRow, cctvCol);
		if(command[1])forDown(option, cctvRow, cctvCol);
		if(command[2])forLeft(option, cctvRow, cctvCol);
		if(command[3])forRight(option, cctvRow, cctvCol);
	}

	
	public static void backtracking(int cnt) {
		if(cnt==cctvnum) {
			int curRes=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!notRoom[i][j]&&currentfield[i][j]==0)++curRes;
				}
			}
			minRes=Math.min(curRes, minRes);
			return;
		}
		int[] curCctv=cctvInfo.get(cnt);
		for(int i=0; i<4; i++) {
			mark(curCctv[2], i, curCctv[0], curCctv[1], 1);
			backtracking(cnt+1);
			mark(curCctv[2], i, curCctv[0], curCctv[1], -1);
		}
	}
	
	public static void forUp(int change, int row, int col) {
		for(int i=row-1; i>=0; i--) {
			if(currentfield[i][col]==-1)break;
			currentfield[i][col]+=change;
		}
	}
	
	public static void forDown(int change, int row, int col) {
		for(int i=row+1; i<N; i++) {
			if(currentfield[i][col]==-1)break;
			currentfield[i][col]+=change;
		}
	}
	
	public static void forLeft(int change, int row, int col) {
		for(int i=col-1; i>=0; i--) {
			if(currentfield[row][i]==-1)break;
			currentfield[row][i]+=change;
		}
	}
	
	public static void forRight(int change, int row, int col) {
		for(int i=col+1; i<M; i++) {
			if(currentfield[row][i]==-1)break;
			currentfield[row][i]+=change;
		}
	}
	
	public static void checkFive(List<int[]> fiveLocation) {
		for(int cindex=0; cindex<fiveLocation.size(); cindex++) {
			int[] cctvLoc=fiveLocation.get(cindex);
			int row=cctvLoc[0]; int col=cctvLoc[1];
			forUp(1, row, col);
			forDown(1, row, col);
			forLeft(1, row, col);
			forRight(1, row, col);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		st.nextToken();
		N=(int)st.nval;
		st.nextToken();
		M=(int)st.nval;
		
		currentfield=new int[N][M];
		notRoom=new boolean[N][M];
		List<int[]> fiveLocation=new ArrayList<>();
		cctvInfo=new ArrayList<>();
		minRes=Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				st.nextToken();
				int num=(int)st.nval;
				if(num>0) notRoom[i][j]=true;
				
				if(num==6) {
					currentfield[i][j]=-1;
				}else if(num==5) {
					fiveLocation.add(new int[] {i, j});
				}else if(num>0) {
					cctvInfo.add(new int[] {i, j, num});
					++cctvnum;
				}
			}
		}
		
		checkFive(fiveLocation);
		backtracking(0);
		
		
		System.out.println(minRes);
		
		br.close();
	}

}
