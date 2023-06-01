/*
1, 2, 3 더하기 
1. 문제 재정의 및 추상화 
정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수. 
n의 범위 0~10 
전형적인 DP 알고리즘 적용 문제. 

dp[i] : i를 1,2,3의 합으로 나타내는 방법의 수 

점화식 
dp[i]=dp[i-1]+dp[i-2]+dp[i-3]

초기값
dp[1]=1
dp[2]=2
dp[3]=4

예시 n=4? 
dp[4]=1+2+4=7 Correct! 

* 리턴 타입 : int 

2. 풀이과정 상세히 적기 
생략

3. 시간복잡도 계산 
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_123더하기_재해결{
	int solution(int n){
		int answer=0;
		int[] dp=new int[11];
		dp[1]=1;
		dp[2]=2;
		dp[3]=4;
		for(int i=4;i<=n;i++){
			dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
		}
		answer=dp[n];
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_123더하기_재해결 T=new BJ_123더하기_재해결();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int tcN=Integer.parseInt(kb.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tci=1;tci<=tcN;tci++){
			int n=Integer.parseInt(kb.readLine());
			sb.append(T.solution(n)).append('\n');
		}
		System.out.println(sb);
	}
}