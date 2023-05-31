/*
줄어들지 않아
1. 문제 재정의 및 추상화 
dp[i][k]=i자리 수이면서 맨 마지막 수가 k인 '줄어들지 않는 수'

점화식
dp[i][k]=dp[i-1][0]+dp[i-1][1]+...dp[i-1][k]

답
dp[n][0]+..+dp[n][9]

답 범위에 맞는 타입 : long

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(T*N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_줄어들지않아{
	long solution(int n){
		long answer=0;
		long[][] dp=new long[n+1][10];
		Arrays.fill(dp[1],1);
		
		for(int i=2;i<=n;i++){
			for(int k=0;k<=9;k++){
				for(int p=0;p<=k;p++){
					dp[i][k]+=dp[i-1][p];
				}
			}
		}
		
		long sum=0;
		for(int i=0;i<=9;i++){
			sum+=dp[n][i];
		}
		answer=sum;
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_줄어들지않아 T=new BJ_줄어들지않아();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int tcN=Integer.parseInt(kb.readLine());
		for(int tci=1;tci<=tcN;tci++){
			int n=Integer.parseInt(kb.readLine());
			sb.append(T.solution(n)).append('\n');
		}
		System.out.println(sb);
	}
}