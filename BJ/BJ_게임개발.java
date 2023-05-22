/*
게임 개발 
1. 문제 재정의 및 추상화 
N개의 각 건물이 완성되기까지 걸리는 최소 시간을 구하여라 
각 건물은 먼저 지어져야 하는 선수 건물이 존재한다. 
최소 시간을 기록하고 있는 배열을 둔다. 

2. 풀이과정 상세히 적기
전형적인 위상정렬 알고리즘 문제 
생략 

3. 시간복잡도 계산
O(V+E)
V: 건물의 수 
E: 건물과 건물 사이의 관계 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_게임개발{
	int vN;
	List<Integer>[] adjS;
	int[] tS;
	int[] inEdgeS;
	int[] totalTimeS;
	Queue<Integer> tskS;
	
	String solution(int vN, List<Integer>[] adjS, int[] tS){
		String answer="";
		init(vN,adjS,tS);
		while(!tskS.isEmpty()){
			
			int v=tskS.poll();
			// System.out.println(v+","+tS[v]+","+totalTimeS[v]);
			totalTimeS[v]=tS[v]+totalTimeS[v];
			for(int adj:adjS[v]){
				totalTimeS[adj]=Math.max(totalTimeS[v],totalTimeS[adj]);
				inEdgeS[adj]-=1;
				if(inEdgeS[adj]==0){
					tskS.offer(adj);
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=vN;i++){
			sb.append(totalTimeS[i]).append('\n');
		}
		answer=sb.toString();
		return answer;
	}
	
	void init(int vN, List<Integer>[] adjS, int[] tS){
		this.vN=vN;
		this.adjS=adjS;
		this.tS=tS;
		
		inEdgeS=new int[vN+1];
		
		for(int i=1;i<=vN;i++){
			for(int adj:adjS[i]){
				inEdgeS[adj]+=1;
			}
		}
		tskS=new ArrayDeque<>();
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
		BJ_게임개발 T=new BJ_게임개발();
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
			// System.out.println(tS[i]);
			while(true){
				int adj=Integer.parseInt(stk.nextToken());
				if(adj==-1)	break;
				adjS[adj].add(i);
			}
		}
		System.out.println(T.solution(vN,adjS,tS));
	}
}