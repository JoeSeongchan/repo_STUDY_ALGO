/*
1. 문제 재정의 및 추상화 
인접 리스트, 인접 행렬, 간선 리스트 중에서 간선 리스트 형태로 데이터 주어짐
그래프 알고리즘: Kruskal, Prim, Dijkstra
Kruskal: Union-Find, 간선 중심
Prim: Greedy, 정점 중심
Dijkstra: Greedy, 정점 중심 
    Prim과 Dijkstra는 비슷한 알고리즘 (정점 중심 알고리즘)
    비록 목적은 다르지만... (MST vs 최단 경로)
Prim과 Dijkstra는 정점 중심 알고리즘이다. 인접 리스트를 만들어서 푼다.
그런데 이 문제는 가중치가 0이기 때문에 굳이 Dijkstra 알고리즘을 사용해서 문제를 풀 필요가 없다. BFS를 쓴다. 
간선 리스트 -> 인접 리스트 변환

2. 풀이과정 상세히 적기
간선 리스트 -> 인접 리스트 변환
출발 노드 1. 1번 노드에서 출발하여 인접 정점으로 이동 (BFS)
distance 배열 필요. distance 배열로 visited 배열 대체

** 주의할 점(오답노트): 
    - 거리가 가장 긴 노드를 구할 때, 최대 거리보다 작은 거리의 노드는 count에서 제외해야 한다.
    그걸 놓쳐서 30분 동안 시간을 잡아먹혔다. 코드를 잘 읽고 잘 작성하자! 
3. 시간 복잡도 계산
O(V+E)
V=노드의 개수
E=간선의 개수

4. 코드 작성 
*/
import java.util.*;
class Solution {
    int nodeN;
    List<Integer>[] adjS;
    int[] distS;
    
    public int solution(int nodeN, int[][] eS) {
        int answer = 0;
        init(nodeN, eS);
        bfs();
        answer=count();
        return answer;
    }
    
    void init(int nodeN, int[][] eS){
        this.nodeN=nodeN;
        adjS=new ArrayList[nodeN+1];
        for(int i=1;i<=nodeN;i++){
            adjS[i]=new ArrayList<>();
        }
        for(int[] e: eS){
            int v1=e[0];
            int v2=e[1];
            adjS[v1].add(v2);
            adjS[v2].add(v1);
        }
        distS=new int[nodeN+1];
        Arrays.fill(distS,Integer.MAX_VALUE);
        // 노드 번호는 1번부터 시작
    }
    
    void bfs(){
        Queue<Integer> tskS=new ArrayDeque<>();
        tskS.offer(1);
        distS[1]=0;
        while(!tskS.isEmpty()){
            int v=tskS.poll();
            // System.out.println(v);
            for(int adj: adjS[v]){
                if(distS[adj]!=Integer.MAX_VALUE)   continue;
                distS[adj]=distS[v]+1;
                tskS.offer(adj);
            }
        }
    }
    
    int count(){
        int max=Integer.MIN_VALUE;
        int maxCnt=0;
        for(int i=2;i<=nodeN;i++){
            if(distS[i]==Integer.MAX_VALUE) continue;
            if(max>distS[i]){ // 최대 거리보다 거리가 짧은 노드는 count에서 제외한다! 
                continue;
            }
            if(max<distS[i]){
                max=distS[i];
                maxCnt=1;
                
                continue;
            }
            maxCnt+=1;
            
        }
        return maxCnt;
    }
}