/*
스티커
1. 문제 재정의 및 추상화
어떤 한 스티커를 떼면 상하좌우에 있는 스티커는 못 쓰게 되어버린다. 
점수가 최대가 되도록 스티커를 떼는 방법을 구하라. 최대 점수값을 구한다. 
DP 알고리즘을 이용해서 문제를 푼다. 
각 줄마다 따로 처리
dp[i][0] = i번째 열 스티커를 하나도 떼지 않을 때 최대 점수 
dp[i][1] = i번째 열 1번째 행 스티커를 뗀 경우, 최대 점수
dp[i][2] = i번째 열 2번째 행 스티커를 뗀 경우, 최대 점수 

dp[i][0] = max(dp[i-1][1],dp[i-1][2])
dp[i][1] = max(dp[i-1][2],dp[i-1][0])+v[0][i]
dp[i][2] = max(dp[i-1][1],dp[i-1][0])+v[1][i]

답 : dp 배열 전부 다 순회. 최댓값 찾기 

2. 풀이과정 상세히 적기
이전 단계에서 이미 상세히 적었음 

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_스티커{
	
	int solution(int tN, int[][] tS){
		
		int answer=0;
		int[][] dp=new int[tN][3];
		
		dp[0][0]=0;
		dp[0][1]=tS[0][0];
		dp[0][2]=tS[1][0];
		
		for(int i=1;i<tN;i++){
			dp[i][0]=Math.max(dp[i-1][1],dp[i-1][2]);
			dp[i][1]=Math.max(dp[i-1][2],dp[i-1][0])+tS[0][i];
			dp[i][2]=Math.max(dp[i-1][1],dp[i-1][0])+tS[1][i];
		}
		int max=Integer.MIN_VALUE;
		for(int i=0;i<tN;i++){
			for(int k=0;k<3;k++){
				max=Math.max(max,dp[i][k]);
			}
		}
		answer=max;
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_스티커 T=new BJ_스티커();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int tcN=Integer.parseInt(kb.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tci=1;tci<=tcN;tci++){
			int tN=Integer.parseInt(kb.readLine());
			int[][] tS=new int[2][tN];
			for(int i=0;i<2;i++){
				StringTokenizer stk=new StringTokenizer(kb.readLine());
				for(int k=0;k<tN;k++){
					tS[i][k]=Integer.parseInt(stk.nextToken());
				}
			}
			sb.append(T.solution(tN,tS)).append('\n');
		}
		System.out.println(sb);
	}
}