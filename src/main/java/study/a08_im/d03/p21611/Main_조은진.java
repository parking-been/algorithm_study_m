package main.java.study.a08_im.d03.p21611;


import java.io.*;

public class Main_조은진 {
	//필요한 메서드 1. 빈 칸 밀어내기 2. 다시 채우기 3. 폭발 시키기 
	//블리자드를 총 M번 시전
	//위치 정보가 필요한 거->오직 얼음 쏠 때랑, 초기화할 때만 필요. 연속된거 확인 폭발->번호별로 가면 된다. 미는거->그냥 인덱스로 채우면 된다.
	//늘릴 때->인덱스로 쓰면 된다.
	
	static boolean[] isVacant;
	static int[][] original;
	static int[] curnum;//해당 인덱스에 들어 있는 숫자를 나타낸다.
	static int N, res, totalnum;
	static int[] magicstart= {0, 7,3,1,5};
	
	//1.인덱스 별 번호를 식별해서 넣어준다.
	public static void initialize() {
		//일단 왼쪽 아래쪽 오른쪽
		//대각선으로 방향 바꿀 기준 애들을 정한다. 처음 빼고 -1-1(시작) 1-1 1 1 -1 1 * 증분 순서여야 한다.
		int[] endx= {1, 1, -1, -1};//좌하 우하 우상 좌상의 순서
		int[] endy= {-1, 1, 1, -1};
		int[] dx= {1, 0, -1, 0};//하 우 상 좌
		int[] dy= {0, 1, 0, -1};
		int curindex=1;
		int endrow, endcol;
		boolean endflag=false;
		for(int i=1; i<=N/2; i++) {
			int curRow=N/2-i+1; int curCol=N/2-i;
			for(int dir=0; dir<4; dir++) {
				endrow=N/2+endx[dir]*i;
				endcol=N/2+endy[dir]*i;
				while(true) {
					if(curRow==endrow&&curCol==endcol)break;
					if(original[curRow][curCol]==0) {
						endflag=true;
						totalnum=curindex;
						break;
					}
					curnum[curindex++]=original[curRow][curCol];
					curRow+=dx[dir]; curCol+=dy[dir]; 
				}//대각선, 즉 방향을 바꿔야 하는 곳 이전까지 순회하면서 체크해준다.
				if(endflag)break;
			}
			if(endflag)break;
			//마지막 처리해주기
			curnum[curindex++]=original[curRow][curCol];
		}
		if(!endflag) totalnum=N*N;//여기에서 -1로 실수함1
	}
	
	//2. 블리자드 쏘기. 
	//어떤 식으로 인덱스를 정하는가->이전 증분에 8씩 더해주면 된다.시작 점은 7 3 1 5
	public static void magic(int d, int s) {
		int curadd=magicstart[d];
		int curnum=0;
		for(int i=0; i<s; i++) {
			curnum+=curadd;
			if(curnum < totalnum) isVacant[curnum]=true;//여기에서 중간에 비워진거만 체크했어야 했음
			curadd+=8;
		}
		pull();
		change();
	}
	
	public static void pull() {
		int[] pullnum=new int[N*N];
		int vacantcnt=0;
		for(int i=1; i<N*N; i++) {
			if(isVacant[i]) {
				++vacantcnt;
				isVacant[i]=false;
				totalnum-=1;
			}
			else pullnum[i]=vacantcnt;
		}//얼마나 밀어야 하는지 센다.
		for(int i=1; i<N*N; i++) {
			curnum[i-pullnum[i]]=curnum[i];
		}//당긴 다음, 빈 칸들 처리해 줘야 한다.
		for(int i=totalnum; i<N*N; i++) {
			curnum[i]=0;
		}
		explore();//당긴 다음에는 폭팔 여부를 확인한다.
	}
	
	//폭팔하기->여기 폭팔한 거 갯수 새어줘야 한다.
	public static void explore() {
		int cnt=1; int prevnum=curnum[1];
		boolean exploreHappened=false;
		for(int i=2; i<N*N; i++) {
			if(curnum[i]!=prevnum) {
				cnt=1;
				prevnum=curnum[i];
			}else ++cnt;
			if(curnum[i]==0)break;
			//초기에 빈칸을 따로 처리해 주지 않음(isVacant는 중간에 빈칸을 확인)->0이 나올 때까지만 explore 여부 검사
			if(cnt==4) {
				exploreHappened=true;
				for(int j=0; j<4; j++) isVacant[i-j]=true;
				res+=curnum[i]*4;
				
			}else if(cnt>4) {
				isVacant[i]=true;
				res+=curnum[i];
			}
		}
		if(exploreHappened)pull();//폭발을 했으면 당긴다.
	}
	
	//바꾸기
	public static void change() {
		//1. 몇개가 있는지 센다. N*N까지 이르면 끝냄
		int[] temp=new int[N*N];//어차피 짝수로 끝남
		int curindex=1;
		int cnt=1; int prevnum=curnum[1];
		if(curnum[1]==0) return;//실수 3: 다 폭파된 경우 0으로 쌓일 수 있었다.
		boolean zeroflag=false;
		for(int i=2; i<N*N; i++) {
			if(curnum[i]!=prevnum) {
				//이전 걸 넣어준다.
				temp[curindex++]=cnt;
				temp[curindex++]=prevnum;
				
				if(curnum[i]==0) {
					zeroflag=true;
					break;
				}
				if(curindex>=N*N)break;
				
				cnt=1;
				prevnum=curnum[i];
			}else ++cnt;
		}
		//마무리
		if(curindex<N*N&&!zeroflag) {
			temp[curindex++]=cnt;
			temp[curindex++]=prevnum;
		}
		for(int i=1; i<N*N; i++) {
			curnum[i]=temp[i];
		}
		totalnum=curindex;
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st=new StreamTokenizer(br);
		
		st.nextToken();
		N=(int)st.nval;
		
		st.nextToken();
		int M=(int)st.nval;
		
		original=new int[N][N];
		curnum=new int[N*N];
		isVacant=new boolean[N*N];
		res=0; totalnum=0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				st.nextToken();
				original[i][j]=(int)st.nval;
			}
		}
		
		initialize();

		for(int i=0; i<M; i++) {
			st.nextToken();
			int d=(int)st.nval;
			st.nextToken();
			int s=(int)st.nval;
			
			magic(d,s);
		}
		
		System.out.println(res);
		
		br.close();
	}

}
