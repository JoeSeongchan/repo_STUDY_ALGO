/*
1, 2, 3 더하기 
1. 문제 재정의 및 추상화 
전형적인 DP 문제. 작은 문제의 답으로 큰 문제의 답을 구한다. 
점화식: 
	dp(n)=dp(n-1)+dp(n-2)+dp(n-3)

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_123더하기{
	int solution(int n){
		int answer=0;
		int[] dp=new int[n+1];
		if(n>=1)
			dp[1]=1;
		if(n>=2)
			dp[2]=2;
		// 1+1+1, 1+2, 2+1, 3 
		if(n>=3)
			dp[3]=4;
		for(int i=4;i<=n;i++){
			dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
		}
		answer=dp[n];
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_123더하기 T=new BJ_123더하기();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int tcN=Integer.parseInt(kb.readLine());
		for(int tci=1;tci<=tcN;tci++){
			int n=Integer.parseInt(kb.readLine());
			sb.append(T.solution(n)).append('\n');
		}
		System.out.println(sb);
	}
}