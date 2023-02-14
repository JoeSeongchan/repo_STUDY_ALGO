/*
No. 2 이진수 표현

1. 문제 재정의 및 추상화 
M의 이진수 표현의 마지막 N비트가 모두 1로 켜져 있는지 확인한다. 
비트연산자를 사용해서 확인한다. (비트마스킹 사용)

2. 풀이과정 상세히 적기 
M&1<<(N+1)-1==1<<(N+1)-1 인지 확인 

3. 시간복잡도 계산 
O(T) 
T: 테스트케이스 개수 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class Solution{
	boolean solution(int n,int m){
		boolean answer=false;
		if((m&((1<<n)-1))==(1<<n)-1){
			answer=true;
		}
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		Solution T=new Solution();
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int tcN=Integer.parseInt(kb.readLine());
		for(int tci=1;tci<=tcN;tci++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			int n=Integer.parseInt(stk.nextToken());
			int m=Integer.parseInt(stk.nextToken());
			String result="OFF";
			if(T.solution(n,m)){
				result="ON";
			}
			sb.append('#').append(tci).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}
}
