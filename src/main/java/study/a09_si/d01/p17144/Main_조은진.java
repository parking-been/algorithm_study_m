package main.java.study.a09_si.d01.p17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_조은진 {
   static int R, C, T;
	static int[][] currentfield;
	static int[]dx= {-1,1,0,0};
	static int[]dy= {0,0,-1,1};
	static int cleanerrow;//공기 청정기의 아래 부분을 가리키는 거
	
	static boolean oob(int row, int col) {
		if(row>=0&&row<R&&col>=0&&col<C) return false;
		return true;
	}
	
	static void spread() {
		
		int[][] temp=new int[R][C];
		//일단 오리지널 넘버를 토대로 줄어준걸 더함. 옆에 있는 것도 더함. 어차피 original numbers를 기준으로 누적
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(currentfield[i][j]<=0) continue;
				int cnt=0;
				for(int k=0; k<4; k++) {
					int nx=i+dx[k]; int ny=j+dy[k];
					if(!oob(nx, ny)&&currentfield[nx][ny]!=-1) {
						++cnt;
						temp[nx][ny]+=(currentfield[i][j]/5);
					}
				}
				temp[i][j]+=(currentfield[i][j]-cnt*(currentfield[i][j]/5));
			}
		}
	
		//더해 준 것들을 다 옯긴다. 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(currentfield[i][j]!=-1)currentfield[i][j]=temp[i][j];
			}
		}
		
	}//1000*2500*4*2=2000만 정도
	
	static void clean() {
		int prev=currentfield[cleanerrow-1][1];
		currentfield[cleanerrow-1][1]=0;
		int current=0;
		for(int i=2; i<C; i++) {
			current=currentfield[cleanerrow-1][i];
			currentfield[cleanerrow-1][i]=prev;
			prev=current;
		}
		for(int i=cleanerrow-2; i>=0; i--) {
			current=currentfield[i][C-1];
			currentfield[i][C-1]=prev;
			prev=current;
		}
		for(int i=C-2; i>=0; i--) {
			current=currentfield[0][i];
			currentfield[0][i]=prev;
			prev=current;
		}
		for(int i=1; i<cleanerrow-1; i++) {
			current=currentfield[i][0];
			currentfield[i][0]=prev;
			prev=current;
		}
		
		prev=currentfield[cleanerrow][1];
		currentfield[cleanerrow][1]=0;
		for(int i=2; i<C; i++) {
			current=currentfield[cleanerrow][i];
			currentfield[cleanerrow][i]=prev;
			prev=current;
		}
		for(int i=cleanerrow+1; i<R; i++) {
			current=currentfield[i][C-1];
			currentfield[i][C-1]=prev;
			prev=current;
		}
		for(int i=C-2; i>=0; i--) {
			current=currentfield[R-1][i];
			currentfield[R-1][i]=prev;
			prev=current;
		}
		
		for(int i=R-2; i>cleanerrow; i--) {
			current=currentfield[i][0];
			currentfield[i][0]=prev;
			prev=current;
		}
		
		
	}//1000*75->75000 정도
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		st.nextToken();
		R=(int)st.nval;
		
		st.nextToken();
		C=(int)st.nval;
		
		st.nextToken();
		T=(int)st.nval;
		
		currentfield=new int[R][C];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				st.nextToken();
				currentfield[i][j]=(int)st.nval;
				if(currentfield[i][j]==-1) cleanerrow=i;
			}
		}
		
		for(int i=0; i<T; i++) {
			spread();
			clean();
		}
		
		
		int res=0;
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(currentfield[i][j]!=-1)res+=currentfield[i][j];
			}
		}
		
		System.out.println(res);
		
		br.close();
	}
}
