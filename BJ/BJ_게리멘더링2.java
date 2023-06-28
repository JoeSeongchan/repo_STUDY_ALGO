/*
게리멘더링 2
1. 문제 재정의 및 추상화 
완전탐색(DFS)+ BFS + 시뮬레이션(구현)
모든 가능한 x,y,d1,d2 케이스를 구하고, 그 케이스를 적용하여 다섯 개의 선거구로 나눠본다. 다섯 개의 선거구의 인구를 구하고, 인구가 가장 많은 선거구의 인구와 인구가 가장 적은 선거구의 인구의 차의 최솟값을 구한다. 모든 케이스를 다 구해야 하기 때문에 완전탐색 알고리즘을 적용하고, 각 케이스에 대해서 다섯 개의 선거구로 나눠야 하기 때문에 시뮬레이션 알고리즘을 사용한다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
모든 가능한 x,y,d1,d2 케이스를 구하기 => O(N^4)
각 케이스에 대해서 다섯 개의 선거구로 나누기 => O(N^2)
총 시간복잡도 (예상) => O(N^6)
N= 최대 20
2^6 * (10^6) = 6400_0000 = 6400만 회 연산 
1초 안에 해결할 수 있다. 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_게리멘더링2{
	
	int x,y,d1,d2;
	int width; // 폭 
	int[][] map; // 지도 
	int minDiff; // 최소 차이
	int[][] status; // 선거구 정보 담고 있는 배열
	final int[] dxS=new int[]{-1,1,0,0};
	final int[] dyS=new int[]{0,0,-1,1};
	final int FIRST=1, SECOND=2, THIRD=3, FOURTH=4, FIFTH=5;
	
	int solution(int width, int[][] map){
		int answer=0;
		init(width,map);
		for(int x=1;x<=width;x++){
			for(int y=1;y<=width;y++){
				for(int d1=1;d1<=width;d1++){
					for(int d2=1;d2<=width;d2++){
						initInitialCondition(x,y,d1,d2);
						run();
					}
				}
			}
		}
		answer=minDiff;
		return answer;
	}
	
	void init(int width, int[][] map){
		this.width=width;
		this.map=map;
		minDiff=Integer.MAX_VALUE;
	}
	
	void initInitialCondition(int x,int y,int d1,int d2){
		this.x=x;
		this.y=y;
		this.d1=d1;
		this.d2=d2;
	}
	
	void run(){
		// x,y,d1,d2 유효하지 않은 경우, 그냥 종료한다. 
		if(!isInitialConditionValid())	return;
		status=new int[width+1][width+1]; // 선거구 정보 담을 배열 초기화 
		checkFifthArea(); // 5번 선거구 영역 파악
		int[] populationS=new int[6];
		for(int r=1;r<=width;r++){
			for(int c=1;c<=width;c++){
				int areaNum=whatArea(r,c); // 1,2,3,4번 선거구 영역 파악
				status[r][c]=areaNum;
				populationS[areaNum]+=map[r][c]; // 각 선거구의 인구수 파악
			}
		}
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		for(int i=1;i<=5;i++){
			min=Math.min(min,populationS[i]);
			max=Math.max(max,populationS[i]);
		}
		int diff=max-min; // 차이값 계산 
		if(minDiff>diff){
			minDiff=diff;
			//System.out.printf("diff : %d\n",diff);
			//System.out.printf("population : %s\n",Arrays.toString(populationS));
			//debug();
		}
		minDiff=Math.min(minDiff,diff); 
	}
	
	void debug(){
		for(int r=1;r<=width;r++){
			for(int c=1;c<=width;c++){
				System.out.printf("%d ",status[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	boolean isInitialConditionValid(){
		if(1<= x && x<x+d1+d2 && x+d1+d2<=width 
		&& 1 <= y-d1 && y-d1 < y && y < y+d2 && y+d2 <= width) return true;
		return false;
	}
	
	void checkFifthArea(){
		for(int curD1=0;curD1<=d1;curD1++){
			for(int curD2=0;curD2<=d2;curD2++){
				status[x+curD1][y-curD1]=FIFTH;
				status[x+curD2][y+curD2]=FIFTH;
				status[x+d1+curD2][y-d1+curD2]=FIFTH;
				status[x+d2+curD1][y+d2-curD1]=FIFTH;
			}
		}
		Stack<int[]> st=new Stack<>();
		for(int r=1;r<=width;r++){
			boolean isFifth=false;
			for(int c=1;c<=width;c++){
				if(!isFifth && status[r][c]==FIFTH){
					isFifth=true;
				}
				else if(isFifth && status[r][c]==FIFTH){
					isFifth=false;
					while(!st.isEmpty()){
						int[] p=st.pop();
						status[p[0]][p[1]]=FIFTH;
					}
					break;
				}
				else if(isFifth){
					// System.out.printf("%d %d\n",r,c);
					st.push(new int[]{r,c});
				}
			}
			st.clear();
		}
	}
	
	int whatArea(int r, int c){
		if(status[r][c]==FIFTH) return 5;
		if( 1 <= r && r < x+d1 && 1 <= c && c <= y)	return 1;
		if( 1 <= r && r <= x+d2 && y < c && c <= width) 	return 2;
		if( x+d1 <= r && r <= width && 1 <= c && c < y-d1+d2) return 3;
		if( x+d2 < r && r <= width && y-d1+d2 <= c && c <= width) return 4;
		debug();
		return -1;
	}
	
	
	
	public static void main(String[] args) throws Exception{
		BJ_게리멘더링2 T=new BJ_게리멘더링2();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int width=Integer.parseInt(kb.readLine());
		int[][] map=new int[width+1][width+1];
		for(int ri=1;ri<=width;ri++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int ci=1;ci<=width;ci++){
				map[ri][ci]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(width,map));
	}
}