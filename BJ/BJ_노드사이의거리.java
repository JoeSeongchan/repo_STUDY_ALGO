/*
노드 사이의 거리 
1. 문제 재정의 및 추상화 
트리도 그래프이다. 그러기 때문에 최단 거리를 구할 때 BFS를 이용하면 된다. 
가중치가 있지만 상관 없다. 홉의 개수가 짧은 경로를 찾아도 되기 때문이다.

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(M(V+E))
V: 노드의 개수
E: 간선의 개수
M: 거리를 알고 싶은 두 노드 쌍의 개수

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_노드사이의거리 {
    
    int vN;
    List<int[]>[] adjS;
    
    String solution (int vN, int eN, int[][] eS, int pN, int[][] pS){
        String answer="";
        StringBuilder sb=new StringBuilder();
        init(vN, eN, eS);
        for(int[] p: pS){
            int v1=p[0];
            int v2=p[1];
            sb.append(calDist(v1,v2)).append('\n');
        }
        answer=sb.toString();
        return answer;
    }
    
    void init(int vN, int eN, int[][] eS){
        this.vN=vN;
        adjS=new ArrayList[vN+1];
        for(int i=1;i<=vN;i++){
            adjS[i]=new ArrayList<>();
        }
        for(int[] e:eS){
            int v1=e[0];
            int v2=e[1];
            int weight=e[2];
            adjS[v1].add(new int[]{v2,weight});
            adjS[v2].add(new int[]{v1,weight});
        }
    }
    
    int calDist(int v1, int v2){
        Queue<Integer> tskS=new ArrayDeque<>();
        tskS.offer(v1);
        int[] distS=new int[vN+1];
        Arrays.fill(distS,-1);
        distS[v1]=0;
        outer: while(!tskS.isEmpty()){
            int v=tskS.poll();
            int dist=distS[v];
            for(int[] adj: adjS[v]){
                int adjVertex=adj[0];
                int weight=adj[1];
                if(distS[adjVertex]!=-1)    continue;
                distS[adjVertex]=dist+weight;
                tskS.offer(adjVertex);
                if(adjVertex==v2)   break outer;
            }
        }
        return distS[v2];
    }
    
    public static void main(String[] args) throws Exception{
        BJ_노드사이의거리 T=new BJ_노드사이의거리();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int vN=Integer.parseInt(stk.nextToken());
        int pN=Integer.parseInt(stk.nextToken());
        int eN=vN-1;
        int[][] eS=new int[eN][3];
        for(int i=0;i<eN;i++){
            stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<3;k++){
                eS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        int[][] pS=new int[pN][2];
        for(int i=0;i<pN;i++){
            stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                pS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(vN,eN,eS,pN,pS));
    }
}