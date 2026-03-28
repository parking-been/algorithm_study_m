package main.java.study.a02_tp.d02.p7453;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_조은진 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		st.nextToken();
		int n=(int)st.nval;
		int[][]arrs=new int[4][n];
		int [][] sums=new int[2][n*n];
		int tempIndex=0, sum=0, curStart, curEnd;
		int startIndex=0, endIndex=n*n-1;
		long finalRes=0, startCount, endCount;
		//finalRes의 범위는 4000^4까지 나올 수 있기에 long 선언 필요.
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<4; j++) {
				st.nextToken();
				arrs[j][i]=(int)st.nval;
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sums[0][tempIndex]=arrs[0][i]+arrs[1][j];
				sums[1][tempIndex]=arrs[2][i]+arrs[3][j];
				++tempIndex;
			}
		}//두개씩 쌍을 이루어 합 배열을 만든다.->시간 복잡도 O(n^2)
		
		Arrays.sort(sums[0]);
		Arrays.sort(sums[1]);
		
		while(endIndex>=0&&startIndex<n*n) {
			sum=sums[0][startIndex]+sums[1][endIndex];
			if(sum==0) {
				curStart=sums[0][startIndex]; 
				curEnd=sums[1][endIndex];
				startCount=0; endCount=0;
				
				while(startIndex<n*n&&sums[0][startIndex]==curStart) {
					++startIndex;
					++startCount;
				}
				while(endIndex>=0&&sums[1][endIndex]==curEnd) {
					--endIndex;
					++endCount;
				}//같은 경우에 대해 스킵해주기
				
				finalRes+=startCount*endCount;//최종 가능 조합들을 더한다.
			}else if(sum<0) {
				++startIndex;
			}else {
				--endIndex;
			}
		}
		System.out.println(finalRes);
		
		br.close();
	}
}
