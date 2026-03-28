package main.java.study.a03_gt.d01.p2667;


import java.util.*;
import java.io.*;
import java.lang.*;

public class Main_조은진
{
	public static int housingComplexDFS(int row, int col, int [][] originHouses, boolean[][] visited) {
		Deque<int[]> stack=new ArrayDeque<>();
		int houseCount=0;
		int cur_row, cur_col, nx, ny;
		int [] dx= {-1,1,0,0};
		int [] dy= {0,0,-1,1};
		int [] temp;
		int n=originHouses.length;
		
		stack.push(new int[] {row, col});
		visited[row][col]=true;
		
		while(!stack.isEmpty()) {
			temp=stack.pop();
			cur_row=temp[0]; cur_col=temp[1];
			
			++houseCount;//단지의 수를 센다.(얘는 pop할 때 세도 된다.)
			
			for(int i=0; i<4; i++) {
				nx=cur_row+dx[i]; ny=cur_col+dy[i];
				if(nx>=0&&ny>=0&&nx<n&&ny<n&&originHouses[nx][ny]==1&&!visited[nx][ny]) {
					stack.push(new int[] {nx, ny});
					visited[nx][ny]=true;
				}
			}
			
		}
		
		return houseCount;
	}
	
	public static int housingComplexBFS(int row, int col, int [][] originHouses, boolean[][] visited) {
		Queue<int[]> queue=new ArrayDeque<>();
		int houseCount=0;
		int cur_row, cur_col, nx, ny;
		int [] dx= {-1,1,0,0};
		int [] dy= {0,0,-1,1};
		int [] temp;
		int n=originHouses.length;
		
		queue.offer(new int[] {row, col});
		visited[row][col]=true;
		
		while(!queue.isEmpty()) {
			temp=queue.poll();
			cur_row=temp[0]; cur_col=temp[1];
			
			++houseCount;//단지의 수를 센다.(얘는 pop할 때 세도 된다.)
			
			for(int i=0; i<4; i++) {
				nx=cur_row+dx[i]; ny=cur_col+dy[i];
				if(nx>=0&&ny>=0&&nx<n&&ny<n&&originHouses[nx][ny]==1&&!visited[nx][ny]) {
					queue.offer(new int[] {nx, ny});
					visited[nx][ny]=true;
				}
			}
			
		}
		
		return houseCount;
	}
	
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	int n=Integer.parseInt(br.readLine());
    	int[][] originHouses=new int [n][n];
    	boolean [][] visited=new boolean[n][n];
    	int complexNum=0, houseCount;
    	List<Integer> complexCounts=new ArrayList<Integer>();
    	String s;
    	
    	for(int i=0; i<n; i++) {
    		s=br.readLine();
    		for(int j=0; j<n; j++) {
    			originHouses[i][j]=(int)(s.charAt(j)-'0');
    		}
    	}//입력 처리
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<n; j++) {
    			if(originHouses[i][j]==1&&!visited[i][j]) {
    				houseCount=housingComplexBFS(i, j, originHouses, visited);
    				++complexNum;
    				complexCounts.add(houseCount);
    			}
    		}
    	}
    	
    	complexCounts.sort((a, b)->Integer.compare(a, b));
    	System.out.println(complexNum);
    	for (Integer integer : complexCounts) {
			System.out.println(integer);
		}
     
    }
}