/*
오르막 수 
1. 문제 재정의 및 추상화
수의 길이가 주어졌을 때 오르막 수의 개수를 구하여라. 
수의 길이의 범위: 1~1000
1~1000자리 최댓값을 하나하나 검사할 수 없다. DP 또는 이분탐색 또는 그리디로 문제를 풀어야 한다. 
O(N), O(N^2) 시간복잡도로 문제를 풀 수는 없을까? 
O(N^2) 시간복잡도로 문제 풀 수 있을 것 같다. 
점화식 
dp(n,k) := n자리의 오르막 수의 개수 (단 마지막 자리 수가 k이다! )
dp(n,k) = dp(n-1,0)*10 + dp(n-1,1)*9 + ... + dp(n-1,9)*1

시간복잡도: O(N^2)

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(N^2)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_오르막수{
	
	int solution(int n){
		
		int answer=0;
		int[][] dp=new int[1001][10];
		Arrays.fill(dp[1],1);
		
		for(int i=2;i<=n;i++){
			for(int k=0;k<10;k++){
				int sum=0;
				for(int p=0;p<=k;p++){
					sum+=dp[i-1][p];
					sum%=10007;
				}
				dp[i][k]=sum;
			}
		}
		int totalSum=0;
		for(int i=0;i<=9;i++){
			totalSum+=dp[n][i];
			totalSum%=10007;
		}
		answer=totalSum;
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_오르막수 T=new BJ_오르막수();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(kb.readLine());
		System.out.println(T.solution(n));
	}
}