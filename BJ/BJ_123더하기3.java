/*
1, 2, 3 더하기 3
1. 문제 재정의 및 추상화 
정수 n이 주어졌을 때, n을 1,2,3의 합으로 나타내는 방법의 수를 구하여라. 
dp[n] := 값 n을 1,2,3의 합으로 나타내는 방법의 수
dp[n]=dp[n-1]+dp[n-2]+dp[n-3]
전형적인 DP 문제 

2. 풀이과정 상세히 적기 
생략

3. 시간복잡도 계산 
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_123더하기3{
	// 1
	// 1+1, 2
	// 1+1+1, 1+2, 2+1, 3
	int solution(int n){
		int answer=0;
		long[] dp=new long[1000000+1];
		dp[0]=0;
		dp[1]=1;
		dp[2]=2;
		dp[3]=4;
		for(int i=4;i<=n;i++){
			long sum=0;
			sum=dp[i-1]+dp[i-2]+dp[i-3];
			sum%=1000000009;
			dp[i]=sum;
		}
		answer=(int)dp[n];
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_123더하기3 T=new BJ_123더하기3();
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