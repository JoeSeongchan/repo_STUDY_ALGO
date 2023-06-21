/*
드래곤 커브 
1. 문제 재정의 및 추상화 
x축 = column, y축 = row 
드래곤 커브의 특성 세 가지 
1. 시작 점 
2. 시작 방향 
3. 세대 
세대가 증가할 수록 드래곤 커브의 모양은 조금씩 더 복잡해진다. 
드래곤 커브의 정보는 주어진다. (드래곤 커브의 개수 또한 주어진다. )
입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않는다. 
드래곤 커브는 서로 겹칠 수 있다. (기억!)
크기가 1*1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수를 출력하라! 
시뮬레이션 문제인 듯 보인다. 드래곤 커브의 정보가 주어질 때, 세대를 하나씩 증가시켜나가면서 드래곤 커브를 2차원 boolean 맵에 그린다. 세대를 증가시켜나갈 때 드래곤 커브의 점들은 Stack에 저장한다. 그리고 회전한 결과 점들을 Stack에 계속 넣는다. 세대가 최대 10이기 때문에 점의 최대 개수는 2^10인 1024이다. 1000*10 = 1만 번. 드래곤 커브 하나당 1만 번의 연산. 드래곤 커브의 개수 최대 20개. 최대 20만 번의 연산. 
왜 점들의 좌표를 Stack에 저장하는가? 맨 마지막 점을 기준으로 회전하기 때문이다. 첫번째 점이 새로운 마지막 점이 된다. 

회전을 어떻게 구현할 것인가? 
y 1 증가 => x 1 감소
y 1 감소 => x 1 증가
x 1 증가 => y 1 증가 
x 1 감소 => y 1 감소

2. 풀이과정 상세히 적기 
Stack 1개, Queue 1개 운용

3. 시간복잡도 계산 
O(드래곤 커브의 개수 * (2^최대 세대) * 최대 세대)

** 유의할 점 
	드래곤 커브는 격자 안에 있다. 0~100, 0~100 만 유효한 좌표이다. 
	세대를 거듭해나가다가 드래곤 커브가 격자 바깥으로 나가면 거기서부터는 무시해버린다.
	
4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class Solution{
	
	boolean[][] map;
	final int width=101;
	final int RIGHT=0, UP=1, LEFT=2, DOWN=3, NONE=4;
	final int[] dxS=new int[]{1,0,-1,0,0};
	final int[] dyS=new int[]{0,-1,0,1,0};
	
	int solution(int curveN, int[][] curveS){
		int answer=0;
		init();
		for(int[] curve: curveS){
			drawCurve(curve);
			// debug();
		}
		
		answer=countRectangle();
		return answer;
	}
	
	void init(){
		map=new boolean[width][width];
	}
	
	void drawCurve(int[] curve){
		List<int[]> pointS=new ArrayList<>();
		List<int[]> newPointS=new ArrayList<>();
		
		int[] startPoint=new int[]{curve[0],curve[1]};
		int startDirection=curve[2];
		int gen=curve[3];
		int[] nextPoint=goInDirection(startPoint,startDirection);
		//System.out.printf("시작 위치 : %s\n",Arrays.toString(startPoint));
		//System.out.printf("끝 위치 : %s\n",Arrays.toString(nextPoint));
		
		pointS.add(startPoint);
		pointS.add(nextPoint);
		newPointS.add(nextPoint);
		
		for(int i=0;i<gen;i++){
			// 기존 점들 거꾸로 탐색
			int[] prevPoint=pointS.get(pointS.size()-1);
			for(int k=pointS.size()-2;k>=0;k--){
				int[] point=pointS.get(k);
				int dir = findOutDirection(prevPoint,point);
				if(dir==NONE)	continue;
				int newDir = getNewDirection(dir);
				int[] lastNewPos=newPointS.get(newPointS.size()-1);
				int[] newPos = goInDirection(lastNewPos, newDir);
				if(!isValid(newPos[0],newPos[1])){
					// 새로운 점이 범위를 유효한 범위를 넘어서면 반복을 멈춘다. 
					break;
				}
				newPointS.add(newPos);
				prevPoint = point;
			}
			// 한 세대가 끝나고 새로운 점들을 추가한다. 
			for(int[] newPoint: newPointS){
				// System.out.printf("점을 추가합니다. (%d, %d)\n",newPoint[0],newPoint[1]);
				pointS.add(newPoint);
			}
			newPointS.clear();
			newPointS.add(pointS.get(pointS.size()-1));
			// System.out.println();
		}
		// 모든 세대를 다 계산한 후에 점들을 맵에 그린다. 
		for(int[] point: pointS){
			if(!isValid(point[0],point[1]))	continue;
			map[point[1]][point[0]]=true;
		}
	}
	
	void debug(){
		for(int x=0;x<10;x++){
			for(int y=0;y<10;y++){
				if(map[x][y]){
					System.out.printf("%d ",1);
				} else{
					System.out.printf("%d ",0);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	int countRectangle(){
		int cnt=0;
		for(int x=0;x<width-1;x++){
			for(int y=0;y<width-1;y++){
				int[] LU=new int[]{x,y};
				int[] RU=new int[]{x+1,y};
				int[] LD=new int[]{x,y+1};
				int[] RD=new int[]{x+1,y+1};
				if(isPainted(LU) && 
					isPainted(RU) && 
					isPainted(LD) && 
					isPainted(RD)){
					cnt+=1;
				}
			}
		}
		return cnt;
	}
	
	boolean isValid(int x, int y){
		if(x<0||x>100)	return false;
		if(y<0||y>100)	return false;
		return true;
	}
	
	boolean isPainted(int[] pos){
		return map[pos[0]][pos[1]];
	}
	
	int findOutDirection(int[] src, int[] dst){
		int srcX=src[0], srcY=src[1];
		int dstX=dst[0], dstY=dst[1];
		for(int dir=0;dir<4;dir++){
			int x=srcX+dxS[dir];
			int y=srcY+dyS[dir];
			if(x==dstX && y==dstY){
				return dir;
			}
		}
		return NONE;
	}
	
	int[] goInDirection(int[] src, int direction){
		int srcX=src[0], srcY=src[1];
		int dx=dxS[direction], dy=dyS[direction];
		int dstX=srcX+dx, dstY=srcY+dy;
		return new int[]{dstX, dstY};
	}
	
	int getNewDirection(int direction){
		switch(direction){
			case RIGHT:
				return DOWN;
			case UP:
				return RIGHT;
			case LEFT:
				return UP;
			case DOWN:
				return LEFT;
		}
		return UP;
	}
	
	public static void main(String[] args) throws Exception{
		Solution T=new Solution();
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int curveN=Integer.parseInt(kb.readLine());
		int[][] curveS=new int[curveN][4];
		for(int i=0;i<curveN;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<4;k++){
				curveS[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(curveN,curveS));
	}
}