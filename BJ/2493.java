/*
탑 (BJ2493)

1. 문제 재정의 및 추상화 
N개의 탑을 왼쪽에서 오른쪽으로 나란히 하나의 선을 이루도록 세운다.
탑의 꼭대기에 레이저 송신기가 있고, 왼쪽으로 레이저를 쏜다. 
탑의 기둥에는 레이저 수신기가 있다. 레이저는 가장 먼저 만나는 수신기에서만 수신된다. 
왼쪽에 자기보다 높은 탑이 있을 때만 레이저가 수신된다. 왼쪽에서 가장 가까운, 
높이는 더 높은 탑을 찾는다. 
각각의 탑에서 발사한 레이저 신호를 어느 탑이 수신하는가? 

N: 50만
높이 범위: 1~1억
시간 제한 1.5초
O(N^2) 절대 안 된다. 
O(N) 또는 O(NlogN) 시간복잡도를 가진 알고리즘으로 풀어야 한다. 
스택을 사용한다. 이런 종류의 문제는 스택을 사용해서 푼다. 
123456 이런 경우, 배열의 왼쪽 끝까지 탐색해야 한다. 그런데 
스택을 이용하여 top이 현재 탑의 높이보다 작다면 top을 pop하고 현재 탑의 높이를 push한다면
이런 경우를 피할 수 있다. 
top이 현재 탑의 높이보다 크다면 현재 탑의 높이를 push한다. 왜냐하면 거리가 우선순위가 높기 때문이다. 
top이 현재탑의 높이보다 클 때까지 top을 계속해서 pop한다. 그러다가 발견하면 해당 높이를 
답으로 가져가고, 만약 발견하지 못했다면 (Stack이 비어 있음) 0을 답으로 가져간다. 

2. 풀이과정 상세히 적기
생략

3. 시간 복잡도 계산 
O(N)?

4. 코드 작성 
*/

import java.util.*;
import java.io.*;
public class Main{
	static class Building{
		int idx;
		int height;
		Building(int idx,int height){
			this.idx=idx;
			this.height=height;
		}
	}
	
	int[] solution(int bN,int[] bS){
		int[] answer=new int[bN];
		Stack<Building> st=new Stack<>();
		for(int i=0;i<bN;i++){
			int b=bS[i];
			while(true){
				if(st.isEmpty()){
					answer[i]=0;
					st.push(new Building(i,b));
					break;
				}
				if(b>=st.peek().height){
					st.pop();
					continue;
				}
				if(b<st.peek().height){
					answer[i]=st.peek().idx+1;
					st.push(new Building(i,b));
					break;
				}
			}
		}
		return answer;	
	}
	
	public static void main(String[] args) throws Exception{
		Main T=new Main();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int bN=Integer.parseInt(kb.readLine());
		int[] bS=new int[bN];
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		for(int i=0;i<bN;i++){
			bS[i]=Integer.parseInt(stk.nextToken());
		}
		int[] answer=T.solution(bN,bS);
		StringBuilder sb=new StringBuilder();
		for(int v: answer){
			sb.append(v).append(' ');
		}
		sb.append('\n');
		System.out.print(sb);
	}
}
