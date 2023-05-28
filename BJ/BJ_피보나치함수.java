/*
피보나치 함수
1. 문제 재정의 및 추상화 
f(N)의 값을 구하려면 f(0), f(1)을 얼마나 사용해야 하는가? 
전형적인 DP 문제인 듯 보인다. 
f(0) = 0 0:1, 1:0
f(1) = 1 0:0, 1:1
f(2) = f(0)+f(1) = 1 0:1, 1:1
f(3) = f(2)+f(1) = 2 0:1, 1:2
f(4) = f(3)+f(2) = 3 0:2, 1:3
...
피보나치 수열의 DP 해결법과 크게 다르지 않다. 
이전값의 f(1), f(0) 사용 개수값을 활용하면 된다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_피보나치함수{
	String solution(int n){
		String answer="";
		int[][] dp=new int[41][2];
		dp[0][0]=1;
		dp[1][1]=1;
		for(int i=2;i<=n;i++){
			dp[i][0]=dp[i-1][0]+dp[i-2][0];
			dp[i][1]=dp[i-1][1]+dp[i-2][1];
		}
		StringBuilder sb=new StringBuilder();
		sb.append(dp[n][0]).append(' ').append(dp[n][1]);
		answer=sb.toString();
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_피보나치함수 T=new BJ_피보나치함수();
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