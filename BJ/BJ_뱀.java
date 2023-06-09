/*
뱀
1. 문제 재정의 및 추상화 
뱀. 사과 먹으면 뱀 길이 늘어남. 벽 또는 자기 자신의 몸과 부딪히면 끝. 
N*N 보드. 몇몇 칸에 사과 놓여짐. 벽 상하좌우 끝에 벽 있음. 
게임 시작 시 뱀은 맨 위 맨 좌측에 위치. 뱀 길이는 1. 뱀은 오른쪽 향함. 
매초마다 이동. 다음과 같은 규칙을 따름. 
- 몸 길이를 늘려 머리를 다음 칸에 위치시킴
- 벽이나 몸에 부딪히면 Game Over
- 사과 있으면 사과 없어지고 꼬리 안 움직임. 
- 사과 없으면 몸 길이 줄여서 꼬리 움직임. (몸 길이 그대로)
사과의 위치와 뱀의 이동경로 주어짐. 게임 몇 초에 끝나는지 계산 
전형적인 시뮬레이션 문제. 케이스 고려할 필요 없이 그대로 구현하면 됨. 
사과의 위치는 다 다름. 방향 변환 정보 주어짐. 
X초 끝난 후에 주어진 방향대로 90도 방향 회전. 방향 전환 정보는 X가 증가하는 순으로 주어짐. 

2. 풀이과정 상세히 적기 
생략

3. 시간복잡도 계산 
O(N*N*L)
확실하지 않음. 시뮬레이션 문제이기 때문에 시간 복잡도로 터지는 일은 없을 것임. 
공간복잡도는? 메모리 때문에 터질 수 있지 않나? 이것도 문제 없음. 

** 주의: 시뮬레이션 문제, 완전탐색 문제는 코드의 중복을 줄이려다가 더 풀이가 꼬이는 경우가 많다. 중복의 양이 크지 않다면 그냥 코드 적자... 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_뱀{
	
	static class Snake{
		
		Deque<int[]> course;
		// 상우하좌
		static final int[] driS=new int[]{-1,0,1,0};
		static final int[] dciS=new int[]{0,1,0,-1};
		int dir;
		
		Snake(){
			course=new ArrayDeque<>();
			course.offerFirst(new int[]{0,0});
			dir=1;
		}
		
		void extend(int[] pos){
			course.offerFirst(pos);
		}
		
		int[] shorten(){
			return course.pollLast();
		}
		
		void turnLeft(){
			dir+=3;
			dir%=4;
		}
		
		void turnRight(){
			dir+=1;
			dir%=4;
		}
		
		int[] getNextPos(){
			int[] last=course.peekFirst();
			int dri=driS[dir];
			int dci=dciS[dir];
			return new int[]{last[0]+dri,last[1]+dci};
		}
		
		@Override
		public String toString(){
			StringBuilder sb=new StringBuilder();
			sb.append("뱀 경로\n");
			for(int[] pos: course){
				sb.append(String.format("  (%d, %d)\n",pos[0],pos[1]));
			}
			return sb.toString();
		}
	}
	
	static class Game{
		
		int width;
		int[][] map;
		final int NO_APPLE = 0;
		final int APPLE = 1;
		final int SNAKE = 2;
		
		Snake snake;

		Game(int width, int appleN, int[][] appleS){
			this.width=width;
			map=new int[width][width];
			for(int[] apple: appleS){
				int appleRi=apple[0]-1,appleCi=apple[1]-1;
				map[appleRi][appleCi]=APPLE;
			}
			snake=new Snake();
			map[0][0]=SNAKE;
		}
		
		boolean isValid(int[] pos){
			int ri=pos[0],ci=pos[1];
			if(ri<0||ri>=width||ci<0||ci>=width){
				return false;
			}
			if(map[ri][ci]==SNAKE){
				return false;
			}
			return true;
		}
		
		boolean move(){
			int[] nextPos=snake.getNextPos();
			if(!isValid(nextPos)){
				return false;
			}
			int ri=nextPos[0],ci=nextPos[1];
			snake.extend(nextPos);
			if(map[ri][ci]==NO_APPLE){
				int[] prevTail=snake.shorten();
				map[prevTail[0]][prevTail[1]]=NO_APPLE;
			} 
			map[ri][ci]=SNAKE;
			// System.out.println(snake);
			return true;
		}
		
		void turn(char dir){
			switch(dir){
				case 'L':
					snake.turnLeft();	break;
				case 'D':
					snake.turnRight();	break;
			}
		}
	}
	
	int solution(int width, int appleN, int[][] appleS, int turnN, int[][] turnS){
		
		int answer=0;
		Game game=new Game(width,appleN,appleS);
		int time=0;
		int turnIdx=0;
		
		while(true){
			// System.out.printf("[시간 : %d] ",time+1);
			boolean ok=game.move();
			time+=1;
			if(!ok)	break;
			int[] turn = turnS[turnIdx];
			int turnTime= turn[0];
			char turnDir = (char)turn[1];
			// System.out.printf("다음 회전 시간 : %d, 방향  : %c\n",turnTime,turnDir);
			if(turnTime==time){
				game.turn(turnDir);
				turnIdx+=1;
				turnIdx=Math.min(turnIdx,turnN-1);
				// System.out.println("회전!\n");
			}
		}
		answer=time;
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_뱀 T=new BJ_뱀();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		
		int width=Integer.parseInt(kb.readLine());
		int appleN=Integer.parseInt(kb.readLine());
		int[][] appleS=new int[appleN][2];
		
		for(int i=0;i<appleN;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<2;k++){
				appleS[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		
		int turnN=Integer.parseInt(kb.readLine());
		int[][] turnS=new int[turnN][2];
		
		for(int i=0;i<turnN;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			turnS[i][0]=Integer.parseInt(stk.nextToken());
			turnS[i][1]=stk.nextToken().charAt(0);
		}
		System.out.println(T.solution(width,appleN,appleS,turnN,turnS));
	}
}