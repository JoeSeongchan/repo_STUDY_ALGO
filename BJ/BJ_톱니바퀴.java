/*
톱니바퀴
1. 문제 재정의 및 추상화 
8개의 톱니를 가지고 있는 4개의 톱니바퀴. 톱니는 N, S. 톱니바퀴에 번호 배정. 톱니바퀴를 K번 회전. 시계방향 or 시계반대방향. 
회전 직전에 옆에 있는 톱니바퀴의 톱니와 극이 다르다면 반대방향으로 회전. 
여러 톱니바퀴가 순차적으로 영향을 받아 회전할 수 있음. 
단순 시뮬레이션 문제. 순차적으로 영향을 받는 것을 어떻게 구현할 것인가? Queue에 회전 사실을 알리는 인스턴스를 넣는다. 회전한 톱니바퀴에 의해 영향을 받는 톱니바퀴가 만약 회전한다면, 이전에 회전의 주체가 되는 톱니바퀴는 이에 영향을 받지 않아야 한다. (로직으로 따로 구현.) 

2. 풀이과정 상세히 적기
톱니바퀴 = Class로 정의. 왼쪽 톱니 = index 0, 오른쪽 톱니 = index 4
회전 = ArrayDeque을 오른쪽으로 shift. ArrayDeque의 get으로 특정 index 위치의 값을 쉽게 알 수 있다. 
톱니바퀴의 12시 방향의 값 = index 2

3. 시간복잡도 계산
O(톱니바퀴의 수 * 회전 횟수 * 톱니 수)

오답노트:
	1에 보수 비트 연산자를 적용하면 0이 안 나온다. 
	111111....0이 결과값으로 나온다!

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_톱니바퀴{
	
	static class Wheel{
		int idx;
		int tobniN;
		LinkedList<Character> tobniS;
		
		Wheel(int idx,int tobniN, char[] rawWheel){
			this.idx=idx;
			this.tobniN=tobniN;
			tobniS=new LinkedList<>();
			for(char rawTobni: rawWheel){
				char tobni=' ';
				// 0: N극, 1: S극
				if(rawTobni=='0'){
					tobni='N';
				} else{
					tobni='S';
				}
				tobniS.offerLast(tobni);
			}
		}
		
		char getUpper(){
			return tobniS.get(0);
		}
		
		char getLeft(){
			return tobniS.get(6);
		}
		
		char getRight(){
			return tobniS.get(2);
		}
		
		void rotate(){
			char last=tobniS.pollLast();
			tobniS.offerFirst(last);
			//System.out.printf("%d 번째 바퀴가 시계방향으로 회전합니다.\n",idx);
		}
		
		void rotateReverse(){
			char first=tobniS.pollFirst();
			tobniS.offerLast(first);
			//System.out.printf("%d 번째 바퀴가 시계반대방향으로 회전합니다.\n",idx);
		}
		
		@Override
		public String toString(){
			return String.format("%d 번째 바퀴 || 왼쪽: %c | 오른쪽: %c | 위쪽: %c",idx,getLeft(),getRight(),getUpper());
		}
	}
	
	static class Rotation{
		int prevWheelIdx;
		int wheelIdx;
		int direction;
		
		Rotation(int prevWheelIdx, int wheelIdx, int direction){
			this.prevWheelIdx=prevWheelIdx;
			this.wheelIdx=wheelIdx;
			this.direction=direction;
		}
	}
	
	Wheel[] wheelS;
	int wheelN;
	
	Rotation[] rotationS;
	int rotationN;
	
	int solution(char[][] rawWheelS, int wheelN, int tobniN, int rotationN, int[][] rawRotationS){
		int answer=0;
		init(rawWheelS, wheelN, tobniN, rotationN, rawRotationS);
		for(Rotation rotation: rotationS){
			run(rotation);
		}
		answer=calculateGrade();
		return answer;
	}
	
	void init(char[][] rawWheelS, int wheelN, int tobniN, int rotationN, int[][] rawRotationS){
		this.wheelN=wheelN;
		wheelS=new Wheel[wheelN+1];
		
		for(int i=1;i<=wheelN;i++){
			wheelS[i]=new Wheel(i,tobniN,rawWheelS[i-1]);
		}
		
		this.rotationN=rotationN;
		rotationS=new Rotation[rotationN];
		
		for(int i=0;i<rotationN;i++){
			int[] rawRotation=rawRotationS[i];
			int wheelIdx=rawRotation[0];
			int direction=0;
			// 1: 시계방향, 0: 시계 반대방향
			if(rawRotation[1]==1){
				direction=1;
			} else{
				direction=0;
			}
			
			rotationS[i]=new Rotation(-1,wheelIdx,direction);
		}
	}
	
	void run(Rotation rotation){
		
		Queue<Rotation> queue=new ArrayDeque<>();
		queue.offer(rotation);
		//System.out.println("\n명령시작");
		while(!queue.isEmpty()){
			//debug();
			Rotation rt=queue.poll();
			int leftWheelIdx=rt.wheelIdx-1;
			int rightWheelIdx=rt.wheelIdx+1;
			Wheel targetWheel = wheelS[rt.wheelIdx];
			
			if(leftWheelIdx>0 && leftWheelIdx<=wheelN && leftWheelIdx!=rt.prevWheelIdx){
				Wheel leftWheel=wheelS[leftWheelIdx];
				char rightTobniOfLeftWheel=leftWheel.getRight();
				char leftTobniOfTargetWheel=targetWheel.getLeft();
				if(rightTobniOfLeftWheel!=leftTobniOfTargetWheel){
					int newDirection = 0;
					if(rt.direction==0)	newDirection=1;
					queue.offer(new Rotation(rt.wheelIdx,leftWheelIdx,newDirection));
				}
			}
			if(rightWheelIdx>0 && rightWheelIdx<=wheelN && rightWheelIdx!=rt.prevWheelIdx){
				Wheel rightWheel=wheelS[rightWheelIdx];
				char leftTobniOfRightWheel=rightWheel.getLeft();
				char rightTobniOfTargetWheel=targetWheel.getRight();
				if(leftTobniOfRightWheel!=rightTobniOfTargetWheel){
					int newDirection = 0;
					if(rt.direction==0)	newDirection=1;
					queue.offer(new Rotation(rt.wheelIdx,rightWheelIdx,newDirection));
				}
			}
			if(rt.direction==1){
				targetWheel.rotate();
			} else{
				targetWheel.rotateReverse();
			}
		}
	}
	
	int calculateGrade(){
		int result=0;
		char first=wheelS[1].getUpper();
		if(first=='S')	result+=1;
		char second=wheelS[2].getUpper();
		if(second=='S')	result+=2;
		char third=wheelS[3].getUpper();
		if(third=='S')	result+=4;
		char fourth=wheelS[4].getUpper();
		if(fourth=='S')	result+=8;
		return result;
	}
	
	void debug(){
		System.out.println();
		for(int i=1;i<=wheelN;i++){
			Wheel wheel = wheelS[i];
			System.out.println(wheel);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BJ_톱니바퀴 T=new BJ_톱니바퀴();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		
		int wheelN=4, tobniN=8;
		char[][] rawWheelS=new char[wheelN][tobniN];
		for(int ri=0;ri<wheelN;ri++){
			rawWheelS[ri]=kb.readLine().toCharArray();
		}
		
		int rotationN=Integer.parseInt(kb.readLine());
		int[][] rawRotationS=new int[rotationN][2];
		for(int i=0;i<rotationN;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			rawRotationS[i][0]=Integer.parseInt(stk.nextToken());
			rawRotationS[i][1]=Integer.parseInt(stk.nextToken());
		}
		System.out.println(T.solution(rawWheelS, wheelN, tobniN, rotationN, rawRotationS));
	}
}