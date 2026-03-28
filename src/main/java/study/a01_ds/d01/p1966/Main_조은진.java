package main.java.study.a01_ds.d01.p1966;

import java.util.*;
import java.io.*;

public class Main_조은진 {
	public static void main(String[] args) throws IOException {
        Set<Integer> rank_set=new HashSet<>();
        Queue<Integer> queue=new LinkedList<>();
        int t, n, cur_index, num, cur_max, res=0;

        Scanner sc=new Scanner(System.in);
        t=sc.nextInt();
        
        for(int i=0; i<t; i++){
        	
        	res=0;
            n=sc.nextInt();
            cur_index=sc.nextInt();
            for(int j=0; j<n; j++){
                num=sc.nextInt();
                rank_set.add(num);
                queue.add(num);
            }

            cur_max=Collections.max(rank_set);
            while(!queue.isEmpty()){
            	num=queue.poll();
                if(num==cur_max){
                    rank_set.remove(num);
                    if(rank_set.size()>0) {
                    	cur_max=Collections.max(rank_set);
                    }//최댓값 갱신
                    
                    ++res;
                    if(cur_index==0){
                        break;
                    }
                    
                }else{
                    queue.add(num);//다시 넣는다.
                }
                
                --cur_index;//문서 인덱스 갱신(앞으로 간다.)
                if(cur_index<0){//너무 앞으로 갔을 때
                    cur_index=queue.size()-1;
                }
            }
            
            System.out.println(res);
        }
    }
}


