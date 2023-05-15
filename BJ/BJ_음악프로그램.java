/*
음악 프로그램 
1. 문제 재정의 및 추상화 
각 음악 PD들이 가져온 가수 순서를 모두 만족하는 하나의 가수 출연 순서를 구하여라. 
위상 정렬 알고리즘을 활용하여 문제를 푼다. 각 PD가 조사한 가수 출연 순서를 바탕으로 그래프의 인접 정점 리스트를 만들고, 
진입 차수 배열을 만든다. 두 배열을 가지고 위상정렬로 문제를 푼다. 

2. 풀이과정 상세히 적기 
생략 

3. 시간복잡도 계산
O(V+E)
V: 정점의 개수
E: 간선의 개수 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class Solution{
	
	int singerN; 
	List<Integer>[] adjS;
	int[] inEdgeS;
	Queue<Integer> tskS;
	
	String solution(int singerN, int pdN, List<Integer>[] orderS){
		String answer="";
		init(singerN, pdN, orderS);
		List<Integer> order=new ArrayList<>();
		// System.out.println(Arrays.toString(inEdgeS));
		while(!tskS.isEmpty()){
			int p=tskS.poll();
			order.add(p);
			for(int adj:adjS[p]){
				inEdgeS[adj]-=1;
				if(inEdgeS[adj]==0){
					tskS.offer(adj);
				}
			}
			// System.out.println(Arrays.toString(inEdgeS));
		}
		if(order.size()!=singerN){
			answer="0";
		} else{
			StringBuilder sb=new StringBuilder();
			for(int v: order){
				sb.append(v).append('\n');
			}
			answer=sb.toString();
		}
		
		return answer;
	}
	
	void init(int singerN, int pdN, List<Integer>[] orderS){
		this.singerN=singerN;
		adjS=new ArrayList[singerN+1];
		inEdgeS=new int[singerN+1];
		for(int i=1;i<=singerN;i++)	adjS[i]=new ArrayList<Integer>();
		for(List<Integer> order: orderS){
			int prev=order.get(0);
			for(int i=1;i<order.size();i++){
				int cur=order.get(i);
				inEdgeS[cur]+=1;
				adjS[prev].add(cur);
				prev=cur;
			}
		}
		tskS=new ArrayDeque<>();
		for(int i=1;i<=singerN;i++){
			if(inEdgeS[i]==0){
				tskS.offer(i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		Solution T=new Solution();
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(kb.readLine());
		
		int singerN=Integer.parseInt(stk.nextToken());
		int pdN=Integer.parseInt(stk.nextToken());
		List<Integer>[] orderS=new ArrayList[pdN];
		
		for(int i=0;i<pdN;i++){
			orderS[i]=new ArrayList<Integer>();
			stk=new StringTokenizer(kb.readLine());
			stk.nextToken();
			while(stk.hasMoreTokens()){
				orderS[i].add(Integer.parseInt(stk.nextToken()));
			}
		}
		System.out.println(T.solution(singerN, pdN, orderS));
	}
}