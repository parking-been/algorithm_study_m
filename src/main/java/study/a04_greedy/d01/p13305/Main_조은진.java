package main.java.study.a04_greedy.d01.p13305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_조은진 {
	static int N;
	static int[] prices;
	static int[] distances;
	static long res;//res는 범위 벗어날 수 있음!->계산할 때 형변환 주의
	
	//현재 살 수 있는 최저가로 계속 가져가면 된다.
	public static void findMin() {
		int curMin=prices[0];
		for(int i=0; i<N-1; i++) {
			res+=(long)curMin*(long)distances[i];
			if(prices[i+1]<curMin) curMin=prices[i+1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		st.nextToken();
		N=(int) st.nval;
		prices=new int[N];
		distances=new int[N-1];
		res=0;
		for(int i=0; i<N-1; i++) {
			st.nextToken();
			distances[i]=(int) st.nval;
		}
		for(int i=0; i<N; i++) {
			st.nextToken();
			prices[i]=(int) st.nval;
		}
		findMin();
		System.out.println(res);
		br.close();
	}
}
