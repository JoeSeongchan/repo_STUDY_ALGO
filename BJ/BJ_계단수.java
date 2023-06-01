/*
계단 수
1. 문제 재정의 및 추상화 
길이가 N이면서 0부터 9까지 숫자가 모두 등장하는 계단 수가 총 몇 개 있는지 구하여라. 

길이가 n인 계단수 = 길이가 n-1인 계단수 뒤에 마지막 수와 1 차이나는 수를 붙임.
n*10*(2^10) 배열.
dp[len][num][state] = 길이가 len, 1의 자리수가 num, 사용한 숫자가 state인 계단 수의 개수. 

dp[1][1][..] = 1
dp[1][2][..] = 1
dp[1][3][..] = 1
dp[1][4][..] = 1

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N*10*1024)=O(N)
계산 수 : 100 * 1만 = 100만
100만 * 2 = 200만

출처:https://velog.io/@jypapapaa/%EB%B0%B1%EC%A4%80-1562-%EA%B3%84%EB%8B%A8-%EC%88%98

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_계단수{
	
	long[][][] dp;
	int n;
	
	long solution(int n){
		
		long answer=0;
		init(n);
		
		for(int i=1;i<n;i++){ // 자릿수
			for(int k=0;k<=9;k++){ // 끝 자리
				for(int p=0;p<=1023;p++){ // 선택 상태
					dp[i][k][p]%=1000000000;
					if(dp[i][k][p]==0)	continue;
					if(k>0){
						dp[i+1][k-1][p|(1<<(k-1))]+=(dp[i][k][p]);
					}
					if(k<9){
						dp[i+1][k+1][p|(1<<(k+1))]+=(dp[i][k][p]);
					}
				}
			}
		}
		long sum=0;
		for(int i=0;i<=9;i++){
			sum+=(dp[n][i][(1<<10)-1])%1000000000;
		}
		answer=sum%1000000000;
		return answer;
	}
	
	void init(int n){
		this.n=n;
		dp=new long[n+1][10][1024];
		for(int i=1;i<=9;i++){
			dp[1][i][1<<i]=1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BJ_계단수 T=new BJ_계단수();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(kb.readLine());
		System.out.println(T.solution(n));
	}
}