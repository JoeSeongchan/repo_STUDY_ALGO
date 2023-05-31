/*
RGB 거리
1. 문제 재정의 및 추상화 
집 빨강, 초록, 파랑으로 색칠. 규칙 만족시켜야 함.
- 1번 집의 색 != 2번 집의 색
- N번 집의 색 != N-1번 집의 색 
- i번 집의 색 != i-1, i+1 번 집의 색

집의 수 주어짐. 각 집을 빨,초,파로 칠하는 비용 주어짐. 
DP 알고리즘 적용. 
빨강 = 0, 초록 = 1, 파랑 = 2
dp[i][빨강] = i번째 집 빨강으로 칠한 케이스의 최소 비용 
dp[i][초록] = i번째 집 초록으로 칠한 케이스의 최소 비용 
dp[i][파랑] = i번째 집 파랑으로 칠한 케이스의 최소 비용 
dp[i+1][색X] = min(dp[i-1][다른 색A],...)+i번째 집 색X로 칠하는 비용
답 = min(dp[N-1][빨강], dp[N-1][초록], dp[N-1][파랑])

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_RGB거리{
	
	int solution(int hN, int[][] pS){
		
		int answer=0;
		
		int[][] dp=new int[hN][3];
		dp[0]=pS[0];
		// 집
		for(int i=1;i<hN;i++){
			// 색
			for(int k=0;k<3;k++){
				int min=Integer.MAX_VALUE;
				// 이전 집 
				for(int p=0;p<3;p++){
					if(k==p)	continue;
					min=Math.min(min,dp[i-1][p]);
				}
				// 집 최소 비용 계산
				dp[i][k]=min+pS[i][k];
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i=0;i<3;i++){
			min=Math.min(min,dp[hN-1][i]);
		}
		answer=min;
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_RGB거리 T=new BJ_RGB거리();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int hN=Integer.parseInt(kb.readLine());
		int[][] pS=new int[hN][3];
		for(int i=0;i<hN;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<3;k++){
				pS[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(hN,pS));
	}
}