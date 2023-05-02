/*
트리와 쿼리
1. 문제 재정의 및 추상화 
어떤 정점을 루트노드인 서브 트리에 속한 정점의 수를 구하여라
이런 쿼리가 굉장히 많이 이루어진다. 최대 10만회 이루어진다.
이 때 어떻게 최적화할 수 있을까? 하나의 쿼리에 할당되는 시간복잡도가 O(logN)이어야 한다.
충분히 가능할 것 같다. 처음에 그래프를 입력 받을 때 각 정점의 부모 노드를 기록한다.
그런 다음 리프 노드에서 시작하여 루트 노드까지 서브 트리의 노드 개수를 1만큼 증가시킨다.
이는 시간복잡도가 O(NlogN)이다. 이러한 전처리를 모두 완료한 후에는 모든 정점을 해시 셋에 저장한다. 이러한 전처리 작업을 모두 완료하고 나면 각 쿼리에 필요한 시간복잡도는 O(1)이 된다. 

2. 풀이과정 상세히 적기
생략

3. 시간복잡도 계산
O(NlogN) <= 전처리에 필요한 시간복잡도
O(M) <= 모든 쿼리를 다 처리하는데 필요한 시간복잡도
여기서 N은 트리의 정점 개수이고, M은 쿼리의 개수이다. 

4. 코드 작성 
*/
import java.util.*;
import java.io.*;
public class BJ_트리와쿼리{
    
    Map<Integer,Integer> subTreeNodeInfo;
    List<Integer>[] adjS;
    int vN,root,qN,eN;
    int[][] eS;
    int[] qS;
    final int MAX=(int)Math.pow(10,5);
    
    String solution(int vN, int root, int qN, int eN, int[][] eS, int[] qS){
        String answer="";
        init(vN,root,qN,eN,eS,qS);
        dfs(root,new boolean[MAX+1]);
        answer=query();
        return answer;
    }
    
    // 멤버 변수 초기화 
    void init(int vN, int root, int qN, int eN, int[][] eS, int[] qS){
        this.vN=vN;
        this.root=root;
        this.qN=qN;
        this.eS=eS;
        this.qS=qS;
        subTreeNodeInfo=new HashMap<>();
        adjS=new ArrayList[MAX+1];
        for(int[] e: eS){
            int v1=e[0];
            int v2=e[1];
            subTreeNodeInfo.putIfAbsent(v1,1);
            subTreeNodeInfo.putIfAbsent(v2,1);
            if(adjS[v1]==null)  adjS[v1]=new ArrayList<>();
            if(adjS[v2]==null)  adjS[v2]=new ArrayList<>();
            adjS[v1].add(v2);
            adjS[v2].add(v1);
        }
    }
    
    // 트리 순회, 서브 트리의 노드 개수 계산 
    void dfs(int v, boolean[] visited){
        visited[v]=true;
        for(int adj: adjS[v]){
            if(visited[adj])    continue;
            dfs(adj,visited);
            subTreeNodeInfo.put(v,subTreeNodeInfo.get(v)+subTreeNodeInfo.get(adj));
            visited[adj]=false;
        }
    }
    
    // 쿼리 수행 
    String query(){
        StringBuilder sb=new StringBuilder();
        for(int q: qS){
            sb.append(subTreeNodeInfo.get(q)).append('\n');
        }
        return sb.toString();
    }
    
    public static void main(String[] args) throws Exception{
        BJ_트리와쿼리 T=new BJ_트리와쿼리();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int vN=Integer.parseInt(stk.nextToken());
        int root=Integer.parseInt(stk.nextToken());
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
        System.out.println(T.solution(vN,root,qN,eN,eS,qS));
    }
}