/*
내려가기
1. 문제 재정의 및 추상화 
N줄에 0~9 숫자가 세 개씩 적혀 있음. 내려가기 게임 = 첫 줄에서 시작, 마지막 줄에서 끝. 
처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작. 다음 줄로 내려갈 때 다음과 같은 제약 조건 있음. 
 - 바로 아래의 수로 내려가거나, 바로 아래의 수와 붙어있는 수로만 이동 가능.
얻을 수 있는 최대 점수, 최소 점수를 구하여라. 
DP 알고리즘을 사용해서 문제를 푼다. 

dp[i][k][0]: i번째 줄 k번째 칸에 도달. 최소 점수
dp[i][k][1]: i번째 줄 k번째 칸에 도달. 최대 점수 

점화식
dp[i][k][0]=min(dp[i][k-1][0],dp[i][k][0],dp[i][k+1][0])+v[i][k]
dp[i][k][1]=max(dp[i][k-1][1],dp[i][k][1],dp[i][k+1][1])+v[i][k]

답 : min(dp[N-1][0][0], ... dp[N-1][2][0])
	max(dp[N-1][0][1], ... dp[N-1][2][1])
	
2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_내려가기{
	
	int[][][] dp;
	final int ONE = 0, TWO = 1, THREE = 2;
	final int MIN = 0, MAX = 1;

	String solution(int n, int[][] table){
		
		String answer="";
		init(n,table);
		
		for(int i=1;i<n;i++){
			for(int k=0;k<3;k++){
				
				int min=Integer.MAX_VALUE;
				int max=Integer.MIN_VALUE;
				
				for(int p=Math.max(k-1,0);p<=Math.min(k+1,2);p++){
					min=Math.min(min,dp[i-1][p][MIN]);
					max=Math.max(max,dp[i-1][p][MAX]);
				}
				
				dp[i][k][MIN]=min+table[i][k];
				dp[i][k][MAX]=max+table[i][k];
			}
		}
		
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		
		for(int i=0;i<3;i++){
			min=Math.min(min,dp[n-1][i][MIN]);
			max=Math.max(max,dp[n-1][i][MAX]);
		}
		
		answer=max+" "+min;
		return answer;
	}
	
	void init(int n, int[][] table){
		
		dp = new int[n][3][2];
		dp[0][ONE][MIN]=dp[0][ONE][MAX]=table[0][ONE];
		dp[0][TWO][MIN]=dp[0][TWO][MAX]=table[0][TWO];
		dp[0][THREE][MIN]=dp[0][THREE][MAX]=table[0][THREE];
	}
	
	public static void main(String[] args) throws Exception{
		BJ_내려가기 T=new BJ_내려가기();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(kb.readLine());
		int[][] table = new int[n][3];
		for(int i=0;i<n;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<3;k++){
				table[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(n,table));
	}
}