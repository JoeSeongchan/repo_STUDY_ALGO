/*
카드 구매하기 
1. 문제 재정의 및 추상화 
PS 카드 여러 종류 있음. 카드팩으로 구매. 1~N개 카드팩 존재 (N 종류)
카드 수가 적은데 비싼 카드팩 => 높은 등급
돈을 최대한 많이 지불해서 카드 N개 구매. 
카드 i개 포함된 카드팩 가격 =Pi

카드팩의 가격이 주어질 때, N개의 카드를 구매하기 위해 민규가 지불해야 하는 금액의 최댓값은? N개보다 많은 카드를 사면 안 된다. N개의 카드가 딱 맞아야 한다. 
DP 알고리즘을 사용해서 문제를 푼다. 
dp[i]:=i개의 카드 구매 최대 비용 
v[i]:=i개의 카드가 들어 있는 카드팩 구매 비용 
dp[i]=max(dp[i-1]+v[1],dp[i-2]+v[2], ...)

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N^2)
최대 연산 횟수 : 100_0000 = 100만 회 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_카드구매하기{
	int solution(int cN, int[] pS){
		int answer=0;
		int[] dp=new int[cN+1];
		for(int i=1;i<=cN;i++){
			int max=Integer.MIN_VALUE;
			for(int k=1;k<=i;k++){
				max=Math.max(max,dp[i-k]+pS[k]);
			}
			dp[i]=max;
		}
		answer=dp[cN];
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_카드구매하기 T=new BJ_카드구매하기();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int cN=Integer.parseInt(kb.readLine());
		int[] pS=new int[cN+1];
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		for(int i=1;i<=cN;i++){
			pS[i]=Integer.parseInt(stk.nextToken());
		}
		System.out.println(T.solution(cN,pS));
	}
}