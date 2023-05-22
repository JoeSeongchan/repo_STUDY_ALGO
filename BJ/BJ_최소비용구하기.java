/*
최소 비용 구하기 
1. 문제 재정의 및 추상화 
그래프가 주어질 때, 출발 노드에서 도착 노드까지 가는 최단 거리를 구하여라. 
최단 거리 알고리즘을 적용하면 된다. 다익스트라.. 
다익스트라 알고리즘은 우선순위 큐를 사용한다. (Prim과 비슷하다. )
새로운 최단 거리를 발견하면 우선순위 큐에 넣는다. 점점 확장해나간다. 
그러다가 최단 거리가 더 이상 업데이트되지 않으면 중단한다. 

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산 
O(VlogV)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_최소비용구하기{
	
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
	
	int solution(int vN, int eN, int[][] eS, int st, int ed){
		int answer=0;
		init(vN,eN,eS,st);
		PriorityQueue<int[]> tskS=new PriorityQueue<>((i1,i2)->Integer.compare(i1[1],i2[1]));
		tskS.offer(new int[]{st,0});
		int visitedNum=0;
		
		while(!tskS.isEmpty()){
			
			int[] p=tskS.poll();
			int v=p[0],tw=p[1];
			visitedNum+=1;
			// 이미 최적화된 경로가 검토된 경우, 
			if(tw>distS[v])	continue;
			
			for(Edge adj:adjS[v]){
				int target=adj.target;
				int weight=adj.weight;
				if(distS[target]<=weight+tw)	continue;
				distS[target]=weight+tw;
				tskS.offer(new int[]{target,distS[target]});
			}
		}
		answer=distS[ed];
		return answer;
	}
	
	void init(int vN,int eN,int[][] eS, int st){
		this.vN=vN;
		adjS=new ArrayList[vN+1];
		
		for(int i=1;i<=vN;i++)	adjS[i]=new ArrayList<>();
		for(int[] e:eS){
			int src=e[0],tar=e[1],wei=e[2];
			adjS[src].add(new Edge(tar,wei));
		}
		
		distS=new int[vN+1];
		Arrays.fill(distS,Integer.MAX_VALUE);
		distS[st]=0;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_최소비용구하기 T=new BJ_최소비용구하기();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		int vN=Integer.parseInt(kb.readLine());
		int eN=Integer.parseInt(kb.readLine());
		int[][] eS=new int[eN][3];
		for(int i=0;i<eN;i++){
			StringTokenizer stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<3;k++){
				eS[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int st=Integer.parseInt(stk.nextToken());
		int ed=Integer.parseInt(stk.nextToken());
		System.out.println(T.solution(vN,eN,eS,st,ed));
	}
}