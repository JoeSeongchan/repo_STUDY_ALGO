/*
나무 탈출
1. 문제 재정의 및 추상화
간선 리스트 -> 인접 정점 리스트
루트부터 시작하여 BFS. 연결된 자식 노드가 없을 때 그 노드의 level(=depth)를 기록한다. (더한다) depth의 총합 == 홀수? 성원 win! 짝수? 성원 lose!

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산  
O(N+N-1) = O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;

public class BJ_나무탈출{
    
    int vN;
    List<Integer>[] adjS;
    
    String solution(int vN, int eN, int[][] eS){
        String answer="";
        init(vN,eN,eS);
        int depthSum=bfs();
        if(depthSum%2==1){
            answer="Yes";
        } else{
            answer="No";
        }
        return answer;
    }
    
    void init(int vN, int eN, int[][] eS){
        this.vN=vN;
        adjS=new ArrayList[vN+1];
        for(int i=1;i<=vN;i++){
            adjS[i]=new ArrayList<Integer>();
        }
        for(int[] e: eS){
            int v1=e[0],v2=e[1];
            adjS[v1].add(v2);
            adjS[v2].add(v1);
        }
    }
    
    int bfs(){
        Queue<Integer> tskS=new ArrayDeque<>();
        int[] depthS=new int[vN+1];
        Arrays.fill(depthS,-1);
        tskS.offer(1);
        depthS[1]=0;
        int depthSum=0;
        while(!tskS.isEmpty()){
            int v=tskS.poll();
            int depth=depthS[v];
            boolean isLeaf=true;
            for(int adj:adjS[v]){
                if(depthS[adj]!=-1) continue;
                depthS[adj]=depth+1;
                tskS.offer(adj);
                isLeaf=false;
            }
            if(isLeaf){
                depthSum+=depth;
            }
        }
        return depthSum;
    }
    
    public static void main(String[] args) throws Exception{
        BJ_나무탈출 T=new BJ_나무탈출();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int vN=Integer.parseInt(kb.readLine());
        int eN=vN-1;
        int[][] eS=new int[eN][2];
        
        for(int i=0;i<eN;i++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                eS[i][k]=Integer.parseInt(stk.nextToken());
            }    
        }
        System.out.println(T.solution(vN,eN,eS));
    }
}