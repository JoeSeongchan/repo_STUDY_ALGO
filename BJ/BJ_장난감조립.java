/*
장난감 조립 
1. 문제 재정의 및 추상화 
하나의 완제품을 만들기 위해 필요한 기본 부품의 개수를 구하여라 
전형적인 위상정렬 알고리즘 문제이다. 

2. 풀이과정 상세히 적기
엣지 가중치값을 따로 기록해두고 있어야 한다. (개수)
완제품을 만들기 위해서 필요한 부품의 수를 따로 기록해두고 있어야 한다. 
(배열의 크기 = 부품 종류 * 부품 종류)

3. 시간복잡도 계산 
O(V+E)

4. 코드 작성 
*/

import java.util.*;
import java.io.*;

public class BJ_장난감조립{
	int vN;
	int[][] partS;
	List<Edge>[] adjS;
	int[] inEdgeS;
	Queue<Edge> tskS;
	boolean[] isSecondary;
	
	static class Edge{
		int targetNode;
		int num;
		Edge(int targetNode, int num){
			this.targetNode=targetNode;
			this.num=num;
		}
	}
	
	String solution(int vN, int eN, int[][] eS){
		String answer="";
		init(vN,eN,eS);
		
		while(!tskS.isEmpty()){
			Edge e=tskS.poll();
			int targetNode=e.targetNode;
			int num=e.num;
			for(Edge adj:adjS[targetNode]){
				inEdgeS[adj.targetNode]-=1;
				for(int i=1;i<=vN;i++){
					if(partS[targetNode][i]!=0){
						partS[adj.targetNode][i]+=(partS[targetNode][i])*adj.num;
					}
				}
				if(inEdgeS[adj.targetNode]==0){
					tskS.offer(adj);
				}
			}
		}
		
		StringBuilder sb=new StringBuilder();
		/*for(int i=1;i<=vN;i++){
			for(int k=1;k<=vN;k++){
				System.out.printf("%d ",partS[i][k]);
			}
			System.out.println();
		}*/
		for(int i=1;i<vN;i++){
			if(isSecondary[i])	continue;
			sb.append(i).append(' ').append(partS[vN][i]).append('\n');
		}
		
		answer=sb.toString();
		return answer;
	}
	
	void init(int vN, int eN, int[][] eS){
		
		this.vN=vN;
		partS=new int[vN+1][vN+1];
		adjS=new ArrayList[vN+1];
		
		for(int i=1;i<=vN;i++)	adjS[i]=new ArrayList<>();
		
		inEdgeS=new int[vN+1];
		tskS=new ArrayDeque<>();
		isSecondary=new boolean[vN+1];
		
		for(int[] e:eS){
			int v1=e[0],v2=e[1],num=e[2];
			isSecondary[v1]=true;
			adjS[v2].add(new Edge(v1,num));
			inEdgeS[v1]+=1;
		}
		
		for(int i=1;i<=vN;i++){
			if(inEdgeS[i]==0){
				tskS.offer(new Edge(i,1));
				partS[i][i]=1;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BJ_장난감조립 T= new BJ_장난감조립();
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
		System.out.println(T.solution(vN,eN,eS));
	}
}