/*
최단 경로 (BJ 1753)
1. 문제 재정의 및 추상화 
V: 정점 개수, E: 간선 개수, K: 시작 정점의 번호 
간선 정보 E개 주어짐. 서로 다른 정점 사이에 여러 개의 간선이 존재할 수 있다. 
그럴 때 가장 작은 값으로 초기화한다. 인접 리스트를 사용한다. 인접 행렬을 사용하면 
4억 개의 요소를 가진 2차원 배열을 만들어야 하기 때문이다. Set으로 인접 정점을 저장한다. 
가중치 값을 수정하고 싶으면 어떻게 해야 하는가? 
4억 개의 요소 1_600_000_000 1GB? 절대 저장하지 못한다. 리스트에 저장하고, 그냥 순회해도 되지 않을까? 될 것 같다. 어차피 PriorityQueue에 의해 정렬되기 때문이다. (visited로 체크도 한다. )

2. 풀이과정 상세히 적기
전형적인 Dijkstra 알고리즘 문제다. 생략. 

3. 시간복잡도 계산
O(ElogE) E: 간선의 개수 

**
추가는 각 간선마다 최대 한 번 일어나기 때문에, 우선순위 큐에 추가되는 원소의 수는 최대 O(|E|)의 시간이 걸리고, O(|E|)개의 원소에 대해 이 작업을 하려면 전체 시간 복잡도는 O(|E||log|E|)가 됨을 알 수 있습니다.
**

4. 코드 작성  
*/
import java.util.*;
import java.io.*;
public class Main{
    static class Node implements Comparable<Node>{
        int no;
        int weight;
        Node(int no,int weight){
            this.no=no;
            this.weight=weight;
        }
        @Override
        public int compareTo(Node other){
            return Integer.compare(weight,other.weight);
        }
    }
    
    int[] solution(int vN, int eN, int start, List<Node>[] adjS){
        int[] answer;
        int[] distS=new int[vN+1];
        Arrays.fill(distS,Integer.MAX_VALUE);
        boolean[] isMinDistFixed=new boolean[vN+1];
        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.offer(new Node(start,0));
        distS[start]=0;
        while(!pq.isEmpty()){
            Node nd=pq.poll();
            if(isMinDistFixed[nd.no])   continue;
            isMinDistFixed[nd.no]=true;
            for(Node adj: adjS[nd.no]){
                if(isMinDistFixed[adj.no])  continue;
                if(distS[adj.no]<distS[nd.no]+adj.weight)  continue;
                distS[adj.no]=distS[nd.no]+adj.weight;
                pq.offer(new Node(adj.no,distS[adj.no]));
            }
        }
        answer=distS;
        return answer;
    }
    
    public static void main(String[] args) throws Exception{
        Main T=new Main();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk=new StringTokenizer(kb.readLine());
        int vN=Integer.parseInt(stk.nextToken());
        int eN=Integer.parseInt(stk.nextToken());
        int start=Integer.parseInt(kb.readLine());
        List<Node>[] adjS=new ArrayList[vN+1];
        for(int i=1;i<=vN;i++){
            adjS[i]=new ArrayList<>();
        }
        for(int i=0;i<eN;i++){
            stk=new StringTokenizer(kb.readLine());
            int from=Integer.parseInt(stk.nextToken());
            int to=Integer.parseInt(stk.nextToken());
            int weight=Integer.parseInt(stk.nextToken());
            adjS[from].add(new Node(to,weight));
            // adjS[to].add(new Node(from,weight));
        }
        int[] answer=T.solution(vN,eN,start,adjS);
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=vN;i++){
            if(answer[i]==Integer.MAX_VALUE){
                sb.append("INF");
            }else{
                sb.append(answer[i]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}