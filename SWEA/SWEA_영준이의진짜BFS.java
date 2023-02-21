/*
No. 10 영준이의 진짜 BFS

1. 문제 재정의 및 추상화 
진짜 BFS? 
공통 조상 찾기 문제. 

2. 풀이과정 상세히 적기
생략 

3. 시간복잡도 계산 
O((V+E)*log(V))
V: 노드의 개수
E: 간선의 개수

4. 코드 작성 
*/

import java.util.*;
import java.io.*;
public class Solution{
    int nodeN;
    int[] parentS;
    Map<Integer,List<Integer>> graph;
    
    int solution(int nodeN, int[] parentS, Map<Integer,List<Integer>> graph){
        int answer=0;
        init(nodeN,parentS,graph);
        Queue<Integer> queue=new ArrayDeque<>();
        int prevNd=1;
        int totalDist=0;
        queue.offer(1);
        while(!queue.isEmpty()){
            int nd=queue.poll();
            totalDist+=getDist(prevNd,nd);
            // System.out.println(totalDist);
            prevNd=nd;
            for(int child: graph.getOrDefault(nd,new ArrayList<>())){
                queue.offer(child);
            }
        }
        answer=totalDist;
        return answer;
    }
    
    void init(int nodeN, int[] parentS, Map<Integer,List<Integer>> graph){
        this.nodeN=nodeN;
        this.parentS=parentS;
        this.graph=graph;
    }
    
    int getDist(int bfNode,int afNode){
        List<Integer> bfNdAncS=new ArrayList<>();
        List<Integer> afNdAncS=new ArrayList<>();
        ancestor(bfNode,bfNdAncS);
        ancestor(afNode,afNdAncS);
        int i=0;
        for(;i<Math.min(bfNdAncS.size(),afNdAncS.size());i++){
            if(afNdAncS.get(i)!=bfNdAncS.get(i))    break;
        }
        return afNdAncS.size()+bfNdAncS.size()-(i*2);
    }
    
    void ancestor(int nd, List<Integer> ancS){
        if(parentS[nd]!=0){
            ancestor(parentS[nd],ancS);
        }
        ancS.add(nd);
    }
    
    public static void main(String[] args) throws Exception{
        Solution T=new Solution();
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        int tcN=Integer.parseInt(kb.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tci=1;tci<=tcN;tci++){
            int nodeN=Integer.parseInt(kb.readLine());
            int[] parentS=new int[nodeN+1];
            Map<Integer,List<Integer>> graph=new HashMap<>();
            StringTokenizer stk=new StringTokenizer(kb.readLine());
            for(int i=2;i<2+nodeN-1;i++){
                parentS[i]=Integer.parseInt(stk.nextToken());
                graph.putIfAbsent(parentS[i],new ArrayList<>());
                graph.get(parentS[i]).add(i);
            }
            sb.append('#').append(tci).append(' ')
            .append(T.solution(nodeN,parentS,graph)).append('\n');
        }
        System.out.print(sb);
    }
}
    
