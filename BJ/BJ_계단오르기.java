/*
계단 오르기
1. 문제 재정의 및 추상화 
점화식 
dp[a][0] = 직전에 한 계단 올라와서 a 지점에 도착했을 때 점수 최댓값 
dp[a][1] = 직전에 두 계단 올라와서 a 지점에 도착했을 때 점수 최댓값 
dp[a][0] = dp[a-1][1] + arr[a]
dp[a][1] = max(dp[a-2][0],dp[a-2][1]) + arr[a]

2. 풀이과정 자세히 적기 
생략

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_계단오르기{
	int solution(int n, int[] arr){
		int answer=0;
		
		final int ONE = 0;
		final int TWO = 1;
		
		int[][] dp=new int[301][2];
		dp[1][ONE]=arr[1];
		if(n>=2){
			dp[2][ONE]=arr[1]+arr[2];
			dp[2][TWO]=arr[2];
		}
		for(int i=3;i<=n;i++){
			dp[i][ONE]=dp[i-1][TWO]+arr[i];
			if(i>=2)
				dp[i][TWO]=Math.max(dp[i-2][ONE],dp[i-2][TWO])+arr[i];
		}
		// System.out.println(Arrays.toString(dp[n]));
		answer=Math.max(dp[n][ONE],dp[n][TWO]);
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_계단오르기 T=new BJ_계단오르기();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(kb.readLine());
		int[] arr=new int[n+1];
		for(int i=1;i<=n;i++){
			arr[i]=Integer.parseInt(kb.readLine());
		}
		System.out.println(T.solution(n,arr));
	}
}