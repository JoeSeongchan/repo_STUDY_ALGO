/*
1학년
1. 문제 재정의 및 추상화 
전형적인 DP 알고리즘 적용 문제이다. 
마지막 두 숫자 사이에는 =을 넣고, 나머지에는 +, -을 넣는다. 맞는 등식이 나오는 경우의 수를 구하라. 중간 계산값이 음수이거나 20을 넘어서면 안 된다. 

dp[i][k] = i개의 숫자 사용. 총합 k인 경우의 수
v[k] = k번째 수 

점화식
dp[i][k]=dp[i-1][k-v[k]]+dp[i-1][k+v[k]]

답
dp[n-1][v[n-1]]

2. 풀이과정 상세히 적기
중간 계산 값이 음수이거나 20을 넘어서면 그냥 무시한다.

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_1학년{
	long solution(int vN, int[] vS){
		long answer=0;
		int opN=vN-1;
		int[] opS=Arrays.copyOfRange(vS,0,vN-1);
		int result = vS[vN-1];
		long[][] dp=new long[opN+1][21];
		dp[1][opS[0]]=1;
		for(int i=2;i<=opN;i++){
			int opIdx=i-1;
			int op = opS[opIdx];
			for(int k=0;k<=20;k++){
				int minus = k-op;
				if(minus>=0 && minus<=20){
					dp[i][k]+=dp[i-1][minus];
				}
				int plus = k+op;
				if(plus>=0 && plus<=20){
					dp[i][k]+=dp[i-1][plus];
				}
			}
		}
		/*for(int i=1;i<=opN;i++){
			for(int k=0;k<=20;k++){
				System.out.printf("%d ",dp[i][k]);
			}
			System.out.println();
		}*/
		answer=dp[opN][result];
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_1학년 T=new BJ_1학년();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int vN=Integer.parseInt(kb.readLine());
		int[] vS=new int[vN];
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		for(int i=0;i<vN;i++){
			vS[i]=Integer.parseInt(stk.nextToken());
		}
		System.out.println(T.solution(vN,vS));
	}
}