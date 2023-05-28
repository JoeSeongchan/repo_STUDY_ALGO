/*
피보나치 수 5
1. 문제 재정의 및 추상화 
n번째 피보나치 수를 구하여라

2. 풀이과정 상세히 적기
DP 알고리즘을 사용한다. 
시간복잡도 : O(N)

3. 시간복잡도 계산 
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_피보나치수5{
	int solution(int n){
		int answer=0;
		int[] dp=new int[21];
		dp[0]=0;
		dp[1]=1;
		for(int i=2;i<=n;i++){
			dp[i]=dp[i-1]+dp[i-2];
		}
		answer=dp[n];
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_피보나치수5 T=new Solution();
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(kb.readLine());
		System.out.println(T.solution(n));
	}
}