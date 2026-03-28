package main.java.study.a03_gt.d02.p18513;



import java.io.*;
import java.util.*;

public class Main_조은진 {
	static long res=0;
	//중요! 100000개가 최대 1000000거리를 가질 수 있기에 long으로 선언 필요.
	
	//bfs를 돌면서 아직 안 방문했으면 node 수 1 늘리고 queue에 넣기
	//방문 안했다면(Set을 통한 검색)집을 세운다. 집count 1늘이고 해당 집의 거리만큼 res를 증가시킨다.
	//k개의 집을 샐 때까지 진행한다.
	public static void bfs(int k, Queue<int[]> queue, Set<Integer> visitedSet) {
		int count=0;
		int []currentPosition;
		
		while(count<k) {
			currentPosition=queue.poll();//[0]->현재 노드 위치 [1]->현재 노드와 샘 간의 거리
			if(!visitedSet.contains(currentPosition[0]+1)) {
				visitedSet.add(currentPosition[0]+1);
				queue.offer(new int[] {currentPosition[0]+1, currentPosition[1]+1});
				res+=currentPosition[1]+1;
				++count;
			}
			
			if(!visitedSet.contains(currentPosition[0]-1) && count<k) {//위에서 끝날 수도 있으니까
				visitedSet.add(currentPosition[0]-1);
				queue.offer(new int[] {currentPosition[0]-1, currentPosition[1]+1});
				res+=currentPosition[1]+1;
				++count;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		int n, k, num;
		st.nextToken();
		n=(int)st.nval;
		st.nextToken();
		k=(int)st.nval;
		
		Queue<int[]> queue=new ArrayDeque<>();
		Set<Integer> visitedSet=new HashSet<>();
		
		for(int i=0; i<n; i++) {
			st.nextToken();
			num=(int)st.nval;
			queue.offer(new int[] {num, 0});
			visitedSet.add(num);
		}
		
		bfs(k, queue, visitedSet);
		System.out.println(res);
	}
}

