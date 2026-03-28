package main.java.study.a07_dp.d03.p3687;

import java.io.*;
import java.util.*;

public class Main_조은진 {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		int[] minDigitCnt=new int[101];//i개를 사용할 경우 최소 몇 자리 숫자를 만들 수 있는지 자리수를 저장하는 배열
		int [][] minCnt=new int[101][10];//i개를 사용할 경우 최소 숫자들에서 각각 몇개씩 가지고 있는지 센다.
		int [] makeOneDigits= new int[]{-1,-1, 1, 7, 4, 2, 0, 8};//2-7로 만들 수 있는 최소 숫자들을 표현
		
		//1자리수로 만들 수 있는 애들 초기화
		for(int i=2; i<=7; i++) minDigitCnt[i]=1;
		minCnt[2][1]+=1;
		minCnt[3][7]+=1;
		minCnt[4][4]+=1;
		minCnt[5][2]+=1;
		minCnt[6][6]+=1;
		minCnt[7][8]+=1;
		
		
		//시간복잡도 대충 100*10*20 정도
		for(int i=8; i<=100; i++) {
			int minDigit=Integer.MAX_VALUE; 
			int[]tempCnt=new int[10];
			int[] resCnt=new int[10];
			
			for(int j=2; j<=7; j++) {
				if(i==8&&j==7) continue;
				if(minDigit>=minDigitCnt[i-j]+1) {
					int addNum=makeOneDigits[j];
					tempCnt=Arrays.copyOf(minCnt[i-j], 10);
					tempCnt[addNum]+=1;
					
					int firstNum=10;
					for(int k=1; k<10; k++) {
						if(tempCnt[k]>0) firstNum=k;
					}//첫 번째 숫자로 올 것을 고른다.
					
					if(firstNum<6&&tempCnt[6]>=1) {
						tempCnt[6]-=1;
						tempCnt[0]+=1;
					}//자리수가 늘어나서 6을 0으로 바꿀 수 있을 경우 바꾼다. 6이 있는 경우 항상 가장 앞에있는 경우에만 오게 된다.
					//가장 높은 숫자가 6보다 작고 6이 숫자 안에 존재했을 경우 6을 0으로 바꾸고 뒤로 보낸다.(여기서 틀림 ㅠㅠ)
	
					if(minDigit>minDigitCnt[i-j]+1) {//min값으로 인해 바로 바꿔야 한다.
						minDigit=minDigitCnt[i-j]+1;//minDigit값 업데이트
						resCnt=Arrays.copyOf(tempCnt, 10);
					}else {
						boolean changeflag=false;
						boolean firstflag=false;
						
						for(int k=1; k<10; k++) {
							if(resCnt[k]>0&&tempCnt[k]==0) {//min의 첫번째 자리가 더 작다
								firstflag=true;
								break;
							}else if(resCnt[k]==0&&tempCnt[k]>0) { //현재 숫자의 첫번째 자리가 더 작다
								firstflag=true;
								changeflag=true;
								break;
							}else if(resCnt[k]>0&&tempCnt[k]>0) {
								break;
							}//둘의 첫번째 자리수가 같다.
						}//1과 가까운 것들 중 가장 먼저 나온다->해당 경우가 첫번째
						
						if(changeflag) resCnt=Arrays.copyOf(tempCnt, 10);
						
						if(!firstflag) {//가장 높은자리 비교로 결론이 안 난 경우
							for(int k=0; k<10; k++) {
								if(resCnt[k]<tempCnt[k]) {
									changeflag=true;
									break;
								}else if(resCnt[k]>tempCnt[k])break;
							}//첫째 숫자가 같을 경우->이제 0가지고 일반적인 비교
							if(changeflag) resCnt=Arrays.copyOf(tempCnt, 10);
						}
					}
				}
			}
			minDigitCnt[i]=minDigit;
			minCnt[i]=Arrays.copyOf(resCnt, 10);
		}
		
		st.nextToken();
		int testCase=(int)st.nval;
		
		
		
		for(int t=1; t<=testCase; t++) {
			st.nextToken();
			int N=(int)st.nval;
			
			int[] finalMin=new int[10];
			finalMin=Arrays.copyOf(minCnt[N], 10);
			
			for(int i=1; i<10; i++) {
				if(finalMin[i]>0) {
					System.out.print(i);
					finalMin[i]-=1;
					break;
				}
			}//첫 번째 자리수를 출력한다.
			
			for(int i=0; i<10; i++) {
				for(int j=0; j<finalMin[i]; j++) {
					System.out.print(i);
				}
			}//count 배열에서 작은 숫자 순서대로 출력한다.
			
			System.out.print(' ');
			
			//무조건 자릿수를 늘리는게 좋다.->최소한의 성냥을 사용하는 숫자들만 사용한다.
			if(N%2==1) {
				N-=3;
				System.out.print(7);
			}//홀수의 경우 1만 하면 1 남게 되니까 3을 빼줄 필요가 있다. 3의 결과인 7이 1보다 크기에 가장 앞에 써준다.
			
			for(int i=0; i<N/2; i++) System.out.print(1);//N/2만큼 1을 출력한다.
			System.out.println();
		}
		
		br.close();
				
	}	

}
