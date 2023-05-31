/*
포도주 시식
1. 문제 재정의 및 추상화 
3개의 포도주 잔을 연속으로 마실 수는 없다. 
1 2 4 5 이런 식으로 마셔야 한다. 
최대로 마시는 경우를 구하여라. 
DP 알고리즘을 사용한다. 
dp[i][k] = i번째 포도주 잔을 연속으로 마실 때 마신 포도주 양의 최댓값 (지금 연속으로 마신 잔의 수 = k)
dp[i][0] = max(dp[i-1][0],dp[i-1][1],dp[i-1][2])
dp[i][1] = max(dp[i-2][0],dp[i-2][1],dp[i-2][2])+v[i]
dp[i][2] = dp[i-1][1]+v[i]

답 : max(dp[n-1][0], dp[n-1][1], dp[n-1][2])

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_포도주시식{
	int solution(int vN, int[] vS){
		int answer=0;
		int[][] dp=new int[10001][3];
		final int ONE = 0;
		final int TWO = 1;
		final int ZERO = 2;
		dp[0][ONE]=vS[0];
		dp[0][TWO]=0;
		dp[0][ZERO]=0;
		if(vN>1){
			dp[1][ONE]=vS[1];
			dp[1][TWO]=dp[0][ONE]+vS[1];
			dp[1][ZERO]=Math.max(dp[0][ZERO],dp[0][ONE]);
			dp[1][ZERO]=Math.max(dp[1][ZERO],dp[0][TWO]);
		}
		for(int i=2;i<vN;i++){
			int prevMax=Math.max(dp[i-2][ZERO],dp[i-2][ONE]);
			prevMax=Math.max(prevMax,dp[i-2][TWO]);
			dp[i][ONE]=prevMax+vS[i];
			dp[i][TWO]=dp[i-1][ONE]+vS[i];
			dp[i][ZERO]=Math.max(dp[i-1][ZERO],dp[i-1][ONE]);
			dp[i][ZERO]=Math.max(dp[i][ZERO],dp[i-1][TWO]);
		}
		int max=Integer.MIN_VALUE;
		for(int i=0;i<vN;i++){
			max=Math.max(max,dp[i][ZERO]);
			max=Math.max(max,dp[i][ONE]);
			max=Math.max(max,dp[i][TWO]);
		}
		answer=max;
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_포도주시식 T=new BJ_포도주시식();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int vN=Integer.parseInt(kb.readLine());
		int[] vS=new int[vN];
		for(int i=0;i<vN;i++){
			vS[i]=Integer.parseInt(kb.readLine());
		}
		System.out.println(T.solution(vN,vS));
	}
}