/*
트리와 쿼리 
1. 문제 재정의 및 추상화 
루트가 있는 트리가 주어진다. 쿼리는 서브트리의 정점 개수를 구하는 것이다. 
어떻게 하면 서브트리의 정점 개수를 편하게 계산할 수 있을까? 
모든 문제에 대한 답을 미리 계산하면 된다. 
모든 정점에 대해서 서브트리를 고려하여 서브트리의 정점 개수를 계산한다. 

2. 풀이과정 상세히 적기 
이걸 어떻게 할 수 있을까? DFS 로 트리 순회. 별도의 배열에 현재 노드 - 리프 노드 범위 내의 노드 개수를 더해가며 값을 만들어간다. 

3. 시간복잡도 계산 
O(N+N-1+Q)
O(N+Q)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_트리와쿼리_재해결{
	int vN,r,qN;
	List<Integer>[] adjS;
	int[] qS;
	int[] subTreeNodeNumS;
	boolean[] visited;
	
	String solution(int vN, int r, int qN, int eN, int[][] eS, int[] qS){
		String solution="";
		init(vN,r,qN,eN,eS,qS);
		dfs(r);
		StringBuilder sb=new StringBuilder();
		for(int q: qS){
			sb.append(subTreeNodeNumS[q]).append('\n');
		}
		solution=sb.toString();
		return solution;
	}
	
	void init(int vN, int r, int qN, int eN, int[][] eS, int[] qS){
		this.vN=vN;
		this.r=r;
		this.qN=qN;
		adjS=new ArrayList[vN+1];
		for(int i=1;i<=vN;i++){
			adjS[i]=new ArrayList<Integer>();
		}
		for(int[] e: eS){
			int v1=e[0], v2=e[1];
			adjS[v1].add(v2);
			adjS[v2].add(v1);
		}
		this.qS=qS;
		subTreeNodeNumS=new int[vN+1];
		visited=new boolean[vN+1];
	}
	
	int dfs(int nd){
		int sum=1;
		visited[nd]=true;
		for(int adj:adjS[nd]){
			if(visited[adj])	continue;
			sum+=dfs(adj);
		}
		subTreeNodeNumS[nd]=sum;
		return sum;
	}
	
	public static void main(String[] args) throws Exception{
		BJ_트리와쿼리_재해결 T=new BJ_트리와쿼리_재해결();
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		int vN=Integer.parseInt(stk.nextToken());
		int r=Integer.parseInt(stk.nextToken());
		int qN=Integer.parseInt(stk.nextToken());
		int eN=vN-1;
		int[][] eS=new int[eN][2];
		for(int i=0;i<eN;i++){
			stk=new StringTokenizer(kb.readLine());
			for(int k=0;k<2;k++){
				eS[i][k]=Integer.parseInt(stk.nextToken());
			}
		}
		int[] qS=new int[qN];
		for(int i=0;i<qN;i++){
			qS[i]=Integer.parseInt(kb.readLine());
		}
		System.out.println(T.solution(vN,r,qN,eN,eS,qS));
	}
}