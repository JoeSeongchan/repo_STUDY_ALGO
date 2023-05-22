/*
작업 
1. 문제 재정의 및 추상화 
선행 관계가 주어질 때, 모든 작업을 완료하기 위해 걸리는 시간을 구하여라 
전형적인 위상정렬 문제이다. 

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산 
O(V+E)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_작업{
	
	int vN;
	List<Integer>[] adjS;
	int[] tS;
	int[] inEdgeS;
	Queue<Integer> tskS;
	int[] totalTimeS;
	
	int solution(int vN, List<Integer>[] adjS, int[] tS){
		int answer=0;
		init(vN,adjS,tS);
		
		while(!tskS.isEmpty()){
			
			int v=tskS.poll();
			totalTimeS[v]+=tS[v];
			
			for(int adj:adjS[v]){
				inEdgeS[adj]-=1;
				totalTimeS[adj]=Math.max(totalTimeS[adj],totalTimeS[v]);
				if(inEdgeS[adj]==0){
					tskS.offer(adj);
				}
			}
		}
		int maxTime=Integer.MIN_VALUE;
		for(int i=1;i<=vN;i++){
			maxTime=Math.max(maxTime,totalTimeS[i]);
		}
		answer=maxTime;
		return answer;
	}
	
	void init(int vN, List<Integer>[] adjS, int[] tS){
		this.vN=vN;
		this.adjS=adjS;
		this.tS=tS;
		inEdgeS=new int[vN+1];
		tskS=new ArrayDeque<>();
		for(int i=1;i<=vN;i++){
			for(int adj:adjS[i]){
				inEdgeS[adj]+=1;
			}
		}
		totalTimeS=new int[vN+1];
		Arrays.fill(totalTimeS,Integer.MIN_VALUE);
		for(int i=1;i<=vN;i++){
			if(inEdgeS[i]==0){
				tskS.offer(i);
				totalTimeS[i]=0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BJ_작업 T=new BJ_작업();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int vN=Integer.parseInt(kb.readLine());
		List<Integer>[] adjS=new ArrayList[vN+1];
		for(int i=1;i<=vN;i++){
			adjS[i]=new ArrayList<Integer>();
		}
		int[] tS=new int[vN+1];
		for(int i=1;i<=vN;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			tS[i]=Integer.parseInt(stk.nextToken());
			int adjN=Integer.parseInt(stk.nextToken());
			for(int k=0;k<adjN;k++){
				int adj=Integer.parseInt(stk.nextToken());
				adjS[adj].add(i);
			}
		}
		System.out.println(T.solution(vN, adjS, tS));
	}
}