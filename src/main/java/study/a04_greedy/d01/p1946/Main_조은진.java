package main.java.study.a04_greedy.d01.p1946;


import java.io.*;
import java.util.*;

public class Main_조은진 {
	static int N;
	static int[][] ranks;//0번은 서류 점수, 1번은 면접 점수
	static int cnt;
	
	//정렬로 인해 이미 이전 index 애들 보다 서류 등수가 높은게 확정이다.
	//만약, 이전에 있는 애들 중에 자신보다 면접 등수가 높은 사람이 있다면,
	//그 사람은 나보다 서류, 면접 등수가 둘 다 높다.
	//계속해서 이전 애들의 면접 등수 최소를 갱신하면서 카운트를 센다.
	public static void count() {
		int curmin=ranks[0][1];
		for(int i=1; i<N; i++) {
			if(ranks[i][1]<curmin) {
				++cnt;
				curmin=ranks[i][1];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		StringBuilder sb=new StringBuilder();
		st.nextToken();
		int testCase=(int)st.nval;
		
		for(int t=0; t<testCase; t++) {
			st.nextToken();
			N=(int)st.nval;
			ranks=new int[N][2];
			cnt=1;
			for(int i=0; i<N; i++) {
				st.nextToken();
				ranks[i][0]=(int)st.nval;
				st.nextToken();
				ranks[i][1]=(int)st.nval;
			}
			
			//서류점수 등수를 기준으로 정렬한다.
			Arrays.sort(ranks, (o1,o2)-> Integer.compare(o1[0], o2[0]));
			count();
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
