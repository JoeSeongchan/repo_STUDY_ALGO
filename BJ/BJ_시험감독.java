/*
시험감독
1. 문제 재정의 및 추상화 
N개의 시험장. 시험장마다 응시자 존재. i번 시험장에 있는 응시자 수 = Ai 
감독관 = 총, 부. 총감독관 감시 수 = B, 부감독관 = C 
각 시험장 = 총감독관 딱 1명, 부감독관 여러명 
각 시험장마다 모든 응시생 감시. 필요한 감독관 수의 최솟값 구하자. 
최솟값을 구하는 문제! 
시험장 수 = 1~100만
각 시험장 응시자 수 = 1~100만
B, C = 1~100만 
각 시험장 응시자 수 -= B 하고 생각. 어차피 총 감독관은 딱 1명씩만 있어야 하기 때문.
Math.ceil((응시자 수 - B) /  C) = 시험장에 필요한 부감독관 수 
double의 정확도가 6자리 이상이기 때문에 부동소수점 문제 걱정하지 않아도 된다. 
단 응시자 수 <= B인 경우는 따로 처리하자! 
결과값의 최댓값 = 100만 * 100만 = 10000억 = 1조
long 타입을 가지도록 만들자! 

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산 
O(1)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_시험감독{
	long solution(int roomN, int[] roomS, int mainPersonN, int subPersonN){
		long answer=0;
		for(int room: roomS){
			answer+=1;
			if(room>mainPersonN){
				room-=mainPersonN;
				answer+=Math.ceil((double)room/subPersonN);
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_시험감독 T=new BJ_시험감독();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int roomN=Integer.parseInt(kb.readLine());
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int[] roomS=new int[roomN];
		for(int i=0;i<roomN;i++){
			roomS[i]=Integer.parseInt(stk.nextToken());
		}
		stk=new StringTokenizer(kb.readLine());
		int mainPersonN=Integer.parseInt(stk.nextToken());
		int subPersonN=Integer.parseInt(stk.nextToken());
		System.out.println(T.solution(roomN,roomS,mainPersonN,subPersonN));
	}
}