package 조성찬;

/*
9229. 한빈이와 Spot Mart 

1. 문제 재정의 및 추상화 
과자를 딱 두 봉지 산다. 최대 무게를 구하여라. 
무게 합 제한을 넘지 않는 최대 무게를 구하여라. 
1000개의 과자봉지 중에서 2개를 고른다. 1000*999/2 대략 1000^2=100_0000=100만
조합으로 문제 풀어도 된다. 그래도 시간 안에 문제 풀 수 있다. 

2. 풀이과정 상세히 적기
조합을 사용해 1000개의 과자 봉지 중에서 2개의 과자봉지를 고른다. 
두 개의 과자봉지의 무게 합을 구하고, 만약 최대 무게를 갱신할 수 있으면 갱신한다.

3. 시간복잡도 계산 
O(N^2)
N: 과자봉지의 개수

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class Solution{
	int greatest;
	int vN;
	int limit;
	int[] vS;
	
	int solution(int vN, int limit, int[] vS){
		int answer=0;
		init(vN,limit,vS);
		comb(0,0,0);
		answer=greatest;
		return answer;
	}
	
	void init(int vN, int limit, int[] vS){
		this.vN=vN;
		this.limit=limit;
		this.vS=vS;
		greatest=-1;
	}
	
	void comb(int count, int startIdx, int sum){
		if(sum>limit){
			return;
		}
		if(count>=2){
			greatest=Math.max(greatest,sum);
			return;
		}
		if(startIdx>=vN){
			return;
		}
		for(int i=startIdx;i<vN;i++){
			comb(count+1,i+1,sum+vS[i]);
		}
	}
	
	public static void main(String[] args) throws Exception{
		Solution T=new Solution();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int tcN=Integer.parseInt(kb.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tci=1;tci<=tcN;tci++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			int vN=Integer.parseInt(stk.nextToken());
			int limit=Integer.parseInt(stk.nextToken());
			int[] vS=new int[vN];
			stk=new StringTokenizer(kb.readLine());
			for(int i=0;i<vN;i++){
				vS[i]=Integer.parseInt(stk.nextToken());
			}
			int answer=T.solution(vN,limit,vS);
			sb.append('#').append(tci).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}
}
