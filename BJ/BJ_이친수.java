/*
이친수
1. 문제 재정의 및 추상화 
이진수 중 특별한 성질 가진 수 = 이친수
- 1로 시작
- 1 연속 두 번 등장 X 11을 부분문자열로 가지지 않음.
N이 주어졌을 때 N자리 이친수를 구하여라.
DP 알고리즘 사용해서 문제를 풀자! 
dp[i][0] = 0으로 끝나면서 i자리인 이친수의 개수
dp[i][1] = 1로 끝나면서 i자리인 이친수의 개수 
dp[i][0] = dp[i-1][0]+dp[i-1][1]
dp[i][1] = dp[i-1][0]

초기값
dp[1][0] = 0
dp[1][1] = 1

답
dp[N][0]+dp[N][1]

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_이친수{
	long solution(int n){
		long answer=0;
		long[][] dp=new long[91][2];
		dp[1][1]=1;
		for(int i=2;i<=n;i++){
			dp[i][0]=dp[i-1][0]+dp[i-1][1];
			dp[i][1]=dp[i-1][0];
		}
		answer=dp[n][0]+dp[n][1];
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_이친수 T=new BJ_이친수();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(kb.readLine());
		System.out.println(T.solution(n));
	}
}