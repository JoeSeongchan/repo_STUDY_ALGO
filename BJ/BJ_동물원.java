/*
동물원
1. 문제 재정의 및 추상화 
'스티커' 문제와 비슷
상하좌우로 인접한 호랑이가 있으면 안 됨. DP 알고리즘을 이용해서 문제를 풀어보자!
dp[i][0] = i행,0열에 호랑이 배치하는 경우의 수 
dp[i][1] = i행,1열에 호랑이 배치하는 경우의 수
dp[i][2] = i행에 호랑이 배치하지 않는 경우의 수 

점화식
dp[i][0] = dp[i-1][1]+dp[i-1][2]
dp[i][1] = dp[i-1][0]+dp[i-1][2]
dp[i][2] = dp[i-1][0]+dp[i-1][1]+dp[i-1][2]

답 
dp[N-1][0]+dp[N-1][1]+dp[N-1][2]

예시: N=4
0 1 2

1 1 1
2 2 3 
5 5 7
12 12 17
=> 24+17 = 41 Correct! 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_동물원{
	
	int solution(int n){
		
		int answer=0;
		int[][] dp=new int[n][3];
		Arrays.fill(dp[0],1);
		
		for(int i=1;i<n;i++){
			dp[i][0]=(dp[i-1][1]+dp[i-1][2])%9901;
			dp[i][1]=(dp[i-1][0]+dp[i-1][2])%9901;
			dp[i][2]=(dp[i-1][0]+dp[i-1][1]+dp[i-1][2])%9901;
		}
		answer=(dp[n-1][0]+dp[n-1][1]+dp[n-1][2])%9901;
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_동물원 T=new BJ_동물원();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(kb.readLine());
		System.out.println(T.solution(n));
	}
}