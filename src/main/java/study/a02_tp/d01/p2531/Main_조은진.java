package main.java.study.a02_tp.d01.p2531;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main_조은진 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n, d, k, c;
		int cur_kind=0, max_kind=0;
		int start, end;
		int[] kind_count=new int[3001];
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		List<Integer> sushie_list=new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			sushie_list.add(Integer.parseInt(br.readLine().trim()));
		}
		
		++kind_count[c];
		cur_kind=1;//쿠폰 있는 거는 어차피 무조건 먹을 수 있으니까 1 추가
		//이건 while문을 통해 없어지지 않는다.
		
		start=0; 
		for(int i=0; i<k; i++) {
			if(kind_count[sushie_list.get(i)]==0) {
				++cur_kind;
			}//처음 보는 종류의 스시의 경우 1추가
			++kind_count[sushie_list.get(i)];//해당 종류 스시 개수 추가
		}
		max_kind=cur_kind;//첫번째 경우 초기화
		
		while(true) {
			--kind_count[sushie_list.get(start)];
			if(kind_count[sushie_list.get(start)]==0) {
				--cur_kind;
			}//움직이면서 스시 종류가 1개 제거됌
			
			end=(start+k)%n;//회전 반영
			++kind_count[sushie_list.get(end)];
			if(kind_count[sushie_list.get(end)]==1) {
				++cur_kind;
			}//움직이면서 스시 종류가 1개 추가됌
			
			
			max_kind=Math.max(cur_kind, max_kind);
			
			++start;
			if(start%n==0) {
				break;
			}//처음 상태로 오면 break
		}
		
		System.out.println(max_kind);
		
	}
}
