/*
DFS와 BFS
1. 문제 재정의 및 추상화
전형적인 DFS, BFS 문제. 인접 정점 리스트, 인접 행렬, 간선 리스트 중 어떤 자료구조를 쓸까? 가장 효율적인 인접 정점 리스트를 쓰기로 한다. 

2. 풀이과정 상세히 적기 
생략

3. 시간복잡도 계산
O(V+E)
V=정점의 개수
E=간선의 개수

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_DFS와BFS{
    
    int vN, eN, startVt;
    List<Integer>[] adjS;
    
    String solution(int vN, int eN, int startVt, int[][] eS){
        String answer="";
        init(vN,eN,startVt,eS);
        StringBuilder sb=new StringBuilder();
        sb.append(dfs()).append('\n');
        sb.append(bfs()).append('\n');
        answer=sb.toString();
        return answer;
    }
    
    void init(int vN, int eN, int startVt, int[][] eS){
        this.vN=vN;
        this.eN=eN;
        this.startVt=startVt;
        adjS=new ArrayList[vN+1];
        for(int i=1;i<=vN;i++){
            adjS[i]=new ArrayList<Integer>();
        }
        for(int[] e: eS){
            adjS[e[0]].add(e[1]);
            adjS[e[1]].add(e[0]);
        }
        for(int i=1;i<=vN;i++){
            Collections.sort(adjS[i]);
        }
    }
    
    String dfs(){
        StringBuilder sb=new StringBuilder();
        recurse(startVt,new boolean[vN+1],sb);
        return sb.toString();
    }
    
    void recurse(int v,boolean[] visited,StringBuilder sb){
        visited[v]=true;
        sb.append(v).append(' ');
        
        for(int adj: adjS[v]){
            if(visited[adj])    continue;
            recurse(adj,visited,sb);
        }
    }
    
    String bfs(){
        Queue<Integer> tskS=new ArrayDeque<>();
        tskS.offer(startVt);
        boolean[] visited=new boolean[vN+1];
        visited[startVt]=true;
        StringBuilder sb=new StringBuilder();
        while(!tskS.isEmpty()){
            int v=tskS.poll();
            sb.append(v).append(' ');
            for(int adj: adjS[v]){
                if(visited[adj])    continue;
                visited[adj]=true;
                tskS.offer(adj);
            }
        }
        return sb.append('\n').toString();
    }
    
    public static void main(String[] args) throws Exception{
        BJ_DFS와BFS T=new BJ_DFS와BFS();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int vN=Integer.parseInt(stk.nextToken());
        int eN=Integer.parseInt(stk.nextToken());
        int startVt=Integer.parseInt(stk.nextToken());
        int[][] eS=new int[eN][2];
        for(int i=0;i<eN;i++){
            stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                eS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(vN,eN,startVt,eS));
    }
}