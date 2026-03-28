package main.java.study.a02_tp.d01.p2470;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main_조은진 {
	public static void find_min_abs(List<Integer> arr, int[] res) {
		int start=0, end=arr.size()-1;
		int res_num, min_abs=Integer.MAX_VALUE, current_abs;
		
		while(start<end) {
			res_num=arr.get(start)+arr.get(end);
			
			if(res_num==0) {
				res[0]=arr.get(start);
				res[1]=arr.get(end);
				return;
			}//0 나오면 바로 값 갱신 후에 return
			
			if(min_abs>Math.abs(res_num)) {
				min_abs=Math.abs(res_num);
				res[0]=arr.get(start);
				res[1]=arr.get(end);
			}//0이 안 나올지 모르기 때문에 최소 절댓값 추적
			
			if(res_num<0) {
				++start;
			}else {
				--end;
			}//포인터 이동
			
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		List<Integer> arr=new ArrayList<>();
		int[] res=new int[2];
		StringBuilder sb=new StringBuilder();
		
		for(int i=0; i<n; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		arr.sort((a, b)->Integer.compare(a, b));//입력 및 정렬
		
		if(arr.get(0)>=0) {
			res[0]=arr.get(0);
			res[1]=arr.get(1);
		}
		else if(arr.get(arr.size()-1)<=0) {
			res[0]=arr.get(arr.size()-2);
			res[1]=arr.get(arr.size()-1);
		}//다 양수인 경우랑 다 음수인 경우는 바로 처리해주기
		else {
			find_min_abs(arr, res);
		}
		
		sb.append(res[0]);
		sb.append(" ");
		sb.append(res[1]);
		
		System.out.println(sb.toString());
		
	}
}
