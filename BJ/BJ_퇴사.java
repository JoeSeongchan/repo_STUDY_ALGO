/*
퇴사
1. 문제 재정의 및 추상화 
N+1일 퇴사. N일동안 최대한 많은 상담. 
하루에 하나씩 서로 다른 사람의 상담 잡음. 
상담 완료 기간 = Ti, 금액 = Pi
N+1일째에는 회사에 없기 때문에 상담할 수 없음. 1~N일에만 상담 가능. 
상담원이 얻을 수 있는 최대 수익을 구하여라. 
DP 알고리즘을 사용한다. 
dp[k] = k일째에 얻을 수 있는 최대 수익
dp[k+Tk] = max(dp[k]+Pk,dp[k+Tk])
dp[k+Tk+1] = max(dp[k]+Pk,dp[k+Tk+1]) ... 끝까지... 

답 : dp[1],...,dp[N+1] 중에서 최댓값 찾기
예시 적용 가능?

1
2
3 
4 10
5 30
6 30
7 45
가능! 

2. 풀이과정 상세히 적기 
생략

3. 시간복잡도 계산
O(N^2)
최대 연산 횟수 = 225
무리 없이 문제 해결 가능! 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_퇴사{
	int solution(int dayN, int[][] dayS){
		int answer=0;
		int[] dp=new int[dayN+2];
		for(int i=1;i<=dayN;i++){
			int t=dayS[i-1][0];
			int p=dayS[i-1][1];
			int curP=dp[i];
			for(int k=i+t;k<=dayN+1;k++){
				dp[k]=Math.max(dp[k],curP+p);
			}
		}
		int maxP=Integer.MIN_VALUE;
		for(int i=1;i<=dayN+1;i++){
			maxP=Math.max(maxP,dp[i]);
		}
		answer=maxP;
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_퇴사 T=new BJ_퇴사();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int dayN=Integer.parseInt(kb.readLine());
		int[][] dayS=new int[dayN][2];
		for(int i=0;i<dayN;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<2;k++){
				dayS[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(dayN,dayS));
	}
}