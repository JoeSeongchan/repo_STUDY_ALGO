/*
ACM Craft
1. 문제 재정의 및 추상화 
일단 진입 차수를 미리 계산해놓는다. 그런 다음 건물 하나의 건설 완료 소식이 들릴 때마다 그 건물을 선수 건물로 삼고 있는 건물의 진입 차수를 하나 줄인다. (선수 건물이란 선수 과목과 같이 어떤 건물을 짓기 전에 먼저 지어야 하는 건물을 말한다. ) 어떤 건물이 지어짐에 따라 다른 건물의 진입 차수가 0이 되었을 때, 그 건물을 지을 준비가 되었다는 말과 같다. 그래서 지금까지 조사한 선수 건물들의 총 건설 시간 중에서 최댓값을 가지고 건설을 시작한다. 
BFS + 위상정렬 알고리즘 적용해서 문제를 푼다. 

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산
O(V+E)
V: 건물의 수 
E: 건물과 건물 사이의 관계 (간선)의 수 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_ACMCraft{
	int[] inEdgeS;
	int[] timeS;
	int bN;
	int[] bS;
	int target;
	List<Integer>[] adjS;
	Queue<Integer> tskS;
	
	int solution(int bN, int rN, int[] bS, int[][] rS, int target){
		int answer=0;
		init(bN,rN,bS,rS,target);
		while(!tskS.isEmpty()){
			int b=tskS.poll();
			timeS[b]=timeS[b]+bS[b-1];
			if(b==target)	break;
			for(int adj:adjS[b]){
				timeS[adj]=Math.max(timeS[adj],timeS[b]);
				inEdgeS[adj]-=1;
				if(inEdgeS[adj]==0)	tskS.offer(adj);
			}
		}
		answer=timeS[target];
		return answer;
	}
	
	void init(int bN, int rN, int[] bS, int[][] rS, int target){
		this.bN=bN;
		this.bS=bS;
		this.target=target;
		inEdgeS=new int[bN+1];
		timeS=new int[bN+1];
		adjS=new ArrayList[bN+1];
		for(int i=1;i<=bN;i++)	adjS[i]=new ArrayList<Integer>();
		tskS=new ArrayDeque<>();
		for(int[] r: rS){
			int b1=r[0], b2=r[1];
			inEdgeS[b2]+=1;
			adjS[b1].add(b2);
		}
		for(int i=1;i<=bN;i++){
			if(inEdgeS[i]==0)	tskS.offer(i);
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BJ_ACMCraft T=new BJ_ACMCraft();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int tcN=Integer.parseInt(kb.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tci=1;tci<=tcN;tci++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			int bN=Integer.parseInt(stk.nextToken());
			int rN=Integer.parseInt(stk.nextToken());
			int[] bS=new int[bN];
			stk=new StringTokenizer(kb.readLine());
			for(int i=0;i<bN;i++){
				bS[i]=Integer.parseInt(stk.nextToken());
			}
			int[][] rS=new int[rN][2];
			for(int i=0;i<rN;i++){
				stk=new StringTokenizer(kb.readLine());
				for(int k=0;k<2;k++){
					rS[i][k]=Integer.parseInt(stk.nextToken());
				}
			}
			int target=Integer.parseInt(kb.readLine());
			sb.append(T.solution(bN,rN,bS,rS,target)).append('\n');
		}
		System.out.println(sb);
	}
}