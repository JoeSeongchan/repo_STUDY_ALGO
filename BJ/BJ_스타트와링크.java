/*
스타트와 링크 
1. 문제 재정의 및 추상화 
N명. N=짝수. N/2로 나눠야 함. 1~N 번호 배정. Sij = i번 사람, j번 사람이 같은 팀에 속했을 때 팀에 더해지는 능력치. 팀의 능력치 = 팀에 속한 모든 쌍의 능력치 Sij의 합. Sij!=Sji일 수 있음. i,j같은 팀=>Sij+Sji 더해짐. 
두 팀의 능력치 차이를 최소로 하려 함. 팀의 능력치 차이의 최솟값을 구하여라. 
완전탐색으로 문제 풀어도 될듯!. 20C10 = 2^20 정도라고 봐도 됨. 1000*1000 = 100_0000 = 100만. 조합 알고리즘 적용. 

2. 풀이과정 상세히 적기 
생략

3. 시간복잡도 계산 
O(2^N)
조합 시간복잡도 = O(2^N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_스타트와링크{
	
	int[][] synergy;
	int personN;
	int teamPersonN;
	int minSynergyDiff;
	
	int solution(int personN, int[][] synergy){
		int answer=0;
		init(personN, synergy);
		combination(new int[teamPersonN],0,0);
		answer=minSynergyDiff;
		return answer;
	}
	
	void init(int personN, int[][] synergy){
		this.personN=personN;
		teamPersonN=personN/2;
		this.synergy=synergy;
		minSynergyDiff=Integer.MAX_VALUE;
	}
	
	void combination(int[] teamA, int cnt, int personIdx){
		if(cnt>=teamPersonN){
			int[] teamB=getAnotherTeam(teamA);
			int synergySumA=0,synergySumB=0;
			synergySumA=getSynergySum(teamA);
			synergySumB=getSynergySum(teamB);
			int synergyDiff=Math.abs(synergySumA-synergySumB);
			minSynergyDiff=Math.min(minSynergyDiff,synergyDiff);
			return;
		}
		for(int i=personIdx;i<personN;i++){
			teamA[cnt]=i;
			combination(teamA,cnt+1,i+1);
		}
	}
	
	int[] getAnotherTeam(int[] team){
		int[] anotherTeam=new int[teamPersonN];
		boolean[] status=new boolean[personN];
		for(int i=0;i<teamPersonN;i++){
			int k=team[i];
			status[k]=true;
		}
		int anotherTeamIdx=0;
		for(int i=0;i<personN;i++){
			if(status[i])	continue;
			anotherTeam[anotherTeamIdx++]=i;
		}
		return anotherTeam;
	}
	
	int getSynergySum(int[] team){
		int sum=0;
		for(int i=0;i<teamPersonN;i++){
			int first=team[i];
			for(int k=0;k<teamPersonN;k++){
				if(i==k)	continue;
				int second=team[k];
				sum+=synergy[first][second];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_스타트와링크 T=new BJ_스타트와링크();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		
		int personN=Integer.parseInt(kb.readLine());
		int[][] synergy=new int[personN][personN];
		for(int i=0;i<personN;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<personN;k++){
				synergy[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(personN,synergy));
	}
}