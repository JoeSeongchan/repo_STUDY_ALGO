/*
최단경로
1. 문제 재정의 및 추상화 
하나의 출발점, 여러 개의 도착점이 주어질 때 최단 경로를 구하여라 
Dijkstra 알고리즘을 사용한다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산 
O(VlogV)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_최단경로_2nd{
	
	static class Edge{
		int target;
		int weight;
		Edge(int target,int weight){
			this.target=target;
			this.weight=weight;
		}
	}
	
	int vN;
	List<Edge>[] adjS;
	int[] distS;
	
	String solution(int vN, int eN, int st, int[][] eS){
		
		String answer="";
		init(vN,eN,st,eS);
		PriorityQueue<int[]> tskS=new PriorityQueue<>((i1,i2)->Integer.compare(i1[1],i2[1]));
		tskS.offer(new int[]{st,0});
		
		while(!tskS.isEmpty()){
			int[] p=tskS.poll();
			int v=p[0];
			int tw=p[1];
			if(distS[v]<tw)	continue;
			for(Edge adj: adjS[v]){
				int target=adj.target;
				int weight=adj.weight;
				if(distS[target]<=tw+weight)	continue;
				distS[target]=tw+weight;
				tskS.offer(new int[]{target,distS[target]});
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=vN;i++){
			if(i==st){
				sb.append(0).append('\n');
				continue;
			}
			if(distS[i]==Integer.MAX_VALUE)	{
				sb.append("INF").append('\n');
				continue;
			}
			sb.append(distS[i]).append('\n');
		}
		answer=sb.toString();
		return answer;
	}
	
	void init(int vN,int eN,int st,int[][] eS){
		
		this.vN=vN;
		adjS=new ArrayList[vN+1];
		for(int i=1;i<=vN;i++)	adjS[i]=new ArrayList<>();
		
		for(int[] e:eS){
			int v1=e[0],v2=e[1],weight=e[2];
			adjS[v1].add(new Edge(v2,weight));
		}
		
		distS=new int[vN+1];
		Arrays.fill(distS,Integer.MAX_VALUE);
		distS[st]=0;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_최단경로_2nd T=new BJ_최단경로_2nd();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		
		int vN=Integer.parseInt(stk.nextToken());
		int eN=Integer.parseInt(stk.nextToken());
		int st=Integer.parseInt(kb.readLine());
		
		int[][] eS=new int[eN][3];
		for(int i=0;i<eN;i++){
			stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<3;k++){
				eS[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(T.solution(vN,eN,st,eS));
	}
}