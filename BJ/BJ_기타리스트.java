/*
기타리스트
1. 문제 재정의 및 추상화 
다가오는 공연에서 연주할 N개의 곡을 연주. 매번 곡이 시작되기 전에 볼륨을 바꾸고 연주. 각각의 곡이 시작하기 전에 바꿀 수 있는 볼륨 리스트가 있음. 항상 리스트에 적힌 차이로만 볼륨을 바꿀 수 있음. 
현재 볼륨 : P
i번째 곡 : P+V[i] 또는 P-V[i] 로만 연주 가능
단 볼륨 값 범위는 0~M 이다. 
마지막 곡을 연주할 수 있는 볼륨 최댓값을 구하여라. 
모든 곡은 리스트에 적힌 순서대로 연주해야 한다. 
전형적인 DP 알고리즘 적용 문제. 

dp[i][k] : i번째 곡을 k 볼륨으로 끝내는 경우의 수
v[i] : i번째 곡 시작 전 볼륨 변동 값 

점화식 
dp[i][k] = dp[i-1][k-v[i]]+dp[i-1][k+v[i]]
단 0~M 범위 벗어나는 값은 제외한다. (무시)

답 
dp[N-1][0], .. dp[N-1][M] 중에서 1 이상이면서 열의 값이 가장 큰 것을 찾는다. 

2. 풀이과정 상세히 적기
dp[N-1][0], ... dp[N-1][M] 이 다 0이라면 답은 -1이다.

3. 시간복잡도 계산
O(N)

* 반환 타입 : int, 배열 타입 int

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_기타리스트{
	int solution(int songN, int st, int max, int[] songS){
		int answer=0;
		int[][] dp=new int[songN][max+1];
		int up=st+songS[0];
		int down=st-songS[0];
		if(up<=max){
			dp[0][up]=1;
		}
		if(down>=0){
			dp[0][down]=1;
		}
		for(int i=1;i<songN;i++){ // 노래
			for(int k=0;k<=max;k++){ // 볼륨
				// 가능하지 않기에 skip!
				up=k+songS[i]; // up
				down=k-songS[i]; // down
				// 기준은 k. 이전 볼륨은 up, down
				// 기준을 하나로 고정하는 게 중요! 기준은 현재 곡!
				if(up<=max){
					dp[i][k]+=dp[i-1][up];
				}
				if(down>=0){
					dp[i][k]+=dp[i-1][down];
				}
			}
		}
		int maxAv=-1;
		for(int i=0;i<=max;i++){
			if(dp[songN-1][i]==0)	continue;
			maxAv=Math.max(maxAv,i);
		}
		answer=maxAv;
		
		return answer;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_기타리스트 T=new BJ_기타리스트();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int songN=Integer.parseInt(stk.nextToken());
		int st=Integer.parseInt(stk.nextToken());
		int max=Integer.parseInt(stk.nextToken());
		stk=new StringTokenizer(kb.readLine());
		int[] songS=new int[songN];
		for(int i=0;i<songN;i++){
			songS[i]=Integer.parseInt(stk.nextToken());
		}
		System.out.println(T.solution(songN,st,max,songS));
	}
}