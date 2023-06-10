/*
주사위 굴리기
1. 문제 재정의 및 추상화 
크기가 NxM인 지도. 동서남북. 위에 주사위가 하나 있음. 전개도 주어짐. 지도의 좌표 = (r,c). r=행 index, c=열 index. 
지도의 각 칸에는 정수가 하나씩 쓰여있음. 지도 칸 수 = 0인 경우, 주사위의 바닥면에 쓰여 있는 수가 복사됨. !=0 인 경우, 칸에 쓰여 있는 수가 주사위 바닥면에 복사됨. 칸에 쓰여 있는 수는 0이 됨. 
주사위를 놓은 곳의 좌표와 이동시키는 명령이 주어짐. 주사위가 이동할 때마다 상단에 쓰여 있는 값을 구하여라. 주사위는 지도의 바깥으로 이동X. 바깥으로 이동시키는 명령은 무시. 출력X 
행 수, 열 수, 주사위 초기 위치, 명령 개수 주어짐. 주사위 초기 위치 칸 수=0. 지도의 각 칸에 쓰여 있는 수는 10 미만의 자연수 또는 0. 이동하는 명령이 주어짐. 
전형적인 시뮬레이션 문제. 차근차근 문제를 풀어나가면 된다. Class 정의 잘하자! 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(K)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_주사위굴리기{
	
	static class Dice{
		
		private int lower;
		private int east;
		private int north;
		private int[] valS;
		
		Dice(int lower, int east, int north){
			this.lower=lower;
			this.east=east;
			this.north=north;
			valS=new int[7];
			for(int i=1;i<=6;i++){
				valS[i]=0;
			}
		}
		
		static int getOpposite(int num){
			switch(num){
				case 1: return 6;
				case 2: return 5;
				case 3: return 4;
				case 4: return 3;
				case 5: return 2;
				case 6: return 1;
			}
			return 1;
		}
		
		void apply(int mapVal){
			//System.out.printf("바닥면 %d에 값 %d를 씁니다.\n",lower,mapVal);
			valS[lower]=mapVal;
		}
		
		int getUpper(){
			//System.out.printf("바닥면 : %d, 실제 쓰여 있는 수 : %d\n",lower,valS[lower]);
			return valS[getOpposite(lower)];
		}
		
		int getLower(){
			return valS[lower];
		}
		
		void goEast(){
			int eastTemp=east;
			east=getOpposite(lower);
			lower=eastTemp;
		}
		
		void goWest(){
			int eastTemp=east;
			east=lower;
			lower=getOpposite(eastTemp);
		}
		
		void goNorth(){
			// lower=1, east=3
			// east는 고정
			// lower만 north로 바뀜. 
			// north는 lower의 upper 값 가짐. 
			int northTemp=north;
			north=getOpposite(lower);
			lower=northTemp;
		}
		
		void goSouth(){
			// lower=1, east=3
			// east는 고정 
			// lower는 north의 upper로 바뀜.
			// north는 lower로 바뀜. 
			int northTemp=north;
			north=lower;
			lower=getOpposite(northTemp);
		}
		
	}
	
	static class Game{
		
		Dice dice;
		int[][] map;
		int rowN, colN;
		int diceRi, diceCi;
		// 동서북남
		final int[] driS=new int[]{0,0,-1,1};
		final int[] dciS=new int[]{1,-1,0,0};
		
		Game(int rowN, int colN, int[][] map, int ri, int ci){
			this.rowN=rowN;
			this.colN=colN;
			this.map=map;
			dice=new Dice(1,3,2);
			diceRi=ri;
			diceCi=ci;
		}
		
		int run(int command){
			command-=1;
			int newRi=diceRi+driS[command];
			int newCi=diceCi+dciS[command];
			if(!isValid(newRi,newCi)){
				return -1;
			}
			diceRi=newRi;
			diceCi=newCi;
			switch(command){
				case 0: dice.goEast(); break;
				case 1: dice.goWest(); break;
				case 2: dice.goNorth(); break;
				case 3: dice.goSouth(); break;
			}
			int lower=dice.getLower();
			int mapVal=map[diceRi][diceCi];
			if(mapVal==0){
				map[diceRi][diceCi]=lower;
			} else{
				dice.apply(mapVal);
				map[diceRi][diceCi]=0;
			}
			//return map[diceRi][diceCi];
			return dice.getUpper();
		}
		
		boolean isValid(int ri, int ci){
			if(ri<0||ri>=rowN||ci<0||ci>=colN)	return false;
			return true;
		}
	}
	
	String solution(int rowN, int colN, int ri, int ci, int commandN, int[][] map, int[] commandS){
		String answer="";
		Game game=new Game(rowN, colN, map, ri, ci);
		StringBuilder sb=new StringBuilder();
		for(int command: commandS){
			int upper=game.run(command);
			if(upper==-1)	continue;
			sb.append(upper).append('\n');
		}
		answer=sb.toString();
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_주사위굴리기 T=new BJ_주사위굴리기();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		
		int rowN=Integer.parseInt(stk.nextToken());
		int colN=Integer.parseInt(stk.nextToken());
		int ri=Integer.parseInt(stk.nextToken());
		int ci=Integer.parseInt(stk.nextToken());
		int commandN=Integer.parseInt(stk.nextToken());
		
		int[][] map=new int[rowN][colN];
		for(int i=0;i<rowN;i++){
			stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<colN;k++){
				map[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		
		int[] commandS=new int[commandN];
		stk=new StringTokenizer(kb.readLine());
		for(int i=0;i<commandN;i++){
			commandS[i]=Integer.parseInt(stk.nextToken());
		}
		
		System.out.println(T.solution(rowN,colN,ri,ci,commandN,map,commandS));
	}
}