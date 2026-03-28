package main.java.study.a07_dp.d02.p2302;

import java.io.*;
import java.util.*;

public class Main_조은진 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		st.nextToken();
		int N=(int)st.nval;
		
		boolean[] isVip=new boolean[N+1];
		int[][] mem=new int[N+1][2];
		
		
		st.nextToken();
		int vipnum=(int)st.nval;
		
		List<Integer>mullist=new ArrayList<>();
		
		for(int i=0; i<vipnum; i++) {
			st.nextToken();
			int num=(int)st.nval;
			isVip[num]=true;
		}
		
		if(N==1) {
			System.out.println(1);
			return;
		}//1이 마지막인 경우 그냥 출력해준다.
		
		mem[1][0]=0;
		mem[1][1]=1;//1이 마지막인 경우
		
		mem[2][0]=1;
		mem[2][1]=1;//2가 마지막인 경우
		
		
		for(int i=3; i<=N; i++) {
			mem[i][0]=mem[i-2][0]+mem[i-2][1];//i-1 마지막으로 끝남->i-1에는 i가 들어가야 한다.(정해짐)->나머지는 i-2번째 숫자
			mem[i][1]=mem[i-1][0]+mem[i-1][1];//i로 마지막으로 끝남->앞에 애들 경우의 수만 잘 구해주면 된다.
		}//모든 경우의 수를 구한다.
		
		int res=1; int cnt=0;
		for(int i=1; i<=N; i++) {
			if(isVip[i]) {
				if(cnt>1) res*=(mem[cnt][0]+mem[cnt][1]);//cnt가 0인 경우와 1인 경우는 의미 없음.
				cnt=0;
			}else {
				++cnt;
			}
		} //끊어서 부분의 경우의 수들을 곱해준다.
		if(cnt>1) res*=(mem[cnt][0]+mem[cnt][1]);//마지막 마무리 처리
		
		
		System.out.println(res);
		br.close();
				
	}	
}
