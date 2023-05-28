/*
파일 합치기 
1. 문제 재정의 및 추상화 
dp[x][y] : x~y번째 파일들을 합치는데 필요한 최소비용.
sum[x] : 1~x번째 파일까지의 크기 합.

2. 풀이과정 상세히 적기
dp[i][j] = min(dp[i][j], dp[i][m] + dp[m+1][j] + sum[j] - sum[i-1])

** 참고 
https://cocoon1787.tistory.com/317

3. 시간복잡도 계산 
O(V^3)
V: 장 수 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_파일합치기{
	int[][] dp;
	int[] sumS;
	
	int solution(int vN, int[] vS){
		int answer=0;
		init(vN,vS);
		for(int diff=1;diff<=vN-1;diff++){
			for(int st=0;st+diff<vN;st++){
				int ed=st+diff;
				int min=Integer.MAX_VALUE;
				int sum=0;
				if(st==0){
					sum=sumS[ed];
				}else{
					sum=sumS[ed]-sumS[st-1];
				}
				for(int mid=st;mid<ed;mid++){
					min=Math.min(min,dp[st][mid]+dp[mid+1][ed]+sum);
				}
				dp[st][ed]=min;
			}
		}
		answer=dp[0][vN-1];
		return answer;
	}
	
	void init(int vN, int[] vS){
		dp=new int[vN][vN];
		sumS=new int[vN];
		sumS[0]=vS[0];
		for(int i=1;i<vN;i++){
			sumS[i]=vS[i]+sumS[i-1];
		}
	}
	
	public static void main(String[] args) throws Exception{
		BJ_파일합치기 T=new BJ_파일합치기();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int tcN=Integer.parseInt(kb.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tci=1;tci<=tcN;tci++){
			int vN=Integer.parseInt(kb.readLine());
			int[] vS=new int[vN];
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int i=0;i<vN;i++){
				vS[i]=Integer.parseInt(stk.nextToken());
			}
			sb.append(T.solution(vN,vS)).append('\n');
		}
		System.out.println(sb);
	}
}