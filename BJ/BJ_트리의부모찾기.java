/*
트리의 부모 찾기
1. 문제 재정의 및 추상화 
트리. 루트 없음. 루트=1. 각 노드의 부모 구하기.
노드의 개수=N, 연결 정보=N-1 (루트 노드)
2번 노드~N번 노드 부모 노드 번호 출력
알고리즘: BFS. 인접 정점 정보 (부모-자식)

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(N)

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_트리의부모찾기{
    int vN;
    List<Integer>[] adjS;
    
    String solution(int vN, int[][] vInfoS){
        String answer="";
        init(vN,vInfoS);
        int[] parentS=new int[vN+1];
        Queue<Integer> tskS=new ArrayDeque<>();
        tskS.offer(1);
        
        while(!tskS.isEmpty()){
            int p=tskS.poll();
            for(int adj: adjS[p]){
                if(parentS[adj]!=0) continue;
                parentS[adj]=p;
                tskS.offer(adj);
            }
        }
        
        StringBuilder sb=new StringBuilder();
        for(int i=2;i<=vN;i++){
            sb.append(parentS[i]).append('\n');
        }
        
        answer=sb.toString();
        return answer;
    }
    
    void init(int vN, int[][] vInfoS){
        this.vN=vN;
        adjS=new ArrayList[vN+1];
        for(int i=1;i<=vN;i++){
            adjS[i]=new ArrayList<Integer>();
        }
        for(int[] vInfo: vInfoS){
            int v1=vInfo[0],v2=vInfo[1];
            adjS[v1].add(v2);
            adjS[v2].add(v1);
        }
    }
    
    public static void main(String[] args) throws Exception{
        BJ_트리의부모찾기 T=new BJ_트리의부모찾기();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int vN=Integer.parseInt(kb.readLine());
        int[][] vInfoS=new int[vN-1][2];
        for(int i=0;i<vN-1;i++){
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int k=0;k<2;k++){
                vInfoS[i][k]=Integer.parseInt(stk.nextToken());
            }
        }
        System.out.println(T.solution(vN,vInfoS));
    }
}